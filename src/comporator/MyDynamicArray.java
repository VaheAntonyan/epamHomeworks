package comporator;

import java.util.Arrays;

public class MyDynamicArray<T> {
    private int capacity;
    private static final int CAPACITY = 10;
    private T[] myDynamicArray;
    private int last = -1;

    public MyDynamicArray() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyDynamicArray(int capacity) {
        this.capacity = capacity;
        myDynamicArray = (T[])new Object[capacity];
    }

    private void doubleCapacity() {
        capacity *= 2;
        myDynamicArray = Arrays.copyOf(myDynamicArray, capacity);
    }

    public void add(T s) {
        if (size() == capacity)
            doubleCapacity();
        myDynamicArray[++last] = s;
    }

    public boolean remove(T s) {
        boolean succeeds = false;
        for (int i = 0; i <= last; ++i) {
            if (myDynamicArray[i] == s) {
                while (i < last) {
                    myDynamicArray[i] = myDynamicArray[++i];
                }
                myDynamicArray[--last] = null;
                succeeds = true;
                break;
            }
        }
        return succeeds;
    }

    public int size() {
        return (last + 1);
    }

    public T get(int index) {
        T st = null;
        if (index >= 0 && index <= myDynamicArray.length)
            st = myDynamicArray[index];
        return st;
    }
}
