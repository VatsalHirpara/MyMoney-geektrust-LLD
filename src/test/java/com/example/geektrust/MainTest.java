package com.example.geektrust;

import com.example.geektrust.executor.*;
import com.example.geektrust.runner.MyMoneyApp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    @DisplayName("Test for input file 1")
    public void normalTet() {
        final ByteArrayOutputStream byteArrayOutputStream = captureConsoleOutput();
        List<CommandExecutor> commandExecutors = new ArrayList<>(Arrays.asList(new AllocateCommandExecutor(), new BalanceCommandExecutor(), new SipCommandExecutor(), new ChangeCommandExecutor(), new RebalanceCommandExecutor()));
        MyMoneyApp myMoneyApp = new MyMoneyApp(commandExecutors);
        myMoneyApp.loadCommandsFromFile("sample_input/input1.txt");
        myMoneyApp.runCommands();
        System.out.flush();
        String consoleOutput = byteArrayOutputStream.toString();
        String expectedOutput = "19050 5120 16412 \r\n" + "32467 6493 25974\r\n";
        assertEquals(expectedOutput, consoleOutput);
    }

    private ByteArrayOutputStream captureConsoleOutput() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
        return byteArrayOutputStream;
    }
}