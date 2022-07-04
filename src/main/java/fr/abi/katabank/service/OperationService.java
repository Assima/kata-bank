package fr.abi.katabank.service;

import fr.abi.katabank.domain.Money;
import fr.abi.katabank.domain.operation.Operation;

import java.time.LocalDateTime;

public interface OperationService {
    /**
     * Creates a new operation.
     *
     * @param amount   the amount used to create operation.
     * @param dateTime The operation date.
     * @return a new {@link Operation}.
     */
    Operation createOperation(int amount, LocalDateTime dateTime);

    /**
     * Calculates balance.
     *
     * @param operation The operation to balance with.
     * @param money The money to balance with.
     * @return The balance.
     */
    Money balance(Operation operation, Money money);
}
