package mapAndEnumTask;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Program {
    public static void main(String[] args) {
        task1();
    }

    private static void task1() {
        List<Student> students = new LinkedList<>();
        initializeListOfStudents(students);
        task1a(students);
        task1b(students);
    }

    private static void task1b(List<Student> students) {
        HashMap<String, Integer> hashMap = getStudentsCountByFacultyMap(students);
        printFacultyAndStudentCount(hashMap);
    }

    private static void printFacultyAndStudentCount(HashMap<String, Integer> hashMap) {
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private static HashMap<String, Integer> getStudentsCountByFacultyMap(List<Student> students) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (Student st : students) {
            hashMap.put(st.getFaculty(), hashMap.containsKey(st.getFaculty()) ? hashMap.get(st.getFaculty()) + 1 : 1);
        }
        return hashMap;
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
        students.add(new Student("Diana", "Asatryan", 22, "0988111111", "Math"));
        students.add(new Student("Diana", "Asatryan", 32, "0922111111", "Physics"));
        students.add(new Student("Diana", "Asatryan", 21, "0938111111", "Philosophy"));
        students.add(new Student("Karen", "Balayan", 12, "097777777", "Math"));
        students.add(new Student("Karen", "Balayan", 23, "098888811", "Philosophy"));
        students.add(new Student("Elen", "Mirzoyan", 12, "093333333", "English"));
    }

    private static HashMap<Student, Integer> getStudentsMap(List<Student> students) {
        HashMap<Student, Integer> hashMap = new HashMap<>();
        for (Student st : students) {
            hashMap.put(st, hashMap.containsKey(st) ? hashMap.get(st) + 1 : 1);
        }
        return hashMap;
    }
}
