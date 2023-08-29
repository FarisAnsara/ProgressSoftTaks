package queue;

import org.example.exceptions.CapacityValueOutOfRangeException;
import org.example.exceptions.EnqueValueNullException;
import org.example.exceptions.QueueIsEmptyException;
import org.example.exceptions.QueueIsFullException;
import org.example.stack.Dynamic;
import org.example.stack.Fixed;
import org.example.stack.State;

public class QueueImpl<E> {
    private State state;
    private Object[] queue;
    private int capacity, size;

    public QueueImpl() {
        this.capacity = 0;
        this.queue = new Object[this.capacity];
        this.size = 0;
        this.state = new Dynamic();
    }

    public QueueImpl(int capacity) {
        validateCapacity(capacity);
        this.capacity = capacity;
        this.queue = new Object[capacity];
        this.size = 0;
        this.state = new Fixed();
    }

    public void enque(E value) {
        ifValueNullThenThrow(value);
        if (queueIsFull()) {
            if (state instanceof Fixed) {
                throw new QueueIsFullException("Queue is full, cannot enque more values");
            }
            resize(this.capacity + 1);
        }
        this.queue[this.size++] = value;
    }

    public E deque() {
        isEmptyThenThrow();
        E temp = (E) this.queue[0];
        this.queue = shiftQueueLeft();
        if(this.state instanceof Dynamic){
            resize(this.size);
        }
        return temp;
    }

    public int getSize() {
        return this.size;
    }

    public E peek() {
        return (E) this.queue[0];
    }

    public Object[] getQueue() {
        return this.queue;
    }

    private void validateCapacity(int capacity) {
        if (capacity < 1) {
            throw new CapacityValueOutOfRangeException ("Capacity value can only be equal or greater than 1");
        }
    }

    private boolean queueIsFull() {
        return this.size == this.capacity;
    }

    private void resize(int newSize) {
        this.queue = copyElements(newSize);
        this.capacity = newSize;
    }

    private Object[] copyElements(int newSize) {
        Object[] temp = new Object[newSize];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.queue[i];
        }
        return temp;
    }

    private Object[] shiftQueueLeft() {
        Object[] temp = new Object[this.capacity];
        for (int i = 1; i < this.size; i++){
            temp[i-1] = this.queue[i];
        }
        this.size--;
        return temp;
    }

    private void isEmptyThenThrow() {
        if(this.size == 0){
            throw new QueueIsEmptyException("Queue is empty, so cannot deque as no elements exist");
        }
    }

    private static <E> void ifValueNullThenThrow(E value) {
        if (value == null) {
            throw new EnqueValueNullException("Enque value cannot be null");
        }
    }



}
