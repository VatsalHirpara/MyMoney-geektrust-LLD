package com.example.geektrust.executor;

import com.example.geektrust.command.Command;
import com.example.geektrust.domain.Allocation;
import com.example.geektrust.domain.Portfolio;
import com.example.geektrust.domain.PortfolioTracker;

import java.time.Month;

public class AllocateCommandExecutor extends CommandExecutor {
    private static final String ALLOCATE = "ALLOCATE";

    @Override
    public Boolean isApplicable(Command command) {
        return command.getName().equals(ALLOCATE);
    }

    @Override
    protected Boolean isValid(Command command) {
        if (command.getParameters().size() != 3) return false;
        for (String parameter : command.getParameters()) {
            try {
                int number = Integer.parseInt(parameter);
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void executeValidCommand(Command command, PortfolioTracker portfolioTracker) {
        int equity = Integer.parseInt(command.getParameters().get(0));
        int debt = Integer.parseInt(command.getParameters().get(1));
        int gold = Integer.parseInt(command.getParameters().get(2));
        int total = equity + debt + gold;
        final Portfolio portfolio = Portfolio.builder()
                .equity(equity)
                .debt(debt)
                .gold(gold)
                .month(Month.JANUARY)
                .build();
        portfolioTracker.setCurrentPortfolio(portfolio);
        Allocation allocation = Allocation.builder()
                .equity(calculateAllocationPercentage(equity,total))
                .debt(calculateAllocationPercentage(debt,total))
                .gold(calculateAllocationPercentage(gold,total)).build();
        portfolioTracker.setDesiredAssetAllocation(allocation);
    }
    private double calculateAllocationPercentage(int asset, int totalAssets) {
        double percentage = asset * 100.0 / totalAssets;
        return Math.round(percentage * 100.0) / 100.0;
    }
}
