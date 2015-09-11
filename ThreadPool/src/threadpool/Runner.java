package threadpool;


public class Runner {

    public static void main(String[] args) throws InterruptedException {
        
        ThreadPool pool = new ThreadPool(5);
        
        for (int i = 0; i < 100; i++) {
            final int number = i;
            pool.submitTask(new Runnable() {

                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+ " task number " + number);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        return;
                    }
                }
            });
            
        }
        pool.shutdown();
            System.out.println("all");
    }
}
