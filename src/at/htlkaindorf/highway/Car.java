package at.htlkaindorf.highway;

import java.util.Random;

public class Car implements Runnable{

    private final Toilbooth toilbooth;
    private Random rand;

    public Car(Toilbooth toilbooth) {
        this.toilbooth = toilbooth;
        this.rand = new Random();
    }

    @Override
    public void run() {
        int index;

        synchronized (this.toilbooth) {
            while (this.toilbooth.searchForFreeBox() == -1) {;
                try {
                    System.out.println("All boxes are occupied");
                    System.out.printf("%s has to wait \n", Thread.currentThread().getName());
                    this.toilbooth.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            index = this.toilbooth.searchForFreeBox();

            System.out.printf("Box %d is free\n", index + 1);

            double money = (new Random().nextInt(1500 - 500 + 100) + 500) / 100.0;
            System.out.printf("Box %d is occupied - %.2f by %s\n", index + 1, money, Thread.currentThread().getName());

            this.toilbooth.setStatusOfBox(index, false);
        }

        try {
            Thread.sleep(rand.nextInt(250 - 50 + 1) + 50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synchronized (this.toilbooth) {
            System.out.printf("Box %d is free\n", index + 1);
            this.toilbooth.setStatusOfBox(index, true);
            this.toilbooth.notifyAll();
        }

        System.out.printf("%s has finished!\n", Thread.currentThread().getName());
    }
}
