package com.example.geektrust.executor;

import com.example.geektrust.command.Command;
import com.example.geektrust.domain.PortfolioTracker;
import com.example.geektrust.domain.Sip;

public class SipCommandExecutor extends CommandExecutor {
    private static final String SIP = "SIP";

    @Override
    public Boolean isApplicable(Command command) {
        return command.getName().equals(SIP);
    }

    @Override
    protected Boolean isValid(Command command) {
        return command.getParameters().size() == 3;
    }

    @Override
    protected void executeValidCommand(Command command, PortfolioTracker portfolioTracker) {
        int equity = Integer.parseInt(command.getParameters().get(0));
        int debt = Integer.parseInt(command.getParameters().get(1));
        int gold = Integer.parseInt(command.getParameters().get(2));
        final Sip sip = Sip.builder()
                .equity(equity)
                .debt(debt)
                .gold(gold).build();
        portfolioTracker.setSip(sip);
    }
}
