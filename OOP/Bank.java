package OOP;
import java.util.*;
// Make a simple banking application to perform deposit,withdraw and check balance operations and make it menu driven , allow the user to choose bewtween current and saving account
class Bank {
    static class Account {
        String accountType;
        double balance;

        Account(String accountType) {
            this.accountType = accountType;
            this.balance = 0.0;
        }

        void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: " + amount);
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        }

        void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Withdrew: " + amount);
            } else if (amount > balance) {
                System.out.println("Insufficient balance.");
            } else {
                System.out.println("Withdrawal amount must be positive.");
            }
        }

        void checkBalance() {
            System.out.println("Current balance: " + balance);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose account type (1. Savings 2. Current): ");
        int choice = sc.nextInt();
        Account account;

        if (choice == 1) {
            account = new Account("Savings");
        } else if (choice == 2) {
            account = new Account("Current");
        } else {
            System.out.println("Invalid choice. Exiting.");
            return;
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
 