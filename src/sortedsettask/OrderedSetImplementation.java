package sortedsettask;

import java.util.LinkedList;

public class OrderedSetImplementation<T extends Comparable<T>> {
    private final LinkedList<T> orderedSet = new LinkedList<>();

    public boolean add(T elem) {
        int index = getIndex(elem);
        if (index == -1)
            return false;
        orderedSet.add(index, elem);
        return true;
    }

    public boolean remove(T elem) {
        return orderedSet.remove(elem);
    }

    public boolean contains(T elem) {
        return orderedSet.contains(elem);
    }

    public int getSize() {
        return orderedSet.size();
    }

    public void print() {
        for (T elem : orderedSet) {
            System.out.println(elem);
        }
        System.out.println();
    }

    private int getIndex(T elem) {
        int index = 0;
        while (index < orderedSet.size()) {
            if (elem.compareTo(orderedSet.get(index)) > 0) {
                ++index;
            } else if (elem.compareTo(orderedSet.get(index)) == 0) {
                return -1;
            } else {
                break;
            }
        }
        return index;
    }
}
