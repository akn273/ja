package com.epam.jmp.app;

import com.epam.impl.bank.impl.BankImpl;
import com.epam.jmp.bank.Bank;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.Service;
import com.epam.jmp.service.impl.ServiceImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {

        Service service = new ServiceImpl();
        Bank bank = new BankImpl();
        demonstrateCard(BankCardType.CREDIT, "name1", "surname1", LocalDate.of(2010, 1, 1), service, bank);
        demonstrateCard(BankCardType.DEBIT, "name2", "surname2", LocalDate.of(2000, 2, 2), service, bank);

        service.getAllUsers().forEach(user -> {
            System.out.print("Registered user: " + user);
            System.out.println(" payable? " + Service.isPayableUser(user));
        });

        System.out.print("\nAverage age: ");
        System.out.println(service.getAverageUsersAge());

        System.out.println("Today was/were subscribed:: ");
        System.out.println(service.getAllSubscriptionsByCondition(subscription -> ChronoUnit.DAYS.between(subscription.getStartDate(), LocalDate.now()) == 0));

    }

    private static void demonstrateCard(BankCardType cardType, String name, String surname, LocalDate birthday, Service service, Bank bank) {
        BankCard card = bank.createBankCard(new User(name, surname, birthday), cardType);
        System.out.println("New  " + cardType + " card: " + card);
        service.subscribe(card);
        System.out.print("Subscription for correct card number: ");
        String cardNumber = card.getNumber();
        Subscription subscription = service.getSubscriptionByBankCardNumber(cardNumber);
        printSubscription(subscription);
        System.out.println();
    }

    private static void printSubscription(Subscription subscription) {
        System.out.println(subscription);
    }

}