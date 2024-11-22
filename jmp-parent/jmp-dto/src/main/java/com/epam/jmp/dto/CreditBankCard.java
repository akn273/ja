package com.epam.jmp.dto;

import java.util.Objects;
import java.util.UUID;

public class CreditBankCard extends BankCard {
    private final static  double DEFAULT_CREDIT_LIMIT = 100;

    private double creditLimit;
    public CreditBankCard(User user) {
        super(user);
        this.creditLimit = DEFAULT_CREDIT_LIMIT;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditBankCard that = (CreditBankCard) o;
        return Double.compare(creditLimit, that.creditLimit) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(creditLimit);
    }

    @Override
    public String toString() {
        return "CreditBankCard{" +
                "creditLimit=" + creditLimit +
                '}';
    }
}
