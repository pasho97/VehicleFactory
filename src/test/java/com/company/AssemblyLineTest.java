package com.company;

import com.company.command.CommandInterpreter;
import com.company.storages.TransactionalPersistentStorage;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.mockito.Mockito.*;

public class AssemblyLineTest {
    private AssemblyLine line;
    private CommandInterpreter interpreter=mock(CommandInterpreter.class);
    private TransactionalPersistentStorage storageForTransactions;
    private Logger logger;
    @Before
    public void init() throws InterruptedException {
        logger=mock(Logger.class);
        line=new AssemblyLine(interpreter,logger);
        storageForTransactions=mock(TransactionalPersistentStorage.class);
        when(storageForTransactions.hasWork()).thenReturn(true);
        when(storageForTransactions.getNextRow()).thenReturn(new String[]{"test1","test2"});
    }
    @Test
    public void testAssemblyLineCallsInterpreterInternallyAndLogsAsInfoTheResult() throws InterruptedException {
        when(interpreter.interpret("test2")).thenReturn("testPassed");
        line.work(storageForTransactions);

        verify(logger,times(1)).info("testPassed");
    }

    @Test
    public void testAssemblyLineCallsInterpreterInternallyIfExceptionThrownLogsAsWarningTheMessage() throws InterruptedException {
        when(interpreter.interpret("test2")).thenThrow(new RuntimeException("testPassed"));
        line.work(storageForTransactions);

        verify(logger,times(1)).warning("testPassed");
    }


    @Test(expected = Exception.class)
    public void testAssemblyLineDoesNotCatchNorLogCheckedExceptions() throws InterruptedException {
        when(interpreter.interpret("test2")).thenThrow(new Exception("testPassed"));
        line.work(storageForTransactions);

    }
}
