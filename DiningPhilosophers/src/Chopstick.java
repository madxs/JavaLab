package philosophers;


public class Chopstick {

    private volatile boolean isTaken = false;

    public synchronized void take() throws InterruptedException {
        while (isTaken) {
            wait();
        }
        isTaken = true;
    }

    public synchronized void drop() {
        isTaken = false;
        notifyAll();

    }

    /**
     * @return the value isTaken
     */
    public boolean isTaken() {
        return isTaken;
    }

}
