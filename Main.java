import java.util.*;

class BankAccount {
    int balance = 0;

    void deposit(int amount) {
        balance += amount;
    }

    void withdraw(int amount) {
        balance -= amount;
    }

    int getBalance() {
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();

        BankAccount account = new BankAccount();

        for (int i = 0; i < N; i++) {
            String operation = sc.nextLine();
            String[] parts = operation.split(" ");

            int amount = Integer.parseInt(parts[1]);

            if (parts[0].equals("Deposit")) {
                account.deposit(amount);
            } else if (parts[0].equals("Withdraw")) {
                account.withdraw(amount);
            }
        }

        System.out.println(account.getBalance());
    }
}
