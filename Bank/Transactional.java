
public class Transactional implements Runnable {

    private final Account from;
    private final Account to;
    private final int trunsferSum;

    public Transactional(Account from, Account to, int trunsferSum) {
        this.from = from;
        this.to = to;
        this.trunsferSum = trunsferSum;
    }

    @Override
    public void run() {

        if (from.getId() == to.getId()) {
            return;
        }
        synchronized (from) {
            synchronized (to) {
                if (from.withdraw(trunsferSum)) {
                    to.deposit(trunsferSum);
                    System.out.println("Transaction from account " + from.getId() +" to " + to.getId() +" trunsferSum: " + trunsferSum);
                } else {
                    System.out.println("No money, no honey.");

                }
            }
        }
    }
}
