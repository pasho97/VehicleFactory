package com.company;

import com.company.storages.TransactionalPersistentStorage;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class FactoryTest {

    private AssemblyLine line=mock(AssemblyLine.class);
    private TransactionalPersistentStorage transactionsStorage = mock(TransactionalPersistentStorage.class);
    private Factory factory;


    @Test
    public void testFactoryCallsAssemblyLineWorkMethodWithTransactionsStoragePassedAsArgument() throws InterruptedException {
        when(transactionsStorage.hasWork()).thenReturn(true,false);
        when(transactionsStorage.getNextRow()).thenReturn(new String[]{"test1","test2"});
        factory=new Factory(line,transactionsStorage);
        factory.start();
        verify(line,times(1)).work(transactionsStorage);
    }

    @Test
    public void testFactoryReadFilesCallsTransactionsStorageFillMethod() throws IOException {
        factory=new Factory(line,transactionsStorage);
        FileInputStream fileInputStream=mock(FileInputStream.class);
        factory.readFiles(fileInputStream);
        verify(transactionsStorage,times(1)).fillWork(fileInputStream);
    }

}
