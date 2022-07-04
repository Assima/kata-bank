package fr.abi.katabank.service;

import fr.abi.katabank.domain.Account;

import java.time.LocalDateTime;

public interface AccountBusinessService {
    /**
     * Deposits some money on the account.
     *
     * @param account  The account to deposit money on.
     * @param money    The money that is depositted.
     * @param dateTime The deposit date.
     */
    void deposit(Account account, int money, LocalDateTime dateTime);

    /**
     * Withdraw some money from the account.
     *
     * @param account  The account to withdraw money from.
     * @param money    The money that is withdrawn.
     * @param dateTime The withdrawn date.
     */
    void withdraw(Account account, int money, LocalDateTime dateTime);
}
