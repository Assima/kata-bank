# kata-bank
Think of your personal bank account experience When in doubt, go for the simplest solution
## Requirements
* Deposit
* Withdrawal
* Account statement (date, amount, balance)
* Statement printing
## Instructions
Clone the repo and open the project with your favorite IDE
* `mvn clean install` to build the project
* `java -jar kata-bank-${ptoject.version}.jar` to run the project from the  build output directory
# To Do
* Better use `BigDecimal` instead of `int` for account operation
* Maximum that can be deposited
* Overdraft that can be allowed
* Integration tests (i.e `KatBankControllerTest`) to validate user stories.
* Apply `Strategy Pattern` to handle operations.
