package comporator;

import java.util.Comparator;

public class AscendingNameAndDescendingAgeComparator implements Comparator<Student> {

    @Override
    public int compare(Student student1, Student student2) {
        int nameCompare = student1.getFirstName().compareTo(student2.getFirstName());
        int ageCompare = student2.getAge().compareTo(student1.getAge());

        return nameCompare == 0 ? ageCompare : nameCompare;
    }
}
