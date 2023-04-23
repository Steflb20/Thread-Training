package at.htlkaindorf.chef_waiter_problem;

import at.htlkaindorf.chef_Chef_problem.Chef;

public class Restaurant {
    public static void main(String[] args) {
        OrderList orderList = new OrderList(10);

        Waiter waiter = new Waiter(orderList, 20);
        Chef chef = new Chef(orderList, 20);

        new Thread(chef).start();
        new Thread(waiter).start();
    }
}
