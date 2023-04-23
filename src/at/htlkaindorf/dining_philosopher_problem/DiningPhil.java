package at.htlkaindorf.dining_philosopher_problem;

import java.util.Random;

public class DiningPhil implements Runnable {
    private int number;
    private final boolean[] forks;

    public DiningPhil(int number, boolean[] forks) {
        this.number = number;
        this.forks = forks;
    }

    @Override
    public void run() {
        int forkCount = 0;

        int iLeft = this.number;
        int iRight = this.number == 4 ? 0 : this.number + 1;

        System.out.printf("Phil %d started...\n", this.number);
        // System.out.printf("Phil %d chose following indices: %d - %d\n", this.number, iLeft, iRight);

        for (int i = 0; i < 20; i++) {
            synchronized (this.forks) {
                while (this.forks[iLeft]) {
                    try {
                        System.out.printf("Phil %d is waiting...\n", this.number);
                        this.forks.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.printf("Phil %d takes left fork...\n", this.number);
                forkCount++;
                this.forks[iLeft] = true;

                while (this.forks[iRight]) {
                    try {
                        this.forks.wait();
                        System.out.printf("Phil %d is waiting...\n", this.number);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.printf("Phil %d takes right fork...\n", this.number);
                forkCount++;
                this.forks[iRight] = true;
            }

            if (forkCount == 2) {
                System.out.printf("Phil %d is dining...\n", this.number, i);

                // Eat for 500ms
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.printf("Phil %d finished dining and returns forks...\n", this.number, i);

                // Release Forks
                forkCount = 0;

                synchronized (this.forks) {
                    this.forks[iLeft] = false;
                    this.forks[iRight] = false;
                    this.forks.notifyAll();
                }

                try {
                    Thread.sleep(new Random().nextInt(1000) + 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.printf("Phil %d finished...\n", this.number);
    }
}
