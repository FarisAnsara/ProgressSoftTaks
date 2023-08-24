package org.example;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Stack<E> {
    private int size;
    private E[] stack;
    private boolean isDynamic;
    private final Class<E> stackClass;
    private Stack<E> newStack;
    private int top = -1;

    // Overloading to create either dynamic stack or none-dynamic stack
    public Stack(Class<E> clazz){
        this.size = 0;
        this.stack = (E[]) Array.newInstance(clazz, size);
        this.stackClass = clazz;
        this.isDynamic = true;
    }
    public Stack(Class<E> clazz, int size){
        this.stack = (E[]) Array.newInstance(clazz, size);
        this.size = size;
        this.stackClass = clazz;
    }

    // fixed
    public void push(E value){
        checkValueType(value);
        if(isDynamic){
            pushDynamic(value);
            System.out.println(this.printStack());
        } else {
            this.stack[++top] = value;
        }
    }

    public E[] pop(){
        checkEmptyStack();
        newStack = new Stack(stackClass, size-1);
        System.out.println("removed the last element: " + this.stack[top] + "\nnew stack is:");
        for (int i = 0; i < top + 1; i++){
            if(i == top){
                top -= 1;
                break;
            }
            newStack.push(stack[i]);
        }
        this.stack = newStack.getElements();
        return stack;
    }

    public int size(){
        checkEmptyStack();
        return this.top+1;
    }

    public E peek() {
        checkEmptyStack();
        return this.stack[top];
    }

    public String printStack(){
        return Arrays.toString(this.stack);
    }

    private void pushDynamic(E value){
        newStack = new Stack(stackClass, this.size+1);
        for (int i = 0; i <= top + 1; i++){
            if(top+1 == i){
                newStack.push(value);
                top++;
                break;
            }
            newStack.push(stack[i]);
        }
        this.stack = newStack.getElements();
        this.size += 1;
    }

    private E[] getElements(){
        return stack;
    }
//
    private boolean isEmpty(){
        return (this.top == -1);
    }

    private void checkValueType(E value) {
        if(value == null){
            throw new IllegalArgumentException("argument needs to be of type" + this.stackClass);
        }
    }

    private void checkEmptyStack() {
        if(isEmpty()){
            throw new NegativeArraySizeException("Stack is empty, push values in stack to get results.");
        }
    }

}
