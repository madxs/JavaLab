import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Runner {

    private static final int THREAD_TRANSFERS_AMOUNT = 30;
    private static final int THREADS_AMOUNT = 1500;
    private static final int ACCOUNT_AMOUNT = 100;
    private static final int MAX_INITIAL_BALANCE = 5000;
    private static final int MAX_TRANSFER_SUM = 500;
    private static final int MIN_TRANSFER_SUM = 10;

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        List<Account> accounts = new LinkedList<Account>();
        Bank bank = new Bank(accounts);

        for (int i = 0; i < ACCOUNT_AMOUNT; i++) {
            accounts.add(new Account(random.nextInt(MAX_INITIAL_BALANCE), i));
        }

        int expectedSumm = calculateTotalBankSumm(bank);

        System.out.println(expectedSumm);

        List<Thread> threads = new LinkedList<Thread>();

        for (int i = 0; i < THREADS_AMOUNT; i++) {
            Thread thread = new Thread(new Transactional(bank.getRandomAccount(),bank.getRandomAccount(), new Random().nextInt(MAX_INITIAL_BALANCE) ));
            threads.add(thread);
            thread.join();
        }

        for (Thread t : threads) {
            t.start();
            t.join();
        }

        int realSum = calculateTotalBankSumm(bank);
        System.out.println("Start sum: " + expectedSumm + " Result sum: " + realSum);

        }

        private static int calculateTotalBankSumm(Bank bank) {
            List<Account> accounts = bank.getAccounts();
            int expectedSumm = 0;
            for (Account account : accounts) {
                expectedSumm += account.getBalance();
            }
            return expectedSumm;
        }
    }

