package at.htlkaindorf.trunctable_primes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class TruncPrimesWorker implements Callable<List<Integer>> {

    private int start;
    private int end;

    public TruncPrimesWorker(int start, int end) {
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

    private boolean checkLeftToRight(int number) {
        String strNumber = String.valueOf(number);

        for (int i = 0; i < strNumber.length(); i++) {
            String newStrNumber = strNumber.substring(i);

            if (!isPrime(Integer.parseInt(newStrNumber))) {
                return false;
            }
        }

        return true;
    }

    private boolean checkRightToLeft(int number) {
        String strNumber = String.valueOf(number);

        for (int i = strNumber.length(); i >= 1; i--) {
            String newStrNumber = strNumber.substring(0, i);

            if (!isPrime(Integer.parseInt(newStrNumber))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public List<Integer> call() throws Exception {
        List<Integer> list = new ArrayList<>();

        list.add(this.start);
        list.add(this.end);

        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                if (checkLeftToRight(i) && checkRightToLeft(i)) {
                    list.add(i);
                }
            }
        }

        return list;
    }
}
