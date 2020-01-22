package comporator;

public class Helper {
    MyDynamicArray<Student> myDynamicArray;
    MyDoublyLinkedList<Student> myDoublyLinkedList;

    void initialize() {
        myDynamicArray = new MyDynamicArray<>();
        myDoublyLinkedList = new MyDoublyLinkedList<>();

        Student s1 = new Student("Arman", "Karapetyan", 18);
        Student s2 = new Student("Arman", "Hakobyan", 20);
        Student s3 = new Student("Karen", "Martirosyan", 22);
        Student s4 = new Student("Hayk", "Andreasyan", 20);

        myDynamicArray.add(s1);
        myDynamicArray.add(s2);
        myDynamicArray.add(s3);
        myDynamicArray.add(s4);

        myDoublyLinkedList.push(s1);
        myDoublyLinkedList.push(s2);
        myDoublyLinkedList.push(s3);
        myDoublyLinkedList.push(s4);
    }

    void printInfo() {
        printDynamicArrayInfo();
        printDoublyLinkedListInfo();
    }

    private void printDoublyLinkedListInfo() {
        System.out.println("\nStudents' first and last name in myDoublyLinkedList.");
        for (Student st: myDoublyLinkedList) {
            System.out.println(st.firstName + " " + st.lastName);
        }
    }

    private void printDynamicArrayInfo() {
        System.out.println("\nStudents' first and last name in myDynamicArray.");
        Student st;
        int size = myDynamicArray.size();
        for (int i = 0; i < size; i++) {
            st = myDynamicArray.get(i);
            System.out.println(st.firstName + " " + st.lastName);
        }
    }
}
