package comporator;

import java.util.Arrays;

public class MyDynamicArray {
    private int capacity;
    private static final int CAPACITY = 10;
    private Student[] studentArray;
    private int last = -1;

    public MyDynamicArray() {
        this(CAPACITY);
    }

    public MyDynamicArray(int capacity) {
        this.capacity = capacity;
        studentArray = new Student[capacity];
    }

    private void doubleCapacity() {
        capacity *= 2;
        studentArray = Arrays.copyOf(studentArray, capacity);
    }

    public boolean add(Student s) {
        if (size() == capacity)
            doubleCapacity();
        studentArray[++last] = s;
        return true;
    }

    public boolean remove(Student s) {
        boolean succeeds = false;
        for (int i = 0; i <= last; ++i) {
            if (studentArray[i] == s) {
                while (i < last) {
                    studentArray[i] = studentArray[++i];
                }
                studentArray = Arrays.copyOf(studentArray, i);
                succeeds = true;
                --last;
                break;
            }
        }
        return succeeds;
    }

    public int size() {
        return (last + 1);
    }

    public Student get(int index) {
        Student st = null;
        if (index >= 0 && index <= studentArray.length)
            st = studentArray[index];
        return st;
    }
}
