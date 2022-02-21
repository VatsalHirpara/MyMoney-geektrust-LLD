package com.example.geektrust.executor;

import com.example.geektrust.command.Command;
import com.example.geektrust.domain.Allocation;
import com.example.geektrust.domain.Portfolio;
import com.example.geektrust.domain.PortfolioTracker;

import javax.sound.sampled.Port;
import java.time.Month;
import java.util.List;

public class ChangeCommandExecutor extends CommandExecutor {
    private static final String CHANGE = "CHANGE";

    @Override
    public Boolean isApplicable(Command command) {
        return command.getName().equals(CHANGE);
    }

    @Override
    protected Boolean isValid(Command command) {
        return command.getParameters().size() == 4;
        // validate input
    }

    @Override
    protected void executeValidCommand(Command command, PortfolioTracker portfolioTracker) {
        List<String> parameters = command.getParameters();
        double equityChangePercentage = extractChangePercentageValue(parameters.get(0));
        double debtChangePercentage = extractChangePercentageValue(parameters.get(1));
        double goldChangePercentage = extractChangePercentageValue(parameters.get(2));
        String month = parameters.get(3);

        Portfolio currentPortfolio = portfolioTracker.getCurrentPortfolio();

        if (!month.equals(Month.JANUARY.toString()) && portfolioTracker.getSip() != null) {
            currentPortfolio.setEquity(currentPortfolio.getEquity() + portfolioTracker.getSip().getEquity());
            currentPortfolio.setDebt(currentPortfolio.getDebt() + portfolioTracker.getSip().getDebt());
            currentPortfolio.setGold(currentPortfolio.getGold() + portfolioTracker.getSip().getGold());
        }
        currentPortfolio.setMonth(Month.valueOf(month));

        currentPortfolio.setEquity((int) (currentPortfolio.getEquity() + (currentPortfolio.getEquity() * equityChangePercentage / 100)));
        currentPortfolio.setDebt((int) (currentPortfolio.getDebt() + (currentPortfolio.getDebt() * debtChangePercentage / 100)));
        currentPortfolio.setGold((int) (currentPortfolio.getGold() + (currentPortfolio.getGold() * goldChangePercentage  / 100)));

        if (month.equals(Month.JUNE.toString()) || month.equals(Month.DECEMBER.toString())) {
            final int equity = currentPortfolio.getEquity();
            final int debt = currentPortfolio.getDebt();
            final int gold = currentPortfolio.getGold();
            int total = equity + debt + gold;
            Allocation allocation = portfolioTracker.getDesiredAssetAllocation();
            currentPortfolio.setEquity((int) (total * allocation.getEquity() / 100));
            currentPortfolio.setDebt((int) (total * allocation.getDebt() / 100));
            currentPortfolio.setGold((int) (total * allocation.getGold() / 100));
        }
        portfolioTracker.getPortfolioHistory().add(new Portfolio(currentPortfolio));
    }

    private double extractChangePercentageValue(String parameter) {
        return Double.parseDouble(parameter.replace("%", ""));
    }
}
