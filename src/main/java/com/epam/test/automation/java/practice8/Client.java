package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;

public class Client {
    private static final int DEPOSIT_ARRAY_LENGTH = 10;
    private static final String ILLEGAL_ARGUMENT = "Argument must not be null";

    private final Deposit[] deposits;
    public Client() {
        this.deposits = new Deposit[DEPOSIT_ARRAY_LENGTH];
    }

    public boolean addDeposit(Deposit deposit) {
        if (deposit == null) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        for (int i = 0; i < deposits.length; i++) {
            if (this.deposits[i] == null){
                this.deposits[i] = deposit;
                return true;
            }
        }
        return false;
    }
    public BigDecimal totalIncome(){
        BigDecimal totInc=BigDecimal.ZERO;
        for (Deposit depos: deposits) {
            if (depos !=null)
                totInc=totInc.add(depos.income());
        }
        return totInc;
    }
    public BigDecimal maxIncome(){
        BigDecimal maxIncome=deposits[0].income();
        for (var depos: deposits) {
            if (maxIncome.compareTo(depos.income())<0)
                maxIncome=depos.income();
            }
        return maxIncome;
    }
    public BigDecimal getIncomeByNumber(int n){
        if (n<0){
            throw new IllegalArgumentException();
        }
        if (deposits[n]==null) return BigDecimal.ZERO;
        return deposits[n].income();
    }
}
