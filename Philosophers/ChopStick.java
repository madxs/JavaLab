import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ChopStick {

    Lock up = new ReentrantLock();

    private final int id;

    public ChopStick(int id) {
            this.id = id;
    }

    public boolean pickUp(Philisopher philosoph, String location) throws InterruptedException {
        if (up.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println(philosoph + " picked up " + location + " " + this);
            return true;
        }
        return false;
    }

    public void putDown(Philisopher philosoph, String name) {
        up.unlock();
        System.out.println(philosoph + " put down " + name + " " + this);
    }

    @Override
    public String toString() {
        return "Chopstick - " + id;
        }
    }

