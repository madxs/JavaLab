package philosophers;

public class Philosopher implements Runnable {

    private Chopstick left;
    private Chopstick right;
    private int id;
    private final int thinkFactor;


    public Philosopher(Chopstick left, Chopstick right, int id, int thinkFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.thinkFactor = thinkFactor;

    }

    private void pause() throws InterruptedException {
        if (thinkFactor == 0) return;
        Thread.sleep(thinkFactor * 100);

    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {

                System.out.println(this + " " + "thinking...");
                pause();

                System.out.println(this + " " + "take right chopstick");
                right.take();
                System.out.println(this + " " + "take left chopstick");
                left.take();
                System.out.println(this + " eating. " );

                System.out.println(this + " " + "thinking...");
                pause();

                right.drop();
                left.drop();
            }

        } catch (InterruptedException ex) {
            System.out.println(this + " " + "end up because of interupt thread ");
        }

    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Philosopher №" + id;

    }
}
