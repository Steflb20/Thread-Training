package at.htlkaindorf.chef_waiter_problem;

import java.util.Random;

// Consumer
public class Waiter implements Runnable {

    private final OrderList orderList;
    private final int count;

    public Waiter(OrderList orderList, int count) {
        this.orderList = orderList;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("Waiter started...");

        for (int i = 0; i < this.count; i++) {
            synchronized (this.orderList) {
                while (this.orderList.isEmpty()) {
                    try {
                        System.out.println("Waiter has to wait...");
                        this.orderList.wait();
                        System.out.println("Waiter stopped waiting");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                this.orderList.deQueue();
                System.out.println("Waiter took element...");
                this.orderList.notifyAll();
            }

            try {
                Thread.sleep(new Random().nextInt(1000) + 1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Waiter finished...");
    }
}
