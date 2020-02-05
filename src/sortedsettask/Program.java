package sortedsettask;

public class Program {
    public static void main(String[] args) {
        testCoffee();
        testStudents();
        testGlasses();
    }

    private static void testGlasses() {
        RBTreeImplementation<Glasses> rbTree = new RBTreeImplementation<>(false);
        rbTree.add(new Glasses("Rectangle"));
        rbTree.add(new Glasses("Round"));
        rbTree.add(new Glasses("Oval"));
        rbTree.add(new Glasses("Browline"));
        rbTree.add(new Glasses("Square"));
        rbTree.add(new Glasses("Aviator"));

        rbTree.print();
    }

    private static void testCoffee() {
        RBTreeImplementation<Coffee> rbTree = new RBTreeImplementation<>(false);
        rbTree.add(new Coffee("Americano"));
        rbTree.add(new Coffee("Espresso"));
        rbTree.add(new Coffee("Latte"));
        rbTree.add(new Coffee("Cappuccino"));
        rbTree.add(new Coffee("Glace"));

        rbTree.print();
    }

    private static void testStudents() {
        RBTreeImplementation<Student> rbTree = new RBTreeImplementation<>(false);
        rbTree.add(new Student("Armen", "Sargsyan"));
        rbTree.add(new Student("Narek", "Gasparyan"));
        rbTree.add(new Student("Marine", "Gevorgyan"));
        rbTree.add(new Student("Sahak", "Gevorgyan"));

        rbTree.print();
    }
}
