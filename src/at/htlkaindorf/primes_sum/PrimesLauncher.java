package at.htlkaindorf.primes_sum;

import java.util.concurrent.*;

public class PrimesLauncher {
    public void launch() {
        ExecutorService pool = Executors.newFixedThreadPool(8);
        ExecutorCompletionService<Long> service = new ExecutorCompletionService<>(pool);

        for (int i = 0; i < 1_000_000 / 100_000; i++) {
            service.submit(new PrimesWorker(i));
        }

        pool.shutdown();

        long sum = 0;

        for (int i = 0; i < 1_000_000 / 100_000; i++) {
            try {
                Future<Long> value = service.take();
                long i64Value = value.get();
                sum += i64Value;
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Complete Sum: " + sum);
    }

    public static void main(String[] args) {
        new PrimesLauncher().launch();
    }
}
