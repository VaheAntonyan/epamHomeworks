package comporator;

import java.util.Comparator;

public class AscendingNameAndDescendingAgeComparator implements Comparator<Student> {

    @Override
    public int compare(Student student1, Student student2) {
        int NameCompare = student1.getFirstName().compareTo(student2.getFirstName());
        int AgeCompare = student2.getAge().compareTo(student1.getAge());

        return NameCompare == 0 ? AgeCompare : NameCompare;
    }
}
