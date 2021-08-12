package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Client implements Iterable<Deposit>{
    private static final int DEPOSIT_ARRAY_LENGTH = 10;
    private static final String ILLEGAL_ARGUMENT = "Argument must not be null";
    private final Deposit[] deposits;
    private int size=-1;

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
                size++;
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
        return new ClientIterator();
    }

    public class DepositComparator implements Comparator<Deposit>{
        @Override
        public int compare(Deposit o1, Deposit o2) {
            return o2.incomeAmount().compareTo(o1.incomeAmount());
        }
    }
    class ClientIterator implements Iterator<Deposit>{
        private int i;

        public ClientIterator() {
            this.i = 0;
        }
	
        @Override
        public boolean hasNext() {
          if (size<0) return false;
        else return i<deposits.length-1;
        }
    
        @Override
        public Deposit next() {
            if(hasNext()) return deposits[i++];
              else throw new NoSuchElementException();
        }
    }

    public void sortDeposits(){
        if (size<0) throw new IllegalArgumentException();
        else Arrays.sort(deposits, 0, size, new DepositComparator());
    }
    public int countPossibleToProlongDeposit(){
        int sum=0;
        for (Deposit deposit:deposits) {
           if ((deposit instanceof Prolongable)&&((Prolongable) deposit).canToProlong())  sum++;
        }
        return sum;
    }
}