package com.epam.jmp.dto;

import java.util.Objects;

public class DebitBankCard extends BankCard {

    private final static  double DEFAULT_DEBIT_LIMIT = 0;
    private double balance;

    public DebitBankCard( User user) {
        super(user);
        this.balance = DEFAULT_DEBIT_LIMIT;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DebitBankCard that = (DebitBankCard) o;
        return Double.compare(balance, that.balance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), balance);
    }

    @Override
    public String toString() {
        return "DebitBankCard{" +
                "balance=" + balance +
                '}';
    }
}
