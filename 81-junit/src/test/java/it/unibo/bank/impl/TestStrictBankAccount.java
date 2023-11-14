package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestStrictBankAccount {

    private final static int INITIAL_AMOUNT = 100;
    private final static int MANAGEMENT_FEE = 5;
    private final static double TRANSACTION_FEE = 0.1;

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new StrictBankAccount(mRossi, 0.0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals("Mario", mRossi.getName());
        assertEquals("Rossi", mRossi.getSurname());
        assertEquals(1, mRossi.getUserID());
        assertEquals(mRossi, bankAccount.getAccountHolder());
        assertEquals(0.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        bankAccount.deposit(mRossi.getUserID(), INITIAL_AMOUNT);
        final double totalfees = (MANAGEMENT_FEE + bankAccount.getTransactionsCount() * TRANSACTION_FEE);
        bankAccount.chargeManagementFees(mRossi.getUserID());
        assertEquals(INITIAL_AMOUNT - totalfees, bankAccount.getBalance() );
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        try{
            bankAccount.withdraw(mRossi.getUserID(), -100);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot withdraw a negative amount", e.getMessage());
        }
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        
        try{
            bankAccount.withdraw(mRossi.getUserID(), bankAccount.getBalance() * 2 + 1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Insufficient balance", e.getMessage());
        }
    }
}
