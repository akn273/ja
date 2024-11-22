package com.epam.jmp.service;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {
    int PAYABLE_AGE = 18;
    String USER_NOT_DEFINED_MSG = "User not defined";

    void subscribe(BankCard bankCard);

    Subscription getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> conditionPredicate);

    default double getAverageUsersAge() {
        var now = LocalDate.now();

        double averageAge = getAllUsers().stream()
                .map(User::getBirthday)
                .mapToDouble(birthday-> ChronoUnit.YEARS.between( birthday, now))
                .average()
                .orElse(0.0d);

        return Math.round(averageAge);
    }

    static boolean isPayableUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException(USER_NOT_DEFINED_MSG);
        }
        var now = LocalDate.now();
        return ChronoUnit.YEARS.between(user.getBirthday(),  now) >= PAYABLE_AGE;
    }
}
