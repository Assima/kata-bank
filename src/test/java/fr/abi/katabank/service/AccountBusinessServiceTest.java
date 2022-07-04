package fr.abi.katabank.service;

import fr.abi.katabank.domain.Account;
import fr.abi.katabank.domain.Money;
import fr.abi.katabank.domain.Statement;
import fr.abi.katabank.exception.OperationException;
import fr.abi.katabank.service.impl.AccountBusinessServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class AccountBusinessServiceTest {

    private static final LocalDateTime now = LocalDateTime.now();

    @Test
    public void givenAccountWithBalance100_whenDeposit_thenAddMoneyToAccount() {
        Account account = new Account(new Statement());
        account.setBalance(Money.of(100));

        AccountBusinessService accountBusinessService = new AccountBusinessServiceImpl();
        accountBusinessService.deposit(account, 5, now);

        Assert.assertEquals(105, account.getBalance().getValue());
    }

    @Test
    public void givenAccountWithBalance50_whenWithdraw100_thenThrowsException() {
        Account account = new Account(new Statement());
        account.setBalance(Money.of(50));

        AccountBusinessService accountBusinessService = new AccountBusinessServiceImpl();
        Assert.assertThrows(OperationException.class, () -> accountBusinessService.withdraw(account, 100, now));
    }

    @Test
    public void givenAccountWithBalancePositiveBalance_whenWithdrawMoneyGreaterThanBalance_thenWithdrawGivenAmount() {
        Account account = new Account(new Statement());
        account.setBalance(Money.of(100));

        AccountBusinessService accountBusinessService = new AccountBusinessServiceImpl();
        accountBusinessService.withdraw(account, 50, now);

        Assert.assertEquals(50, account.getBalance().getValue());
    }
}
