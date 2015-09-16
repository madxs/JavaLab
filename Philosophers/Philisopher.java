import java.util.Random;

public class Philisopher implements Runnable {

    private final int id;

    private final ChopStick leftChopStick;

    private final ChopStick rightChopStick;

    volatile boolean isFull = false;

    private Random randomGenerator = new Random();

    private int noTurnsToEat = 0;


    public Philisopher(int id, ChopStick leftChopStick, ChopStick rightChopStick) {
        this.id = id;
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
    }

        @Override
        public void run() {

            try {
                while (!isFull) {
                    think();
                    if (leftChopStick.pickUp(this, "left")) {
                        if (rightChopStick.pickUp(this, "right")) {
                            eat();
                            rightChopStick.putDown(this, "right");
                        }
                        leftChopStick.putDown(this, "left");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void think() throws InterruptedException {
            System.out.println(this + " is thinking");
            Thread.sleep(randomGenerator.nextInt(1000));
        }

        private void eat() throws InterruptedException {
            System.out.println(this + " is eating");
            noOfTurnsToEat++;
            Thread.sleep(randomGenerator.nextInt(1000));
        }

        public int getNoOfTurnsToEat() {
            return noOfTurnsToEat;
        }

        @Override
        public String toString() {
            return "Philosopher - " + id;
        }
    }





