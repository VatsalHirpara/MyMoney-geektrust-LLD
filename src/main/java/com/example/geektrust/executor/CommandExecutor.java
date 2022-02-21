package com.example.geektrust.executor;

import com.example.geektrust.command.Command;
import com.example.geektrust.domain.Portfolio;
import com.example.geektrust.domain.PortfolioTracker;

public abstract class CommandExecutor {

    public void execute(final Command command, PortfolioTracker portfolioTracker) {
        if (!isValid(command)) {
            System.out.println("Invalid command : " + command.getName()+ " "+ portfolioTracker);
        }
         this.executeValidCommand(command, portfolioTracker);
    }

    public abstract Boolean isApplicable(final Command command);

    protected abstract Boolean isValid(final Command command);

    protected  abstract void executeValidCommand(final Command command, PortfolioTracker portfolioTracker);
}
