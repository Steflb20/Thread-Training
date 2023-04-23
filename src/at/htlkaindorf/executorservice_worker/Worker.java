package at.htlkaindorf.executorservice_worker;

import java.util.concurrent.Callable;

public class Worker implements Callable<Integer> {
    private int index;

    public Worker(int index) {
        this.index = index;
    }

    private boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Integer call() throws Exception {

        System.out.printf("Worker %d started!\n", this.index);

        int start = 100_000 * this.index + 1;
        int end = 100_000 * this.index + 100_000;

        int count = 0;

        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                System.out.printf("Worker %d found %d as a prime number!\n", this.index, i);
                count++;
            }
        }

        System.out.printf("Worker %d finished with a count of %d prime number!\n", this.index, count);

        return count;
    }
}
