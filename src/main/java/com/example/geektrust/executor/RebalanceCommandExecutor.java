package com.example.geektrust.executor;

import com.example.geektrust.command.Command;
import com.example.geektrust.domain.Allocation;
import com.example.geektrust.domain.Portfolio;
import com.example.geektrust.domain.PortfolioTracker;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class RebalanceCommandExecutor extends CommandExecutor {
    private static final String REBALANCE = "REBALANCE";

    BalanceCommandExecutor balanceCommandExecutor = new BalanceCommandExecutor();

    @Override
    public Boolean isApplicable(Command command) {
        return command.getName().equals(REBALANCE);
    }

    @Override
    protected Boolean isValid(Command command) {
        return command.getParameters() == null || command.getParameters().isEmpty();
    }

    @Override
    protected void executeValidCommand(Command command, PortfolioTracker portfolioTracker) {
        /*if (portfolioTracker.getPortfolioHistory().size() < 6) {
            System.out.println("CANNOT_REBALANCE");
            return;
        }
        Portfolio currentPortfolio = portfolioTracker.getCurrentPortfolio();
        final int equity = currentPortfolio.getEquity();
        final int debt = currentPortfolio.getDebt();
        final int gold = currentPortfolio.getGold();
        int total = equity + debt + gold;
        Allocation allocation = portfolioTracker.getDesiredAssetAllocation();
        currentPortfolio.setEquity((int) (total * allocation.getEquity()  / 100));
        currentPortfolio.setDebt((int) (total * allocation.getDebt()  / 100));
        currentPortfolio.setGold((int) (total * allocation.getGold()  / 100));
        System.out.println(String.format("%d %d %d", currentPortfolio.getEquity(), currentPortfolio.getDebt(), currentPortfolio.getGold()));*/

        if (portfolioTracker.getPortfolioHistory().size() < 6) {
            System.out.println("CANNOT_REBALANCE");
            return;
        }
        final List<Portfolio> portfolios = portfolioTracker.getPortfolioHistory().stream()
                .filter(p -> p.getMonth().equals(Month.JUNE) || p.getMonth().equals(Month.DECEMBER))
                .collect(Collectors.toList());
        Portfolio rebalancedPortfolio = portfolios.get(portfolios.size()-1);
        System.out.println(String.format("%d %d %d", rebalancedPortfolio.getEquity(), rebalancedPortfolio.getDebt(), rebalancedPortfolio.getGold()));

    }

}
