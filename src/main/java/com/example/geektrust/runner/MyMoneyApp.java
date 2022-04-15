package com.example.geektrust.runner;

import com.example.geektrust.command.Command;
import com.example.geektrust.domain.PortfolioTracker;
import com.example.geektrust.executor.CommandExecutor;
import com.example.geektrust.factory.CommandFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class MyMoneyApp {
    List<CommandExecutor> commandExecutors;
    List<Command> commands;
    PortfolioTracker portfolioTracker;

    public MyMoneyApp(List<CommandExecutor> commandExecutors) {
        this.commandExecutors = commandExecutors;
        this.portfolioTracker = new PortfolioTracker();
        this.commands = new ArrayList<>();
    }

    public void runCommands() {
        for (Command command : this.commands) {
            for (CommandExecutor commandExecutor : commandExecutors) {
                if (commandExecutor.isApplicable(command)) {
                    commandExecutor.execute(command, portfolioTracker);
                }
            }
        }
    }

    public void loadCommandsFromFile(String filepath) {
        try {
            Files.readAllLines(Paths.get(filepath)).forEach(commandString -> this.commands.add(createCommand(commandString.trim())));
        } catch (IOException e) {
            System.out.println("No such file exists : " + filepath); // should be replaced with logger in real application
        }
    }

    private Command createCommand(String commandString) {
        return CommandFactory.createCommand(commandString);
    }
}
