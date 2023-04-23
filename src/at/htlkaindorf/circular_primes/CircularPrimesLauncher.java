package at.htlkaindorf.circular_primes;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class CircularPrimesLauncher {

    private String buildOutput(List<Integer> list) {
        StringBuilder builder = new StringBuilder(
                String.format("numbers[%7d;%7d]: ", list.get(0), list.get(1))
        );

        for (int i = 2; i < list.size(); i++) {
            if (i == list.size() - 1) {
                builder.append(list.get(i));
            } else {
                builder.append(list.get(i)).append(", ");
            }
        }

        return builder.toString();
    }

    public void launch() {
        Scanner scanner = new Scanner(System.in);
        int nThreads;
        int valuesPerWorker;

        System.out.print("Number of values per thread: ");
        valuesPerWorker = scanner.nextInt();

        System.out.print("Number of threads for pool: ");
        nThreads = scanner.nextInt();

        ExecutorService pool = Executors.newFixedThreadPool(nThreads);

        ExecutorCompletionService<List<Integer>> service = new ExecutorCompletionService<>(pool);

        int last = -1;

        for (int i = 0; i < 1_000_000 / valuesPerWorker; i++) {
            service.submit(
                    new CircularPrimesWorker(
                            valuesPerWorker * i + 2,
                            valuesPerWorker * i + valuesPerWorker + 1
                    )
            );

            last = valuesPerWorker * i + valuesPerWorker;
        }

        service.submit(
                new CircularPrimesWorker(
                        last,
                        1_000_000
                )
        );

        pool.shutdown();

        for (int i = 0; i < 1_000_000 / valuesPerWorker + 1; i++) {
            try {
                Future<List<Integer>> fList = service.take();
                List<Integer> list = fList.get();
                System.out.println(buildOutput(list));
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        CircularPrimesLauncher launcher = new CircularPrimesLauncher();
        launcher.launch();
    }
}
