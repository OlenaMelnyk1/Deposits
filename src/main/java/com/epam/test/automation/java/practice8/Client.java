package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.util.Iterator;


public class Client implements Iterable<Deposit> {
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

    /**
     *
     * @return total income amount based on all client’s deposits with %
     */
    public BigDecimal totalIncome(){
        BigDecimal totInc=BigDecimal.ZERO;
        for (Deposit deposit: deposits) {
            if (deposit !=null)
                totInc=totInc.add(deposit.income());
        }
        return totInc;
    }

    /**
     *
     * @return max deposit income with %
     */
    public BigDecimal maxIncome(){
        BigDecimal maxIncome=deposits[0].income();
        for (var deposit: deposits) {
            if ((deposit!=null)&&(maxIncome.compareTo(deposit.income())<0)) 
                maxIncome=deposit.income();
            }
        return maxIncome;
    }

    /**
     *
     * @param n - number elements array
     * @return - income from deposit with such number
     */
    public BigDecimal getIncomeByNumber(int n){
        if (n<0){
            throw new IllegalArgumentException();
        }
        if (deposits[n]==null) return BigDecimal.ZERO;
        return deposits[n].income();
    }


    @Override
    public Iterator<Deposit> iterator() {
        return new clientIterator();
    }
	
    class clientIterator implements Iterator<Deposit>{
        private int i;
        
        public clientIterator() {
            this.i = 0;
        }
	
        @Override
        public boolean hasNext() {
          if (deposits[i]==null) return false;
        return i<deposits.length-1;
        }
    
        @Override
        public Deposit next() {
            if(hasNext()) return deposits[i++];
              else throw new NoSuchElementException();
        }
    }

    public Deposit[] sortDeposits(){
        Deposit temp;
        for (int i =deposits.length-1; i >1; i--) {
            for (int j = 0; j < i; j++) {
                if (deposits[j+1].incomeAmount().compareTo(deposits[j].incomeAmount())>0){
                    temp=deposits[j+1];
                    deposits[j+1]=deposits[j];
                    deposits[j]=temp;
                }

            }

        }
        return deposits;
    }

    public int countPossibleToProlongDeposit(){
        int sum=0;
        for (Deposit deposit:deposits) {
           if ((deposit instanceof Prolongable)&&((Prolongable) deposit).canToProlong())  sum++;
        }
        return sum;
    }
}