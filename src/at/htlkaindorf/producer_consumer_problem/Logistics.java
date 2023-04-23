package at.htlkaindorf.producer_consumer_problem;

public class Logistics {
    public static void main(String[] args) {
        MyStack stack = new MyStack(10);

        Producer producer = new Producer(stack);
        Consumer consumer = new Consumer(stack);

        new Thread(producer, "Producer").start();
        new Thread(consumer, "Consumer").start();
    }
}
