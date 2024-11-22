package com.epam.jmp.service.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {

    public final static String SUBSCRIPTION_NOT_FOUND_EXCEPTION_MSG = "Subscription not found";

    final List<Subscription> SUBSCRIPTIONS = new ArrayList<>();
    final List<User> USERS = new ArrayList<>();

    @Override
    public void subscribe(BankCard bankCard) {
        if (bankCard != null && bankCard.getNumber() != null) {
            try {
                getSubscriptionByBankCardNumber(bankCard.getNumber());
            } catch (SubscriptionNotFoundException ex) {
                var subscription = new Subscription(bankCard.getNumber(), LocalDate.now());
                SUBSCRIPTIONS.add(subscription);
                USERS.add(bankCard.getUser());
            }
        }
    }

    @Override
    public Subscription getSubscriptionByBankCardNumber(String cardNumber) {
        return SUBSCRIPTIONS.stream()
                .parallel()
                .filter(item -> item.getBankcard().equals(cardNumber))
                .findAny()
                .orElseThrow(() -> new SubscriptionNotFoundException(SUBSCRIPTION_NOT_FOUND_EXCEPTION_MSG));
    }

    @Override
    public List<User> getAllUsers() {
        return USERS.stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> conditionPredicate){
        return SUBSCRIPTIONS.stream()
                .filter(conditionPredicate)
                .collect(Collectors.toList());
    }

}
