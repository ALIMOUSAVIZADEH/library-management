package datastructures.lists;
import datastructures.interfaces.LinkedList;

import java.util.NoSuchElementException;

public class CustomLinkedList<T> implements LinkedList<T>{
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head, tail;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0) ? true : false;
    }

    @Override
    public boolean add(Object t) {
        if (t == null)
            throw new NullPointerException();

        if (head == null) {
            head = new Node<>((T) t);
            tail = head;

            size++;

            return true;
        }

        tail.next = new Node<>((T) t);
        tail.next.prev = tail;
        tail = tail.next;

        size++;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        if (head == null)
            return false;

        Node<T> node = head;
        while (node.next != null) {
            if (node == o)
                return true;

            node = node.next;
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null)
            throw new NullPointerException();

        if (head == null)
            return false;

        Node<T> node = head;
        while (node.next != null) {
            if (node == o) {
                size--;
                return true;
            }

            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        return false;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        if (index == 0)
            return head.data;

        Node<T> node = head;

        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.data;
    }

    @Override
    public T set(int index, Object element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        if (index == size) {
            size++;
        }

        if (index == 0) {
            Node<T> node = new Node<>((T) element);

            node.next = head.next;
            head = node;

            return head.data;
        }

        Node<T> node = head;

        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        Node<T> newNode = new Node<>((T) element);
        newNode.next = node.next;
        newNode.prev = node.prev;

        node.next.prev = newNode;
        node.prev.next = newNode;

        return node.data;
    }

    @Override
    public void addFirst(T t) {
        Node<T> node = new Node<>(t);

        if (head == null) {
            head = node;
            tail = head;

            size++;

        } else {
            node.next = head;
            head = node;

            size++;
        }
    }

    @Override
    public void addLast(T t) {
        Node<T> node = new Node<>(t);

        if (head == null) {
            head = node;
            tail = head;

            size++;

        } else {
            tail.next = node;
            tail.next.prev = tail;
            tail = node;

            size++;
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        T data = head.data;

        if (head == tail) {
            head = tail = null;

        } else {
            head = head.next;
            head.prev = null;
        }

        size--;
        return data;
    }

    @Override
    public T removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        T data = tail.data;

        if (head == tail) {
            head = tail = null;

        } else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;
        return data;
    }

    @Override
    public T getFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        return head.data;
    }

    @Override
    public T getLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        return tail.data;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }
}