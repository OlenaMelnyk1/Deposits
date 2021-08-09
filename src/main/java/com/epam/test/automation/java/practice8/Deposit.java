package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;

public abstract class Deposit implements Comparable <Deposit> {
    public final  BigDecimal amount;
    public final int period;
    static final String INCORRECT_ARGS= "Incorrect period or amount";

    public Deposit(BigDecimal amount, int period){
        if ((amount.compareTo(BigDecimal.ZERO)<0) || (period<1) || (amount==null))
            throw new IllegalArgumentException(INCORRECT_ARGS);
        this.amount=amount;
        this.period=period;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    /** Income is the difference between sum amount at the begin and at the end deposit.
     *
     * @return
     */
    public abstract BigDecimal income();

    public abstract BigDecimal incomeAmount();


}