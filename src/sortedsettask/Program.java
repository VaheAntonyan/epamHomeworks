package sortedsettask;

public class Program {
    public static void main(String[] args) {
        testCoffee();
        testStudents();
        testGlasses();
    }

    private static void testGlasses() {
        OrderedSetImplementation<Glasses> orderedSet = new OrderedSetImplementation<>();
        orderedSet.add(new Glasses("Rectangle"));
        orderedSet.add(new Glasses("Round"));
        orderedSet.add(new Glasses("Oval"));
        orderedSet.add(new Glasses("Browline"));
        orderedSet.add(new Glasses("Square"));
        orderedSet.add(new Glasses("Aviator"));

        orderedSet.print();
    }

    private static void testCoffee() {
        OrderedSetImplementation<Coffee> orderedSet = new OrderedSetImplementation<>();
        orderedSet.add(new Coffee("Americano"));
        orderedSet.add(new Coffee("Espresso"));
        orderedSet.add(new Coffee("Latte"));
        orderedSet.add(new Coffee("Cappuccino"));
        orderedSet.add(new Coffee("Glace"));

        orderedSet.print();
    }

    private static void testStudents() {
        OrderedSetImplementation<Student> orderedSet = new OrderedSetImplementation<>();
        orderedSet.add(new Student("Vahe", "Antonyan"));
        orderedSet.add(new Student("Arman", "Antonyan"));
        orderedSet.add(new Student("Arthur", "Antonyan"));
        orderedSet.add(new Student("Alvard", "Ayvazyan"));

        orderedSet.print();
    }
}
