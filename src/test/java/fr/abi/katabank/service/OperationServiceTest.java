package fr.abi.katabank.service;

import fr.abi.katabank.domain.Money;
import fr.abi.katabank.domain.operation.Operation;
import fr.abi.katabank.exception.OperationException;
import fr.abi.katabank.service.impl.DepositOperationServiceImpl;
import fr.abi.katabank.service.impl.WithdrawalOperationServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class OperationServiceTest {

    @Test
    public void givenAnyOperationType_whenCreateOperationWithAmountZero_thenThrowException() {
        Assert.assertThrows(OperationException.class, () -> new DepositOperationServiceImpl().createOperation(0, LocalDateTime.now()));
    }

    @Test
    public void givenAnyOperationType_whenCreateOperation_returnOperationCreatedAtGivenDate() {
        LocalDateTime now = LocalDateTime.now();
        OperationService operationService = new DepositOperationServiceImpl();
        Operation operation = operationService.createOperation(9, now);
        Assert.assertNotNull(operation);
        Assert.assertNotNull(operation.getAmount());
        Assert.assertEquals(9, operation.getAmount().getValue());
        Assert.assertEquals(now, operation.getOperationDate());
    }

    @Test
    public void givenDepositOperation_whenBalance_addMoney() {
        LocalDateTime now = LocalDateTime.now();
        OperationService operationService = new DepositOperationServiceImpl();
        Operation operation = operationService.createOperation(5, now);
        Money balancedMoney = operationService.balance(operation, Money.of(5));
        Assert.assertNotNull(operation);
        Assert.assertNotNull(balancedMoney);
        Assert.assertNotNull(operation.getAmount());
        Assert.assertEquals(now, operation.getOperationDate());
        Assert.assertEquals(10, balancedMoney.getValue());
    }

    @Test
    public void givenWithdrawalOperation_whenBalance_removeGivenMoney() {
        LocalDateTime now = LocalDateTime.now();
        OperationService operationService = new WithdrawalOperationServiceImpl();
        Operation operation = operationService.createOperation(5, now);
        Money balancedMoney = operationService.balance(operation, Money.of(5));
        Assert.assertNotNull(operation);
        Assert.assertNotNull(balancedMoney);
        Assert.assertNotNull(operation.getAmount());
        Assert.assertEquals(now, operation.getOperationDate());
        Assert.assertEquals(0, balancedMoney.getValue());
    }
}
