package university;

import university.myExceptions.GradeException;

import java.util.Random;

public class Main {
    static String universityName;
    static String[] facultyNames;
    static int facultyCount;
    static String[] groupNames;
    static int[] groupCount;
    static String[][] subjectsForGroup;
    static String[] subjectsAll;
    static int subjectsAllCount;

    static int[] studentCountInGroup;
    static int studentCountAll;
    static Student[] students;

    public static void initializing(){
        initializingUniversity();
        initializingFaculty();
        initializingGroup();
        initializingSubjects();
        initializingStudent();
    }

    public static void initializingUniversity(){
        universityName = "YSU";
    }

    public static void initializingFaculty(){
        facultyNames = new String[]{"Informatics and Applied Mathematics", "Mathematics and Mechanics"};
        facultyCount = facultyNames.length;
    }

    public static void initializingGroup(){
        groupNames = new String[]{"1501", "1502", "2501", "2502", "2503"};
        groupCount = new int[]{2, 3};
    }

    public static void initializingSubjects(){
        subjectsForGroup = new String[][]{
                {"Physics", "Philosophy", "History"},
                {"Math", "Physics", "Philosophy"},
                {"Math", "Physics", "Philosophy"},
                {"Math", "Physics"},
                {"Math", "Physics", "Philosophy", "History"}};
        subjectsAll = new String[]{"Physics", "Philosophy", "History", "Math"};
        subjectsAllCount = subjectsAll.length;
    }

    public static void initializingStudent(){
        studentCountInGroup = new int[]{3, 5, 7, 4, 4};
        studentCountAll = 23;
        students = new Student[studentCountAll];

        int studentIndex = 0;
        Random ran = new Random();
        for (int i = 0; i < facultyCount; ++i) {
            for (int j = 0; j < groupCount[i]; ++j) {
                Group group = new Group(universityName, facultyNames[i], groupNames[i * facultyCount + j], subjectsForGroup[i * facultyCount + j]);
                for (int k = 0; k < studentCountInGroup[i * facultyCount + j]; ++k, ++studentIndex) {
                    int gradeMaxCount = ran.nextInt(6) + 5;
                    try {
                        students[studentIndex] = new Student.StudentBuilder("Student " + (studentIndex + 1))
                                .group(group)
                                .grades(new GradeTable(subjectsForGroup[i * facultyCount + j], gradeMaxCount).addGrades())
                                .build();
                    } catch (GradeException gradeExeption) {
                        gradeExeption.printStackTrace();
                    }
                }
            }
        }
    }
    
    public static void printStudentsGrades(){
        for (int i = 0; i < studentCountAll; i++) {
            System.out.println(students[i].getStudentName());
            students[i].getGrades().printGrades();
            System.out.println();
        }
    }

    private static void calculateAndPrintMeanGradesForEverything() {
        String curFacultyName = students[0].getGroup().getFacultyName();
        String curGroupName = students[0].getGroup().getGroupName();
        int[][] currentSumAndCountOfGradesForStudent = new int[subjectsAllCount][2];
        int[][] currentSumAndCountOfGradesForGroup = new int[subjectsAllCount][2];
        int[][] currentSumAndCountOfGradesForFaculty = new int[subjectsAllCount][2];
        int[][] currentSumAndCountOfGradesForUniversity = new int[subjectsAllCount][2];
        int subjectIndex;

        for (int i = 0; i < studentCountAll; i++) {
            //group changed
            if (!curGroupName.equals(students[i].getGroup().getGroupName())) {
                calculateAndPrintMeanGrades(currentSumAndCountOfGradesForGroup, curGroupName);
                curGroupName = students[i].getGroup().getGroupName();
            }
            //faculty changed
            if (!curFacultyName.equals(students[i].getGroup().getFacultyName())) {
                calculateAndPrintMeanGrades(currentSumAndCountOfGradesForFaculty, curFacultyName);
                curFacultyName = students[i].getGroup().getFacultyName();
            }

            //Adding grades to tables
            for (int j = 0; j < students[i].getGroup().getSubjects().length; j++) {
                for (int k = 0; k < students[i].getGrades().gradeTable[j].length && students[i].getGrades().gradeTable[j][k] != -1; k++) {
                    for (subjectIndex = 0; subjectIndex < subjectsAllCount; ++subjectIndex) {
                        if (students[i].getGroup().getSubjects()[j].equals(subjectsAll[subjectIndex])) {
                            break;
                        }
                    }
                    currentSumAndCountOfGradesForStudent[subjectIndex][0] += students[i].getGrades().gradeTable[j][k];
                    ++currentSumAndCountOfGradesForStudent[subjectIndex][1];
                    currentSumAndCountOfGradesForGroup[subjectIndex][0] += students[i].getGrades().gradeTable[j][k];
                    ++currentSumAndCountOfGradesForGroup[subjectIndex][1];
                    currentSumAndCountOfGradesForFaculty[subjectIndex][0] += students[i].getGrades().gradeTable[j][k];
                    ++currentSumAndCountOfGradesForFaculty[subjectIndex][1];
                    currentSumAndCountOfGradesForUniversity[subjectIndex][0] += students[i].getGrades().gradeTable[j][k];
                    ++currentSumAndCountOfGradesForUniversity[subjectIndex][1];
                }
                calculateAndPrintMeanGrades(currentSumAndCountOfGradesForStudent, students[i].getStudentName());
            }
        }
        //last group
        calculateAndPrintMeanGrades(currentSumAndCountOfGradesForGroup, curGroupName);

        //last faculty
        calculateAndPrintMeanGrades(currentSumAndCountOfGradesForFaculty, curFacultyName);

        //uni
        calculateAndPrintMeanGrades(currentSumAndCountOfGradesForUniversity, universityName);
    }

    private static void calculateAndPrintMeanGrades(int[][] table, String name) {
        for (int i = 0; i < subjectsAllCount; ++i) {
            if (table[i][1] != 0) {
                System.out.println(name + " mean grade from subject " + subjectsAll[i] + " " + (double) table[i][0] / table[i][1]);
                table[i][0] = 0;
                table[i][1] = 0;
            }
        }
    }

    public static void main(String[] args) {
        initializing();
        printStudentsGrades();
        calculateAndPrintMeanGradesForEverything();
    }
}
