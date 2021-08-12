package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;

public abstract class Deposit implements Comparable <Deposit> {
    public final  BigDecimal amount;
    public final int period;
    static final String INCORRECT_ARGS= "Incorrect period or amount";

    public Deposit(BigDecimal amount, int period){
        if ((period<1) || (amount==null))
            throw new IllegalArgumentException(INCORRECT_ARGS);
        this.amount=amount;
        this.period=period;
    }

    public abstract BigDecimal income();

    public abstract BigDecimal incomeAmount();


}