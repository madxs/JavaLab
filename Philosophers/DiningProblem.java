import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


    public class DiningProblem {

        private static final int AMOUNT = 5;

        private static final int SIMULATION_TIME = 1000*5 ;

        public static void main(String args[]) throws InterruptedException {
            ExecutorService executorService = null;
            Philisopher[] philosophers = null;

            try {
                philosophers = new Philisopher[AMOUNT];
                ChopStick[] chopSticks = new ChopStick[AMOUNT];

                for (int i = 0; i < AMOUNT; i++) {
                    chopSticks[i] = new ChopStick(i);
                }

                executorService = Executors.newFixedThreadPool(AMOUNT);

                for (int i = 0; i < AMOUNT; i++) {
                    philosophers[i] = new Philisopher(i, chopSticks[i], chopSticks[(i + 1) % AMOUNT]);
                    executorService.execute(philosophers[i]);
                }

                Thread.sleep(SIMULATION_TIME);

                for (Philisopher philosopher : philosophers) {
                    philosopher.isFull = true;
                }

            } finally {
                executorService.shutdown();

                while (!executorService.isTerminated()) {
                    Thread.sleep(1000);
                }

                for (Philisopher philosopher : philosophers) {
                    System.out.println(philosopher + " No turns to eat! "
                            + philosopher.getNoOfTurnsToEat());
                }
            }
        }
    }

