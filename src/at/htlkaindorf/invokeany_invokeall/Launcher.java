package at.htlkaindorf.invokeany_invokeall;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Launcher {

    public void launch() {
        ExecutorService pool = Executors.newFixedThreadPool(6);

        List<Worker> workers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            workers.add(new Worker(i + 5));
        }

        // Execute all and expect every result
        try {
            List<Future<Integer>> futureList = pool.invokeAll(workers);
            for (Future<Integer> f : futureList) {
                System.out.println(f.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("------------------------------");

        Map<Integer, Integer> map = new TreeMap<>();

        // Execute all and expect a single result (the first one wins!)
        for (int i = 0; i < 100_000; i++) {
            try {
                int value = pool.invokeAny(workers);

                if (!map.containsKey(value)) {
                    map.put(value, 1);
                } else {
                    map.put(value, map.get(value) + 1);
                }

            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        pool.shutdown();

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.printf("%d -> %d\n", e.getKey(), e.getValue());
        }
    }

    public static void main(String[] args) {
        new Launcher().launch();
    }
}
