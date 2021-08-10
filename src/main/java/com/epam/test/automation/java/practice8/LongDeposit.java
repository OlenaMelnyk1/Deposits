package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LongDeposit extends  Deposit implements Prolongable{
    private static final int LONG_TERM=36;
    BigDecimal sum = BigDecimal.ZERO;

    public LongDeposit(BigDecimal depositAmount,int depositPeriod){
        super(depositAmount,depositPeriod);
    }


    @Override
    public BigDecimal income() {
        final double percent = 1.15;
        sum = this.amount;
        if (period<7) return BigDecimal.ZERO;
            else {
                for (int i = 7; i <= period; i++)
                    sum = sum.multiply(BigDecimal.valueOf(percent));
                
            }
        return sum.subtract(this.amount).setScale(2, RoundingMode.HALF_DOWN);
    }

    @Override
    public boolean canToProlong() {
        return (this.period<LONG_TERM);
    }
    @Override
    public BigDecimal incomeAmount() {
        return income().add(this.amount).setScale(2,RoundingMode.HALF_DOWN);
    }

    @Override
    public int compareTo(Deposit o) {
        return this.incomeAmount().compareTo(o.incomeAmount());
    }
}
