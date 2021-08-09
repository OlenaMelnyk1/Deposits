package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SpecialDeposit extends Deposit implements Prolongable{

    BigDecimal sum= BigDecimal.ZERO;
    public SpecialDeposit(BigDecimal depositAmount,int depositPeriod) {
        super(depositAmount,depositPeriod);
    }

    @Override
    public BigDecimal income() {
        double percent=1.00;
        sum=this.amount;
        for (int i = 1; i < period+1; i++) {
            percent+=0.01;
            sum=sum.multiply(BigDecimal.valueOf(percent));
        }
        return sum.subtract(this.amount).setScale(2, RoundingMode.HALF_DOWN);
    }

    @Override
    public boolean canToProlong() {
        if (this.amount.compareTo(BigDecimal.valueOf(1000))>0)
            return true;
        return false;
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
