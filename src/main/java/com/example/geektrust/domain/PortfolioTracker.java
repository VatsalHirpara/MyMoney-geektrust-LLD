package com.example.geektrust.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioTracker {
    private Portfolio currentPortfolio;
    private Sip sip;
    private Allocation desiredAssetAllocation;
    private List<Portfolio> portfolioHistory = new ArrayList<>();
}
