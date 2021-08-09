package com.epam.test.automation.java.practice8;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BaseDeposit extends Deposit{

    public BaseDeposit(BigDecimal depositAmount,int depositPeriod) {
        super(depositAmount,depositPeriod);
    }

    @Override
    /**
     * Calculate 5% of interest from current deposit sum
     */
    public BigDecimal income() {
        final double percent=1.05;
        BigDecimal sum=this.amount;
        for (int i = 1; i < period+1; i++)
            sum=sum.multiply(BigDecimal.valueOf(percent));
        return sum.subtract(this.amount).setScale(2, RoundingMode.HALF_DOWN);
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
