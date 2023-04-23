package at.htlkaindorf.datastructures;

import java.util.concurrent.Callable;

public class MyStack {
    private int[] stack;
    private int tos;

    public MyStack(int size) {
        this.stack = new int[size];
        this.tos = 0;
    }

    public boolean isEmpty() {
        return this.tos == 0;
    }

    public boolean isFull() {
        return this.tos == this.stack.length;
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

    public static void main(String[] args) {
        MyStack myStack = new MyStack(6);

        for (int i = 0; i < 6; i++) {
            myStack.push(i);
        }

        for (int i = 0; i < 7; i++) {
            System.out.println(myStack.pop());
        }
    }
}
