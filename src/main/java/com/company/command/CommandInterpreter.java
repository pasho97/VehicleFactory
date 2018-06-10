package com.company.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Command interpreter used for interpreting string with command name and command args joined by single empty space
 */
@Component
public class CommandInterpreter {
    private final Map<String, Command> commandHashMap;

    /**
     * @param commands The command the interpreter must support
     */
    @Autowired
    public CommandInterpreter(List<Command> commands) {
        commandHashMap = new Hashtable<>();
        for (Command command : commands
                ) {
            commandHashMap.put(command.getCommandName(), command);
        }
    }

    /**
     * The method used for interpreting the commands , logs info or warnings
     *
     * @param input The string input to be interpreted
     * @return String information about the execution of the command
     * @throws UnsupportedOperationException if command is unsupported
     */
    public String interpret(String input) {
        input = input.trim();
        String command = input.split(" ")[0];

        if (!commandHashMap.containsKey(command)) {
            System.out.println(command);
            throw new UnsupportedOperationException("Unsupported command");
        }
        String commandArgs = input.substring(command.length() + 1);
        Command result = commandHashMap.get(command);

        return result.interpret(commandArgs);
    }

}
