package org.example.stack;

import java.util.NoSuchElementException;

public class newImpl {

    int size, capacity;
    String[] stack;
    boolean isDynamic;

    public newImpl(int capacity, boolean isDynamic) {
        validateCapacity(capacity);
        this.capacity = capacity;
        this.size = 0;
        this.stack = new String[this.capacity];
        this.isDynamic = false;
    }

    public newImpl() {
        this.capacity = 1;
        this.size = 0;
        this.stack = new String[this.capacity];
        this.isDynamic = true;
    }

    public void push(String value) {
        validateValue(value);
        validateFullStackThenDoResize();
        this.stack[this.size++] = value;
    }

    public String pop(){
        String temp = this.peek();
        this.stack[--this.size] = null;
        if(isDynamic && this.capacity > 0){
            resize(--this.capacity);
        }
        return temp;
    }

    public int getSize() {
        return this.size;
    }

    public String peek(){
        validateIfEmpty();
        return this.stack[this.size-1];
    }

    private static void validateCapacity(int capacity) {
        if(capacity < 1){
            throw new IllegalArgumentException("Capacity needs to be greater than or equal to 1");
        }
    }

    private boolean isEmpty(){
        return this.size == 0;
    }

    private void validateFullStackThenDoResize() {
        if (isStackFull()) {
            if (!isDynamic)
                throw new IllegalArgumentException("Stack is Full");
            resize(this.capacity *2);
        }
    }

    private void validateValue(String value) {
        if(value == null){
            throw new IllegalArgumentException("Cannot push a null value into stack.");
        }
    }

    private void resize(int newSize) {
        this.stack = copyElements(newSize);
        this.capacity = newSize;
    }

    private String[] copyElements(int newSize) {
            String[] temp = new String[newSize];
            for (int i = 0; i < this.size; i++){
                temp[i] = this.stack[i];
            }
            return temp;
    }

    private boolean isStackFull() {
        return this.size == this.capacity;
    }

    private void validateIfEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty, there is no element at the top of the stack.");
        }
    }


}
