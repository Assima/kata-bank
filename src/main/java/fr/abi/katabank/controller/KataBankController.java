package fr.abi.katabank.controller;

import fr.abi.katabank.domain.Account;
import fr.abi.katabank.domain.Statement;
import fr.abi.katabank.domain.operation.ConsolidatedOperation;
import fr.abi.katabank.exception.OperationException;
import fr.abi.katabank.service.AccountBusinessService;
import fr.abi.katabank.service.impl.AccountBusinessServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Supplier;

public class KataBankController {
    private enum Action {
        DEPOSIT,
        WITHDRAW,
        HISTORY,
        EXIT
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final Supplier<LocalDateTime> now = LocalDateTime::now;
    private final Scanner in = new Scanner(System.in);
    private final AccountBusinessService accountBusinessService = new AccountBusinessServiceImpl();
    private final Account account = new Account(new Statement());

    public KataBankController() {
    }

    public void run() {
        while (true) {
            int amount;
            Action action = mainMenu();
            switch (Objects.requireNonNull(action)) {
                case DEPOSIT:
                    amount = getAmount();
                    accountBusinessService.deposit(account, amount, now.get());
                    System.out.println("Money saved.");
                    break;
                case WITHDRAW:
                    amount = getAmount();
                    try {
                        accountBusinessService.withdraw(account, amount, now.get());
                        System.out.println("Money withdrawn.");
                    } catch (OperationException e) {
                        System.out.println("You are not solvent. Retry or make a deposit first.");
                    }
                    break;
                case HISTORY:
                    System.out.println("Account history.");
                    displayAll(account);
                    break;
                case EXIT:
                    System.out.println("Exiting Program");
                    System.exit(0);
                    break;
            }
        }

    }

    /**
     * Action menu to interact with user.
     *
     * @return The action selected.
     */
    private Action mainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Save money");
        System.out.println("2. Withdraw money");
        System.out.println("3. Check History");
        System.out.println("4. Close program");
        System.out.println();
        String choice;
        do {
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    return Action.DEPOSIT;
                case "2":
                    return Action.WITHDRAW;
                case "3":
                    return Action.HISTORY;
                case "4":
                    return Action.EXIT;
                default:
                    System.out.println("Enter a number from 1 to 4");
            }
        } while (!choice.equals("4"));
        return null; //should never reach here
    }

    /**
     * Wraps the amount entered.
     *
     * @return The amount entered.
     */
    private int getAmount() {
        System.out.println("Enter amount: ");
        String value = in.nextLine();
        while (value == null || value.isEmpty()) {
            System.out.println("Amount is not valid retry: ");
            value = in.nextLine();
        }
        return Math.abs(Integer.parseInt(value));
    }

    /**
     * Displays account statement.
     *
     * @param account Account to display statement for.
     */
    private void displayAll(Account account) {
        List<ConsolidatedOperation> operations = Optional.ofNullable(account)
                .map(Account::getStatement)
                .map(Statement::getOperations)
                .orElse(new ArrayList<>());
        operations.sort(ConsolidatedOperation::compareTo);
        for (ConsolidatedOperation operation : operations) {
            System.out.println("===========================================");
            displayConsolidatedOperation(operation);
            System.out.println("===========================================");
        }
    }

    /**
     * Displays an operation.
     *
     * @param consolidatedOperation The operation to display.
     */
    private void displayConsolidatedOperation(ConsolidatedOperation consolidatedOperation) {
        System.out.println(" Operation Date: " + consolidatedOperation.getOperation().getOperationDate().format(DATE_TIME_FORMATTER));
        System.out.println(" Operation type: " + consolidatedOperation.getOperation().getType());
        System.out.println(" Operation amount: " + consolidatedOperation.getOperation().getAmount().getFormattedValue());
        System.out.println(" Operation current balance: " + consolidatedOperation.getCurrentBalance().getFormattedValue());
    }
}
