package fr.abi.katabank.domain.operation;

import fr.abi.katabank.domain.Money;

import java.time.LocalDateTime;

public class Operation implements Comparable<Operation> {
    private Money amount;
    private LocalDateTime operationDate;

    private String type;

    public Operation(Money amount, LocalDateTime operationDate) {
        this.amount = amount;
        this.operationDate = operationDate;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDateTime operationDate) {
        this.operationDate = operationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Operation o) {
        if (getOperationDate() == null || o == null || o.getOperationDate() == null) {
            return 0;
        }
        return o.operationDate.compareTo(this.operationDate);
    }
}
