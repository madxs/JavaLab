package philosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DiningPhilosophers {

    public static void main(String[] args) throws Exception {

        int thinkFactor = 7;
        int size = 5;

        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[size];

        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }

        for (int i = 0; i < size; i++) {
            if (i < (size - 1)) {
                exec.execute(new Philosopher(chopsticks[i], chopsticks[i + 1], i, thinkFactor));
            } else {
                exec.execute(new Philosopher(chopsticks[0], chopsticks[i], i, thinkFactor));
            }

        }
        exec.shutdown();
    }
}
