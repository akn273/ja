package com.epam.jmp.service.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.Service;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    private static final String USER_NAME = "userName";
    private static final String USER_SURNAME = "userSurname";
    LocalDate currentDate;

    Service service;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    public void setUp() {
        currentDate = LocalDate.now();
        service = new ServiceImpl();
    }

    public void testisPayableUser_true() {
        User user = new User(USER_NAME, USER_SURNAME, currentDate.minusYears(Service.PAYABLE_AGE));
        assertTrue(Service.isPayableUser(user));
    }

    public void testisPayableUser_trueOld() {
        User user = new User(USER_NAME, USER_SURNAME, currentDate.minusYears(3 * Service.PAYABLE_AGE));
        assertTrue(Service.isPayableUser(user));
    }


    public void testisPayableUser_false() {
        User user = new User(USER_NAME, USER_SURNAME, currentDate.minusYears(Service.PAYABLE_AGE).plusDays(1));
        assertFalse(Service.isPayableUser(user));
    }

    public void testisPayableUser_falseFuture() {
        User user = new User(USER_NAME, USER_SURNAME, currentDate.plusYears(Service.PAYABLE_AGE).plusDays(1));
        assertFalse(Service.isPayableUser(user));
    }

    public void testSubscribe_null() {
        service.subscribe(null);
        assertEquals(0, service.getAllUsers().size());
    }

    public void testSubscribe_user() {
        User user1 = new User(USER_NAME, USER_SURNAME, currentDate);
        BankCard bankCard = new BankCard(user1);

        service.subscribe(bankCard);

        assertEquals(1, service.getAllUsers().size());
        assertEquals(USER_NAME, service.getAllUsers().get(0), user1);
    }

    public void testSubscribe_bankcardOk() {
        User user1 = new User(USER_NAME, USER_SURNAME, currentDate);
        BankCard bankCard = new BankCard(user1);

        service.subscribe(bankCard);
        Subscription subscription = service.getSubscriptionByBankCardNumber(bankCard.getNumber());

        assertEquals(bankCard.getNumber(), subscription.getBankcard());
        assertEquals(bankCard.getUser(), bankCard.getUser());
    }

    public void testGetAllSubscriptionsByCondition_found() {
        User user1 = new User(USER_NAME, USER_SURNAME, currentDate);
        BankCard bankCard = new BankCard(user1);
        bankCard.setNumber(StringUtils.EMPTY);
        service.subscribe(bankCard);
        List<Subscription> subscriptions = service.getAllSubscriptionsByCondition((subscr) -> subscr.getBankcard().isBlank());

        assertEquals(1, subscriptions.size());
    }

    public void testGetAllSubscriptionsByCondition_notFound() {
        User user1 = new User(USER_NAME, USER_SURNAME, currentDate);
        BankCard bankCard = new BankCard(user1);
        service.subscribe(bankCard);
        List<Subscription> subscriptions = service.getAllSubscriptionsByCondition((subscr) -> subscr.getBankcard().isBlank());

        assertEquals(0, subscriptions.size());
    }

    public void testGetAverageUsersAge() {
        User user1 = new User(USER_NAME, USER_SURNAME, currentDate.minusYears(10));
        User user2 = new User(USER_NAME, USER_SURNAME, currentDate.minusYears(20));
        service.subscribe(new BankCard(user1));
        service.subscribe(new BankCard(user2));

        double averageAge = service.getAverageUsersAge();

        assertEquals(15, averageAge, 0.0001);
    }

}
