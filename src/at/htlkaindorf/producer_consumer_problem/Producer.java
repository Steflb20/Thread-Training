package at.htlkaindorf.producer_consumer_problem;

import java.util.Random;

public class Producer implements Runnable {
    private final MyStack stack;

    public Producer(MyStack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        System.out.printf("%s joined!\n", Thread.currentThread().getName());

        for (int i = 0; i < 20; i++) {
            synchronized (this.stack) {
                while (this.stack.isFull()) {
                    try {
                        System.out.printf("%s is waiting!\n", Thread.currentThread().getName());
                        this.stack.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.printf("%s added a value!\n", Thread.currentThread().getName());
                this.stack.push(new Random().nextInt(100) + 1);
                this.stack.notify();
            }

            try {
                Thread.sleep(new Random().nextInt(1000) + 1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
