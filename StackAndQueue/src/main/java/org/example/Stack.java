package org.example;
import org.example.stack.Dynamic;
import org.example.stack.Fixed;
import org.example.stack.State;

import java.util.Arrays;

public class Stack<E> {
    private int size;
    private E[] stack;
    private final State state;
//    private final Class<E> stackClass;
    private Stack<E> newStack;
    private int top = -1;

    // Overloading to create either dynamic stack or none-dynamic stack
    // TODO with generic no need to re-check about class type
    public Stack(){
        this.size = 0;
        this.stack =(E[]) new Object[this.size];
        this.state = new Dynamic();
    }
    public Stack(int size){
        // TODO check size if negative or zero
        this.stack =(E[]) new Object[size];
        this.size = size;
        this.state = new Fixed();
    }

    // fixed
    public void push(E value){
        checkValueType(value);
        if(state instanceof Dynamic){ // for fixed Stack isDynamic equal null (variable not initialize)
            pushDynamic(value);
//            System.out.println(this.printStack());
        } else {
            // TODO lets say Stack size = 5 and it have 5 elements, then when try to push element number 6 must be return exception (out of bounds)
            this.stack[++top] = value;
        }
    }

    public E[] pop(){
        checkEmptyStack();
        newStack = new Stack<>(size-1);
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
        checkEmptyStack(); // TODO getSize no need to check if Empty
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
        newStack = new Stack<>(this.size+1);
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

    private E[] getElements() {
        return stack;
    }
//
    private boolean isEmpty(){
        return (this.top == -1);
    }

    private void checkValueType(E value) {
        // TODO as expected from checkValueType -> to check value datatype to be match as generic type or to match as stackClass variable
        //  ex.(value to be from Integer type, value to be from String type)
        if(value == null){
            throw new IllegalArgumentException("argument needs to be of type" );
        }
    }

    private void checkEmptyStack() {
        if(isEmpty()){
            throw new NegativeArraySizeException("Stack is empty, push values in stack to get results.");
        }
    }

}
