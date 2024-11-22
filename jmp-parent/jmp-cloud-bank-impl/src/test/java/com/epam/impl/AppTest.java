package com.epam.impl;

import com.epam.impl.bank.impl.BankImpl;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.DebitBankCard;
import com.epam.jmp.dto.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.time.LocalDate;

import static com.epam.impl.bank.impl.BankImpl.NO_CARD_TYPE_DEFINED;


/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    private User user;
    private BankImpl bank;

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

    public void setUp() {
        user = new User("user1n", "user2sn", LocalDate.of(2000, 1, 1));
        bank = new BankImpl();

    }

    public void testCreateBankCard_DebitCardType() {
        BankCard bankCard = bank.createBankCard(user, BankCardType.DEBIT);
        assertEquals(bankCard.getClass().getName(), DebitBankCard.class.getName());
    }

    public void testCreateBankCard_CreditCardType() {
        BankCard bankCard = bank.createBankCard(user, BankCardType.CREDIT);
        assertEquals(bankCard.getClass().getName(), CreditBankCard.class.getName());
    }

    public void testCreateBankCard_nullCardType(){
        try
        {
            bank.createBankCard(user, null);
            fail( "Missing exception" );
        }
        catch (IllegalArgumentException iaex)
        {
            assertEquals( NO_CARD_TYPE_DEFINED, iaex.getMessage() );
        }
    }

    public void testCreateBankCard_correctUser() {
        BankCard bankCard = bank.createBankCard(user, BankCardType.CREDIT);
        assertEquals(bankCard.getUser(), user);
    }


}
