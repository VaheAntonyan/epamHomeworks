package comporator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Program {
    public static void main(String[] args) {
        task1();
        task2();
        task5();
    }

    private static void task5() {
        System.out.println("\n\nTask 5");
        MyCollectionsHelper h = new MyCollectionsHelper();
        h.initialize();
        h.printInfo();
    }

    private static void task2() {
        System.out.println("\n\nTask 2");
        Student s1 = new Student("Arman", "Karapetyan", 18);
        Student s2 = new Student("Arman", "Hakobyan", 20);
        Student s3 = new Student("Karen", "Martirosyan", 22);
        Student s4 = new Student("Hayk", "Andreasyan", 20);

        LinkedList<Student> ll = new LinkedList<>();
        ll.add(s1);
        ll.add(s2);
        ll.add(s3);
        ll.add(s4);

        System.out.println("\nUnsorted");
        for (Student student : ll) {
            System.out.println(student);
        }

        System.out.println("\nSorted");
        Collections.sort(ll);
        for (Student student : ll) {
            System.out.println(student);
        }
    }

    private static void task1() {
        System.out.println("\n\nTask 1");
        Student s1 = new Student("Arman", "Karapetyan", 18);
        Student s2 = new Student("Arman", "Hakobyan", 20);
        Student s3 = new Student("Karen", "Martirosyan", 22);
        Student s4 = new Student("Hayk", "Andreasyan", 20);

        ArrayList<Student> al = new ArrayList<>();
        al.add(s1);
        al.add(s2);
        al.add(s3);
        al.add(s4);

        System.out.println("\nUnsorted");
        for (Student student : al) {
            System.out.println(student);
        }

        System.out.println("\nSorted");
        Collections.sort(al, new AscendingNameAndDescendingAgeComparator());
        for (Student student : al) {
            System.out.println(student);
        }
    }
}
