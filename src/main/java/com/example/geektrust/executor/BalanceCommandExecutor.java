package com.example.geektrust.executor;

import com.example.geektrust.command.Command;
import com.example.geektrust.domain.Portfolio;
import com.example.geektrust.domain.PortfolioTracker;

import java.util.Collections;
import java.util.Optional;

public class BalanceCommandExecutor extends CommandExecutor {
    private static final String BALANCE = "BALANCE";

    @Override
    public Boolean isApplicable(Command command) {
        return command.getName().equals(BALANCE);
    }

    @Override
    protected Boolean isValid(Command command) {
        return null != command.getParameters() && command.getParameters().size() == 1;
    }

    @Override
    protected void executeValidCommand(Command command, PortfolioTracker portfolioTracker) {
        final String month = command.getParameters().get(0);
        Optional<Portfolio> portfolioOptional = portfolioTracker.getPortfolioHistory()
                .stream()
                .filter(p -> p.getMonth().toString().equals(month))
                .findFirst();
        if (portfolioOptional.isPresent()) {
            Portfolio portfolio = portfolioOptional.get();
            System.out.printf("%d %d %d %n", portfolio.getEquity(), portfolio.getDebt(), portfolio.getGold());
        } else {
            System.out.println("Portfolio doesn't exist for month : " + month);
        }
    }
}