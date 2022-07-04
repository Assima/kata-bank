package fr.abi.katabank.service.impl;

import fr.abi.katabank.domain.Money;
import fr.abi.katabank.domain.operation.Operation;

public class WithdrawalOperationServiceImpl extends OperationServiceImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    public Money balance(Operation operation, Money money) {
        return money.minus(operation.getAmount());
    }
}
