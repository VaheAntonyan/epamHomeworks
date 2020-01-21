package comporator;

public class MyDoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Student student;
        Node next;
        Node prev;

        public Node(Student student, Node next, Node prev) {
            this.student = student;
            this.next = next;
            this.prev = prev;
        }
    }

    public void push(Student student) {
        Node newHeadNode = new Node(student, head, null);
        if (head != null) {
            head.prev = newHeadNode;
        }
        head = newHeadNode;
        if (tail == null) {
            tail = newHeadNode;
        }
        ++size;
    }

    public Student pop() {
        if (size == 0) {
            System.out.println("MyDoublyLinkedList is empty");
            return null;
        }
        Node oldFirstNode = head;
        head = head.next;
        if (head != null)
            head.prev = null;
        --size;
        return oldFirstNode.student;
    }

    public boolean addLast(Student student) {
        Node newTailNode = new Node(student, null, tail);
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

    public Student removeLast() {
        if (size == 0) {
            System.out.println("MyDoublyLinkedList is empty");
            return null;
        }
        Node oldTailNode = tail;
        tail = tail.prev;
        tail.next = null;
        --size;
        return oldTailNode.student;
    }

    public int size() {
        return size;
    }
}
