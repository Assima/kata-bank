package fr.abi.katabank.service.impl;

import fr.abi.katabank.domain.Account;
import fr.abi.katabank.domain.Money;
import fr.abi.katabank.domain.operation.ConsolidatedOperation;
import fr.abi.katabank.domain.operation.Operation;
import fr.abi.katabank.exception.OperationException;
import fr.abi.katabank.service.AccountBusinessService;
import fr.abi.katabank.service.OperationService;

import java.time.LocalDateTime;
import java.util.Optional;

public class AccountBusinessServiceImpl implements AccountBusinessService {

    private OperationService operationService;
    /**
     * {@inheritDoc}
     */
    @Override
    public void deposit(Account account, int money, LocalDateTime dateTime) {
        operationService = new DepositOperationServiceImpl();
        Operation operation = operationService.createOperation(money, dateTime);
        operation.setType("Deposit");
        Money balance = operationService.balance(operation, account.getBalance());
        ConsolidatedOperation consolidatedOperation = new ConsolidatedOperation(balance, operation);
        account.setBalance(balance);
        account.getStatement().getOperations().add(consolidatedOperation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void withdraw(Account account, int money, LocalDateTime dateTime) {
        if (!isSolvent(account, money)) {
            throw new OperationException("Account not solvent.");
        }
        operationService = new WithdrawalOperationServiceImpl();
        Operation operation = operationService.createOperation(money, dateTime);
        operation.setType("Withdraw");
        Money balance = operationService.balance(operation, account.getBalance());
        ConsolidatedOperation consolidatedOperation = new ConsolidatedOperation(balance, operation);
        account.setBalance(balance);
        account.getStatement().getOperations().add(consolidatedOperation);
    }

    /**
     * Checks whether the account is solvent or not
     *
     * @param account    The account to check on.
     * @param otherMoney The amount to balance with the
     * @return <code>true</code> if the account is solvent <code>false</code> otherwise.
     */
    private boolean isSolvent(Account account, int otherMoney) {
        return Optional.ofNullable(account)
                .map(Account::getBalance)
                .map(Money::getValue)
                .map(v -> v > otherMoney)
                .orElse(Boolean.FALSE);
    }

}
