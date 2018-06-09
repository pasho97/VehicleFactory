package com.company;

import com.company.vehicles.Vehicle;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * SQL SERVER vehicle persistent storage implementation
 */
public class SqlServerVehiclePersistentStorage implements VehiclePersistentStorage {
    private static final String DISASSEMBLED_TABLE = "disassembledVehicles";
    private static final String VEHICLES_TABLE = "vehicles";
    private static final RowMapper<String> mapper = (resultSet, i) -> {
        StringBuilder builder = new StringBuilder();
        do {
            String vin = resultSet.getString("VIN");
            String model = resultSet.getString("model");
            String engine = resultSet.getString("engine");
            String transmission = resultSet.getString("transmission");
            builder.append(String.join(" ", vin, model, engine));
            if (transmission != null) {
                builder.append(" ").append(transmission).append("\n");
            } else {
                builder.append("\n");
            }

        } while (resultSet.next());
        return builder.toString();
    };
    private JdbcTemplate jdbcTemplate;

    /**
     * This constructor also creates the needed tables if they do not exist already
     *
     * @param dataSource dataSource used for connecting to the database
     */
    SqlServerVehiclePersistentStorage(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String createTableForVehicles = "IF NOT EXISTS (SELECT * " +
                "               FROM INFORMATION_SCHEMA.TABLES " +
                "                 WHERE TABLE_NAME = '%s') " +
                "    BEGIN" +
                "    CREATE TABLE %s (VIN CHAR(17) PRIMARY KEY ,model VARCHAR(20)," +
                "                engine VARCHAR(20), transmission VARCHAR(20) )" +
                "                END;";

        jdbcTemplate.execute(String.format(createTableForVehicles, VEHICLES_TABLE, VEHICLES_TABLE));
        jdbcTemplate.execute(String.format(createTableForVehicles, DISASSEMBLED_TABLE, DISASSEMBLED_TABLE));
    }

    @Override
    public void store(Vehicle vehicle) {
        String[] info = vehicle.getInfo().split(" ");
        store(info, VEHICLES_TABLE);
    }

    private void store(String[] info, String tableName) {
        String sql = String.format("INSERT INTO %s VALUES (?, ?, ?, ?);", tableName);
        if (info.length == 3) {
            jdbcTemplate.update(sql, info[0], info[1], info[2], null);
        } else if (info.length == 4) {
            jdbcTemplate.update(sql, info[0], info[1], info[2], info[3]);
        } else {
            throw new IllegalArgumentException("Cannot store " + Arrays.toString(info) + " to persistent storage");
        }
    }

    @Override
    public boolean uniqueVin(String vin) {
        String sql = "Select COUNT(*) FROM %s WHERE VIN=?;";

        return jdbcTemplate.queryForObject(String.format(sql, VEHICLES_TABLE), Integer.class, vin) +
                jdbcTemplate.queryForObject(String.format(sql, DISASSEMBLED_TABLE), Integer.class, vin) == 0;
    }

    @Override
    public String getInfoByVin(String vin) {
        try {
            return getInfo("VIN", vin);
        } catch (EmptyResultDataAccessException empty) {
            throw new IllegalArgumentException("Vehicle with the given VIN does not exist", empty);
        }
    }

    private String getInfo(String column, String value) {
        String sql = String.format("SELECT * FROM %s WHERE %s=? UNION SELECT * FROM %s WHERE %s=?;",
                VEHICLES_TABLE, column, DISASSEMBLED_TABLE, column);

        return jdbcTemplate.queryForObject(sql, mapper, value, value);
    }

    @Override
    public String getAllInfo() {
        return "\n" + jdbcTemplate.queryForObject("SELECT * FROM " + VEHICLES_TABLE + ";", mapper) +
                "\ndisassembled :\n" +
                jdbcTemplate.queryForObject("SELECT * FROM " + DISASSEMBLED_TABLE + ";", mapper);
    }

    @Override
    public String getColByVin(String vin, String colName) {
        String sql = String.format("SELECT %s FROM %s WHERE VIN=?", colName, VEHICLES_TABLE);
        try {
            return jdbcTemplate.queryForObject(sql, String.class, vin);
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("Vehicle with the given vin does not exist", exception);
        }
    }

    @Override
    public void updateByVin(String vin, String fields) {
        String sql = String.format("UPDATE %s SET %s WHERE VIN=?", VEHICLES_TABLE, fields);
        jdbcTemplate.update(sql, vin);
    }

    @Override
    public String disassemble(String vin) {
        String vehicleInfo = getInfoByVin(vin);
        if (vehicleInfo == null) {

            return null;
        }
        String sql = String.format("DELETE FROM %s WHERE VIN=?;", VEHICLES_TABLE);
        jdbcTemplate.update(sql, vin);
        store(vehicleInfo.split(" "), DISASSEMBLED_TABLE);
        return "disassembled " + vehicleInfo;
    }

    @Override
    public String getByEmissionStandard(String standard) {
        String sql = String.format("SELECT * FROM %s WHERE %s LIKE ?;", VEHICLES_TABLE, "engine");
        try {
            return jdbcTemplate.queryForObject(sql, mapper, "%-" + standard + "%");
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No vehicles found with the required emission standard", exception);
        }

    }
}
