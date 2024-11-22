package com.epam.impl.bank.impl;

import com.epam.jmp.bank.Bank;
import com.epam.jmp.dto.*;


import java.util.function.Function;

public class BankImpl implements Bank {
    public final static String ILLEGAL_CARD_TYPE_MSG = "Illegal card type";
    public final static String NO_CARD_TYPE_DEFINED = "Card type is not defined";

    private final Function<User, BankCard> creditCardProducer = CreditBankCard::new;
    private final Function<User, BankCard> debitCardProducer = DebitBankCard::new;

    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        if (cardType == null) {
            throw new IllegalArgumentException(NO_CARD_TYPE_DEFINED);
        }
        switch (cardType) {
            case CREDIT:
                return creditCardProducer.apply(user);
            case DEBIT:
                return debitCardProducer.apply(user);
            default:
                throw new IllegalArgumentException(ILLEGAL_CARD_TYPE_MSG);
        }
    }
}
