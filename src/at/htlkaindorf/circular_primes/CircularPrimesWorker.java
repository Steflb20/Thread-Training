package at.htlkaindorf.circular_primes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class CircularPrimesWorker implements Callable<List<Integer>> {

    private int start;
    private int end;

    public CircularPrimesWorker(int start, int end) {
        this.start = start;
        this.end = end;
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

    private List<String> getMutations(int n) {
        List<String> mutations = new ArrayList<>();
        String base = String.valueOf(n);

        for (int i = 0; i < base.length(); i++) {
            mutations.add(base);
            base = base.substring(1) + base.charAt(0);
        }

        return mutations;
    }

    private boolean checkMutations(int n) {
        List<String> mutations = getMutations(n);

        for (String m : mutations) {
            if (!isPrime(Integer.parseInt(m))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public List<Integer> call() throws Exception {
        List<Integer> values = new ArrayList<>();

        System.out.printf("%d - %d !\n", start, end);

        values.add(this.start);
        values.add(this.end);

        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                if (checkMutations(i)) {
                    values.add(i);
                }
            }
        }

        return values;
    }
}
