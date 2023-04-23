package at.htlkaindorf.invokeany_invokeall;

import java.util.concurrent.Callable;

public class Worker implements Callable<Integer> {

    private int number;

    public Worker(int number) {
        this.number = number;
    }

    private int fac(int n) {
        return n == 2 ? 2 : n * fac(n - 1);
    }

    @Override
    public Integer call() throws Exception {
        // 5! -> 5 * 4 * 3 * 2 * 1
        return fac(this.number);
    }
}
