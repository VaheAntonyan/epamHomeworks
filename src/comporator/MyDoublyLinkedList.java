package comporator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<T> implements Iterable<T>{
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        T data;
        Node next;
        Node prev;

        public Node(T data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    public void push(T data) {
        Node newHeadNode = new Node(data, head, null);
        if (head != null) {
            head.prev = newHeadNode;
        }
        head = newHeadNode;
        if (tail == null) {
            tail = newHeadNode;
        }
        ++size;
    }

    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node oldHeadNode = head;
        head = head.next;
        if (head != null)
            head.prev = null;
        --size;
        return oldHeadNode.data;
    }

    public boolean addLast(T data) {
        Node newTailNode = new Node(data, null, tail);
        if (tail != null) {
            tail.next = newTailNode;
        }
        tail = newTailNode;
        if (head == null) {
            head = newTailNode;
        }
        ++size;
        return true;
    }

    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node oldTailNode = tail;
        tail = tail.prev;
        tail.next = null;
        --size;
        return oldTailNode.data;
    }

    public int size() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node current = getHead();
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
