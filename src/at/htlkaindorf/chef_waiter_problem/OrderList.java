package at.htlkaindorf.chef_waiter_problem;

public class OrderList {
    private int[] queue;
    private int front;
    private int back;

    public OrderList(int size) {
        this.queue = new int[size];
        this.front = -1;
        this.back = -1;
    }

    public void enQueue(int value) {
        if (isFull()) {
            throw new RuntimeException("Queue is full!");
        }

        if (this.front == -1) {
            this.front = 0;
        }

        this.queue[++this.back] = value;
    }

    public int deQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }

        int element = this.queue[this.front];

        if (this.front >= this.back) {
            this.front = -1;
            this.back = -1;
        } else {
            this.front++;
        }

        return element;
    }

    public boolean isFull() {
        return this.front == 0 && this.back == this.queue.length - 1;
    }

    public boolean isEmpty() {
        return this.front == -1;
    }
}
