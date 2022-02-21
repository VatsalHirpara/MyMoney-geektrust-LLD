package com.example.geektrust.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;


@Builder
@AllArgsConstructor
public class Portfolio {
    private int equity;
    private int debt;
    private int gold;
    private Month month;


    public Portfolio() {
    }

    public Portfolio(Portfolio portfolio) {
        this.equity = portfolio.getEquity();
        this.debt = portfolio.getDebt();
        this.gold = portfolio.getGold();
        this.month = portfolio.getMonth();
    }

    public int getEquity() {
        return equity;
    }

    public void setEquity(int equity) {
        this.equity = equity;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}
