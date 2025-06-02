package datastructures.queues;

import datastructures.interfaces.Queue;
import datastructures.lists.CustomLinkedList;

import java.util.NoSuchElementException;

public class CustomQueue<T> implements Queue<T> {
    private CustomLinkedList<T> list;
    private int capacity;

    public CustomQueue(int capacity) {
        list = new CustomLinkedList<>();
        setCapacity(capacity);
    }

    private void setCapacity(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity can not zero or negative.");

        this.capacity = capacity;
    }

    @Override
    public boolean add(Object t) {
        if (list.size() == capacity)
            throw new IllegalStateException();

        list.addFirst((T) t);
        return true;
    }

    @Override
    public boolean offer(Object t) {
        if (list.size() == capacity)
            return false;

        list.addFirst((T) t);
        return true;
    }

    @Override
    public T remove() {
        if (list.isEmpty())
            throw new NoSuchElementException();

        return list.removeLast();
    }

    @Override
    public T poll() {
        if (list.isEmpty())
            return null;

        return list.removeLast();
    }

    @Override
    public T element() {
        if (list.isEmpty())
            throw new NoSuchElementException();

        return list.getLast();
    }

    @Override
    public T peek() {
        if (list.isEmpty())
            return null;

        return list.getLast();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }
}