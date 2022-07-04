package fr.abi.katabank.domain.operation;

import fr.abi.katabank.domain.Money;

public class ConsolidatedOperation implements Comparable<ConsolidatedOperation> {
    private Money currentBalance;
    private Operation operation;

    public ConsolidatedOperation(Money currentBalance, Operation operation) {
        this.currentBalance = currentBalance;
        this.operation = operation;
    }

    public Money getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Money currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public int compareTo(ConsolidatedOperation o) {
        return this.operation.compareTo(o.getOperation());
    }
}
