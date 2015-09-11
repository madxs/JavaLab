package threadpool;

import java.util.LinkedList;
import java.util.Queue;


public class ThreadPool {
    
    private static final String ERROR_MESSAGE_TASK_IS_NULL = "Task value is null";
    private static final String ERROR_MESSAGE_EMPTY_POOL = "Capacity pool is empty";

    private final Queue<Runnable> queueTasks;
    private final Thread[] executors;


    public ThreadPool(int capacity) {
    	checkCapacity(capacity);
    	executors = new Thread[capacity];
        queueTasks = new LinkedList<Runnable>();
        for (int i = 0; i < executors.length; i++) {
            executors[i] = new Executor("Thread №" + i);
            executors[i].start();
        }
    }

    private void checkCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY_POOL);
        }
    }
    
    private void checkTaskIsNull(Runnable task) {
        if (task == null)
            throw new IllegalArgumentException(ERROR_MESSAGE_TASK_IS_NULL);
    }
    
    public synchronized void submitTask(Runnable task) {  
        checkTaskIsNull(task);
        synchronized(queueTasks) {
          queueTasks.add(task);
          queueTasks.notifyAll();
        }
    }
    
    public void shutdown() throws InterruptedException {
        synchronized (queueTasks) {
            while (!queueTasks.isEmpty()) {
                queueTasks.wait();
            }
            for (Thread workerThread : executors) {
                workerThread.interrupt();
            }
        }
    }     
    
    private class Executor extends Thread {
        public Executor(String name) {
            super(name);
        }
        
        @Override
        public void run() {
            while (!isInterrupted()) {
                Runnable task = null;
                synchronized (queueTasks) {
                    if (!queueTasks.isEmpty()) {
                        task = queueTasks.poll();
                    } else{
                        try {
                            queueTasks.wait();
                        } catch (InterruptedException ex) {
                            return;
                        }
                        if (!queueTasks.isEmpty()) {
                            task = queueTasks.poll();
                        }
                    } 
                    queueTasks.notifyAll();
                }
                if (task != null) {
                    task.run();
                }
            }
        }
    }
}
