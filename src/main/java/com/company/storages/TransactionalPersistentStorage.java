package com.company.storages;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

/**
 * Objects of this class are used in the transaction logic of the factory to preserve uninterpreted commands in a
 * database table if something unexpected happens
 */
@Component
public class TransactionalPersistentStorage {
    private static final String TABLE = "transactionTable";
    private final JdbcTemplate template;
    private final BlockingQueue<Integer> unprocessedCommandsIds;


    /**
     * This constructor also checks if there are commands from previous executions of the program and logs a message
     * with their number if there are any and also creates the needed table if it does not exist already
     *
     * @param template Spring JdbcTemplate instance already configured for the chosen database
     */
    public TransactionalPersistentStorage(JdbcTemplate template) {
        this.template = template;
        unprocessedCommandsIds = new LinkedBlockingQueue<>();
        String createTable = "IF NOT EXISTS (SELECT * " +
                "               FROM INFORMATION_SCHEMA.TABLES " +
                "                 WHERE TABLE_NAME = '%s') " +
                "    BEGIN" +
                "    CREATE TABLE %s (id INTEGER  IDENTITY PRIMARY KEY,info VARCHAR(100), processed BIT DEFAULT 0) " +
                "                END;";

        template.execute(String.format(createTable, TABLE, TABLE));
        recalculateUnprocessed();
        if (unprocessedCommandsIds.size() > 0) {
            Logger.getGlobal().info(unprocessedCommandsIds.size() +
                    " COMMANDS WAITING TO BE PROCESSED SINCE LAST EXECUTION");
        }
    }

    private void recalculateUnprocessed() {
        unprocessedCommandsIds.clear();

        template.execute("DELETE FROM " + TABLE + " WHERE processed = 1 ;");
        SqlRowSet set = template.queryForRowSet("SELECT id FROM " + TABLE + " WHERE processed = 0 ;");
        List<Integer> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getInt("id"));
        }

        unprocessedCommandsIds.addAll(list);
    }

    /**
     * @param id id number of the unprocessed command in the database that this method will mark as processd
     */
    public void markProcessed(String id) {
        template.update("UPDATE " + TABLE + " SET processed = 1 WHERE id = ?", id);

    }

    public boolean hasWork() {
        return !unprocessedCommandsIds.isEmpty();
    }

    public String[] getNextRow() throws InterruptedException {
        if (!unprocessedCommandsIds.isEmpty()) {
            return template.queryForObject("SELECT TOP 1 id,info FROM " + TABLE + " WHERE id = ?;",
                    (resultSet, i) -> new String[]{
                            resultSet.getString("id"), resultSet.getString("info")},
                    unprocessedCommandsIds.take());
        }

        return null;
    }

    public void fillWork(InputStream... streams) throws IOException {
        BufferedReader[] readers = new BufferedReader[streams.length];
        for (int i = 0; i < streams.length; i++) {
            readers[i] = new BufferedReader(new InputStreamReader(streams[i]));
        }
        List<String> commands = new ArrayList<>();
        boolean hasRead = true;
        while (hasRead) {
            hasRead = false;
            for (BufferedReader reader : readers) {
                if (reader.ready()) {
                    hasRead = true;
                    commands.add(reader.readLine());
                }
            }
        }

        template.batchUpdate("INSERT INTO " + TABLE + " VALUES (?,0);", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, commands.get(i));
            }

            @Override
            public int getBatchSize() {
                return commands.size();
            }
        });

        recalculateUnprocessed();


    }
}
