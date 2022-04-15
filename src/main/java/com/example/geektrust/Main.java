package com.example.geektrust;

import com.example.geektrust.executor.*;
import com.example.geektrust.runner.MyMoneyApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//todo change java version before submitting

public class Main {
    public static void main(String[] args) {
        List<CommandExecutor> commandExecutors = new ArrayList<>(Arrays.asList(new AllocateCommandExecutor(), new BalanceCommandExecutor(), new SipCommandExecutor(), new ChangeCommandExecutor(), new RebalanceCommandExecutor()));
        MyMoneyApp myMoneyApp = new MyMoneyApp(commandExecutors);
        myMoneyApp.loadCommandsFromFile("sample_input/input1.txt");
        //myMoneyApp.loadCommandsFromFile(args[0]); //To take input file path from command line
        myMoneyApp.runCommands();
    }
}