package com.example.geektrust;

import com.example.geektrust.executor.*;
import com.example.geektrust.runner.MyMoneyApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//todo change java version before submitting
public class Main {
    public static void main(String[] args) throws IOException {
        List<CommandExecutor> commandExecutors =
                new ArrayList<>(Arrays.asList(new AllocateCommandExecutor()
                        , new BalanceCommandExecutor()
                        , new SipCommandExecutor()
                        , new ChangeCommandExecutor()
                        , new RebalanceCommandExecutor()
                ));
        MyMoneyApp myMoneyApp = new MyMoneyApp(commandExecutors);
//        myMoneyApp.loadCommandsFromFile("sample_input_/input1.txt");
        myMoneyApp.loadCommandsFromFile(args[0]);
        myMoneyApp.runCommands();
    }
}