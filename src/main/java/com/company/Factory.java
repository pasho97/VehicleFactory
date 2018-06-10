package com.company;

import com.company.storages.TransactionalPersistentStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Objects of this class represent vehicle factories
 * <p>
 * When acquired as a spring bean , instances of this class benefit from @Transactional annotations making sure that
 * each command read from file will be executed or saved for later execution if an error occurs
 * <p>
 * These factories can have multiple assembly lines running at the same time , by default the count of the parallel
 * running assembly lines is 1
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class Factory {

    private AssemblyLine line;
    private InputStream inputStream;
    private int linesCount;
    private TransactionalPersistentStorage commandStorage;

    /**
     * @param line           The assembly line template that will be used , if more than one lines run in parallel all of them will
     *                       be having the same properties as this one
     * @param commandStorage storage used for command transactions
     */
    @Autowired
    Factory(AssemblyLine line, TransactionalPersistentStorage commandStorage) {
        this.linesCount = 1;
        this.line = line;
        this.commandStorage = commandStorage;
    }

    /**
     * @param inputStream input stream that can also execute commands , this should not be used with file input streams
     *                    because it waits for input
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * @param linesCount unsigned integer number representing the number of assembly lines that will work in parallel for
     *                   the commands from files or commands that remained from before
     */
    public void setLinesCount(int linesCount) {

        this.linesCount = linesCount;
    }

    /**
     * @param fileInputStreams File input streams containing commands to be interpreted
     * @throws IOException if an error with any of the file occurs
     */
    public void readFiles(FileInputStream... fileInputStreams) throws IOException {
        commandStorage.fillWork(fileInputStreams);
    }

    @Transactional
    public void start() throws InterruptedException {
        Queue<Thread> threadQueue = new LinkedList<>();
        Thread inputStreamThread = null;
        if (inputStream != null) {
            inputStreamThread = new Thread(() -> line.workOnInputStream(inputStream), "main line");
            inputStreamThread.start();
        }

        boolean didWorkOnFiles = false;

        while (commandStorage.hasWork()) {
            didWorkOnFiles = true;
            for (int i = 0; i < linesCount; i++) {
                Thread thread = new Thread(() -> {
                    try {
                        line.work(commandStorage);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, "line " + (i + 1));
                thread.start();
                threadQueue.add(thread);
            }
            while (!threadQueue.isEmpty()) {
                threadQueue.remove().join();
            }
        }

        if (didWorkOnFiles) {
            Logger.getAnonymousLogger().log(Level.INFO, "ALL FILE COMMANDS INTERPRETED !");
        }

        if (inputStreamThread != null) {
            inputStreamThread.join();
        }
    }

}

