package com.company;

import com.company.command.CommandInterpreter;
import com.company.storages.TransactionalPersistentStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Instances of this class represent assembly lines for factories
 * <p>
 * Every executed command's result or exception message logged using the provided in constructor Logger or
 * anonymous logger if no logger is provided
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class AssemblyLine {
    private final CommandInterpreter interpreter;
    private Logger LOGGER;

    public AssemblyLine(CommandInterpreter interpreter, Logger logger) {
        this.interpreter = interpreter;
        this.LOGGER = logger;
    }

    /**
     * This constructor initializes a default {@link Logger} with the name of the class
     *
     * @param interpreter Command interpreter containing the supported commands
     */
    @Autowired
    public AssemblyLine(CommandInterpreter interpreter) {
        this(interpreter, Logger.getLogger(AssemblyLine.class.getName()));
    }

    public void setLogger(Logger newLogger) {
        LOGGER = newLogger;
    }

    private void logInterpretResult(String arguments) {
        try {
            LOGGER.info(interpreter.interpret(arguments));

        } catch (RuntimeException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    void workOnInputStream(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        while (true) {
            String string = scanner.nextLine();
            if (string.equals("exit")) {
                return;
            }
            logInterpretResult(string);

        }
    }

    void work(TransactionalPersistentStorage commandStorageForTransactions) throws InterruptedException {
        if (commandStorageForTransactions.hasWork()) {
            String[] idCommandArray = commandStorageForTransactions.getNextRow();
            logInterpretResult(idCommandArray[1]);
            commandStorageForTransactions.markProcessed(idCommandArray[0]);
        }
    }
}

