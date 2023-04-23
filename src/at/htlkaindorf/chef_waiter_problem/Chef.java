package at.htlkaindorf.chef_Chef_problem;

import at.htlkaindorf.chef_waiter_problem.OrderList;

import java.util.Random;

public class Chef implements Runnable {
    private final OrderList orderList;
    private final int count;

    public Chef(OrderList orderList, int count) {
        this.orderList = orderList;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("Chef started...");

        for (int i = 0; i < this.count; i++) {
            synchronized (this.orderList) {
                while (this.orderList.isFull()) {
                    try {
                        System.out.println("Chef has to wait...");
                        this.orderList.wait();
                        System.out.println("Chef stopped waiting");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                this.orderList.enQueue(new Random().nextInt(1000) + 1);
                System.out.println("Chef created element...");
                this.orderList.notifyAll();
            }

            try {
                Thread.sleep(new Random().nextInt(1000) + 1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Chef finished...");
    }
}
