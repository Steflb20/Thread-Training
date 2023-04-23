package at.htlkaindorf.primes_sum;

import java.util.concurrent.Callable;

public class PrimesWorker implements Callable<Long> {

    private final int index;

    public PrimesWorker(int index) {
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
    public Long call() throws Exception {
        int start = 100_000 * this.index;
        int end = 100_000 * this.index + 99_999;

        long sum = 0;

        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }

        return sum;
    }
}
