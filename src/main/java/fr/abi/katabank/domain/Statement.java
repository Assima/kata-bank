package fr.abi.katabank.domain;

import fr.abi.katabank.domain.operation.ConsolidatedOperation;

import java.util.ArrayList;
import java.util.List;

public class Statement {

    private List<ConsolidatedOperation> operationList = new ArrayList<>();

    public List<ConsolidatedOperation> getOperations() {
        return operationList;
    }

    public void setOperationList(List<ConsolidatedOperation> operationList) {
        this.operationList = operationList;
    }

}
