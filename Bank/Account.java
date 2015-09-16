

public class Account {

    private int balance;

    private int id;

    public Account(int balance, int id) {
        this.balance = balance;
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean withdraw(int amount) {
        if (balance < amount) {
            return false;
        }

        balance -= amount;
            return true;
    }

    public void deposit(int amount) {
        balance += amount;
    }


    @Override
    public String toString() {
        return "Account [balance=" + balance + "]";
    }

}

