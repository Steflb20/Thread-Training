package at.htlkaindorf.executorservice_worker;

import java.util.concurrent.*;

public class Launcher {
    public void launch() {
        ExecutorService pool = Executors.newFixedThreadPool(6);
        ExecutorCompletionService<Integer> service = new ExecutorCompletionService<>(pool);

        for (int i = 0; i < 10; i++) {
            service.submit(new Worker(i));
        }

        pool.shutdown();

        int count = 0;

        for (int i = 0; i < 10; i++) {
            try {
                Future<Integer> future = null;
                future = service.take();
                int res = future.get();
                count += res;
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Primes: " + count);
    }

    public static void main(String[] args) {
        new Launcher().launch();
    }
}
