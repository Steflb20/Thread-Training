package at.htlkaindorf.trunctable_primes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TruncPrimesLauncher {

    private String getLineString(List<Integer> list) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("[%4d;%5d] -> ", list.get(0), list.get(1)));

        for (int i = 2; i < list.size(); i++) {
            builder.append(list.get(i)).append(" ");
        }

        return builder.toString();
    }

    private void launch() {
        Scanner scanner = new Scanner(System.in);
        int values;
        int threads;

        System.out.println("The value range [10; 1000] will be checked.");

        do {
            System.out.print("Input the number of values for each worker: ");
            values = scanner.nextInt();
        } while (values > 990 || values <= 0);

        do {
            System.out.print("Input the number of threads for the pool (2 ... 8): ");
            threads = scanner.nextInt();
        } while (threads < 2 || threads > 8);

        ExecutorService pool = Executors.newFixedThreadPool(threads);

        int last = -1;

        List<TruncPrimesWorker> workers = new ArrayList<>();
        for (int i = 0; i < 990 / values; i++) {
            workers.add(
                    new TruncPrimesWorker(
                            values * i + 10,
                            values * i + 10 + values - 1
                    )
            );
            last = values * i + 10 + values - 1;
        }

        if (last != 1000) {
            workers.add(
                    new TruncPrimesWorker(
                            last,
                            1000
                    )
            );
        }

        try {
            List<Future<List<Integer>>> futureLists = pool.invokeAll(workers);
            for (Future<List<Integer>> fl : futureLists) {
                List<Integer> list = fl.get();
                System.out.println(getLineString(list));
            }
            pool.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new TruncPrimesLauncher().launch();
    }
}