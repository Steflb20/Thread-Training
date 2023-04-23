package at.htlkaindorf.producer_consumer_problem;

public class MyStack {
    private int[] stack;
    private int tos;

    public MyStack(int size) {
        this.stack = new int[size];
        this.tos = 0;
    }

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("Stack is full!");
        }

        this.stack[this.tos++] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }

        return this.stack[--this.tos];
    }

    public boolean isFull() {
        return this.tos == this.stack.length;
    }

    public boolean isEmpty() {
        return this.tos == 0;
    }

}
