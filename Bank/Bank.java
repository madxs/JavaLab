import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {

    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<Account>();
    }

    public Bank(List<Account> accounts) {
        this.accounts = accounts;
    }


    public List<Account> getAccounts() {
        return accounts;
    }


    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getRandomAccount() {
        return accounts.get(new Random().nextInt(accounts.size()));
    }
}

