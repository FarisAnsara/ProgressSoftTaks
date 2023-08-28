package org.example.stack;

import java.util.NoSuchElementException;

public class NewStackImpl {
    private int size, capacity;
    private String stack[];
    private boolean isDynamic;

    //To Create Fixed Stack
    public NewStackImpl(int capacity) {
        if (isNonPositiveCapacity(capacity)) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.size = 0;
        this.stack = new String[this.capacity];
        this.isDynamic = false;
    }

    //To Create Dynamic Stack
    public NewStackImpl() {
        this.capacity = 1;
        this.size = 0;
        this.stack = new String[this.capacity];
        this.isDynamic = true;
    }

    public void push(String value) {
        if (value == null) {
            throw new NullPointerException("null value cannot be pushed to  stack");
        }
        if (isFullStack()) {
            if (!this.isDynamic)
                throw new IllegalArgumentException("Stack is full");
            resize(this.capacity + 3);
        }
        this.stack[this.size++] = value;
    }

    public String pop() {
        String temp = peek();
        this.stack[--this.size] = null;
        if (isDoShrink())
            resize(this.capacity - 2);
        return temp;
    }

    public String peek() {
        if (isEmpty())
            throw new NoSuchElementException("Empty Stack");
        return this.stack[this.size-1];
    }

    private boolean isEmpty() {
        return this.size == 0;
    }
    private boolean isDoShrink(){
        return this.isDynamic && this.size > 0 && this.size == this.capacity - 3;
    }
    private boolean isFullStack() {
        return this.size == capacity;
    }

    private void resize(int newSize) {
        this.stack = copyElements(newSize);
        this.capacity = newSize;
    }

    private String[] copyElements(int newSize) {
        String[] temp = new String[newSize];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.stack[i];
        }
        return temp;
    }

    private boolean isNonPositiveCapacity(int capacity) {
        return capacity <= 0;
    }
}
