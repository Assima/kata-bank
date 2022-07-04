package fr.abi.katabank.service.impl;

import fr.abi.katabank.domain.Money;
import fr.abi.katabank.domain.operation.Operation;
import fr.abi.katabank.exception.OperationException;
import fr.abi.katabank.service.OperationService;

import java.time.LocalDateTime;

public abstract class OperationServiceImpl implements OperationService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Operation createOperation(int amount, LocalDateTime dateTime) {
        if (amount == 0) {
            throw new OperationException("Incorrect Amount");
        }
        return new Operation(Money.of(amount), dateTime);
    }
}
