package com.company.command;

/**
 * Simple command interface
 */
public interface Command {
    /**
     * @return String containing the name of the command instance
     */
    String getCommandName();

    /**
     * @param stringToInterpret The command args to be interpreted
     * @return The output of the command as string
     */
    String interpret(String stringToInterpret);
}
