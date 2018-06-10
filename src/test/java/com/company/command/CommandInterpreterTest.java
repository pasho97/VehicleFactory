package com.company.command;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class CommandInterpreterTest {
    private CommandInterpreter interpreter;
    private Command command;

    @Test
    public void testCommandInterpreterUsesCommandsGetCommandNameMethodToMatchStringCommandToTheActualCommandImpl() {
        command = mock(Command.class);
        when(command.getCommandName()).thenReturn("test");
        interpreter = new CommandInterpreter(Collections.singletonList(command));
        interpreter.interpret("test test1");
        verify(command, times(1)).getCommandName();
    }

    @Test
    public void testCommandInterpreterCallsCommandsByNameIfThereIsAMatch() {
        command = mock(Command.class);
        when(command.getCommandName()).thenReturn("test");
        interpreter = new CommandInterpreter(Collections.singletonList(command));
        interpreter.interpret("test test1");
        verify(command, times(1)).interpret(anyString());
    }

    @Test
    public void testInterpretThrowsExceptionIfNoMatchingCommandImplementationIsFound(){
        command = mock(Command.class);
        when(command.getCommandName()).thenReturn("test");
        interpreter = new CommandInterpreter(Collections.singletonList(command));
        try {
            interpreter.interpret("testException test1");
            Assert.fail();
        }catch (UnsupportedOperationException ex){
            Assert.assertEquals("Unsupported command",ex.getMessage());
        }
    }

    @Test
    public void testInterpretMethodReturnsRequestedCommandInterpretResult(){
        command=mock(Command.class);
        when(command.getCommandName()).thenReturn("test");
        when(command.interpret(anyString())).thenReturn("result");
        interpreter=new CommandInterpreter(Collections.singletonList(command));
        Assert.assertEquals("result",interpreter.interpret("test random input here"));
    }
}
