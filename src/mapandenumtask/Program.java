package mapandenumtask;

import mapandenumtask.myexceptions.DayException;
import mapandenumtask.myexceptions.Faculty;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        task1();
        task2();
    }

    private static void task2() {
        //holidays of April
        System.out.printf("Holidays of April: %s%n", Month.APRIL.getHolidays());

        //days count of March
        System.out.printf("Days count of March: %d%n", Month.MARCH.getDaysCount());

        //traverse and print all month's names.
        traverseAndPrintMonthNames();

        //check if March 21 and March 8 are public holidays
        try {
            printPublicHoliday(Month.MARCH, 21);
        } catch (DayException e) {
            e.printStackTrace();
        }
        try {
            printPublicHoliday(Month.MARCH, 8);
        } catch (DayException e) {
            e.printStackTrace();
        }
    }

    private static void printPublicHoliday(Month month, int day) throws DayException {
        System.out.printf("Is %s %d a public holiday : %b%n", month, day, Month.isPublicHoliday(month, day));
    }

    private static void traverseAndPrintMonthNames() {
        for (Month month : Month.values()) {
            System.out.println(month);
        }
    }

    private static void task1() {
        List<Student> students = new LinkedList<>();
        initializeListOfStudents(students);
        task1a(students);
        task1b(students);
    }

    private static void task1b(List<Student> students) {
        EnumMap<Faculty, Integer> enumMap = getStudentsCountByFacultyMap(students);
        printFacultyAndStudentCount(enumMap);
    }

    private static void printFacultyAndStudentCount(EnumMap<Faculty, Integer> enumMap) {
        for (Map.Entry<Faculty, Integer> entry : enumMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private static EnumMap<Faculty, Integer> getStudentsCountByFacultyMap(List<Student> students) {
        EnumMap<Faculty, Integer> enumMap = new EnumMap<Faculty, Integer>(Faculty.class);
        for (Student st : students) {
            enumMap.put(st.getFaculty(), enumMap.containsKey(st.getFaculty()) ? enumMap.get(st.getFaculty()) + 1 : 1);
        }
        return enumMap;
    }

    private static void task1a(List<Student> students) {
        HashMap<Student, Integer> hashMap = getStudentsMap(students);
        printStudentFullNameAndCount(hashMap);
    }

    private static void printStudentFullNameAndCount(HashMap<Student, Integer> hashMap) {
        for (Map.Entry<Student, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey().getFirstName() + " " + entry.getKey().getLastName() + " " + entry.getValue());
        }
    }

    private static void initializeListOfStudents(List<Student> students) {
        students.add(new Student("Diana", "Asatryan", 22, "0988111111", Faculty.MATH));
        students.add(new Student("Diana", "Asatryan", 32, "0922111111", Faculty.PHYSICS));
        students.add(new Student("Diana", "Asatryan", 21, "0938111111", Faculty.PHILOSOPHY));
        students.add(new Student("Karen", "Balayan", 12, "097777777", Faculty.MATH));
        students.add(new Student("Karen", "Balayan", 23, "098888811", Faculty.PHILOSOPHY));
        students.add(new Student("Elen", "Mirzoyan", 12, "093333333", Faculty.ENGLISH));
    }

    private static HashMap<Student, Integer> getStudentsMap(List<Student> students) {
        HashMap<Student, Integer> hashMap = new HashMap<>();
        for (Student st : students) {
            hashMap.put(st, hashMap.containsKey(st) ? hashMap.get(st) + 1 : 1);
        }
        return hashMap;
    }
}
