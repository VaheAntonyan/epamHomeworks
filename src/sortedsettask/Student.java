package sortedsettask;

public class Student implements Comparable<Student> {
    String firstName;
    String lastName;

    public Student(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student student) {
        int lastNameCompare = this.lastName.compareTo(student.lastName);
        int firstNameCompare = this.firstName.compareTo(student.firstName);
        return lastNameCompare != 0 ? lastNameCompare : firstNameCompare;
    }
}
