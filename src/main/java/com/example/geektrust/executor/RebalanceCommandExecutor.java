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
