package university;

import university.myExeptions.GradeExeption;

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
                    } catch (GradeExeption gradeExeption) {
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

    private static void calculateAndPrintMeanGrades() {
        String curFacultyName = students[0].getGroup().getFacultyName();
        String curGroupName = students[0].getGroup().getGroupName();
        int[][] currentSumAndCountOfGradesForStudent = new int[subjectsAllCount][2];
        int[][] currentSumAndCountOfGradesForGroup = new int[subjectsAllCount][2];
        int[][] currentSumAndCountOfGradesForFaculty = new int[subjectsAllCount][2];
        int[][] currentSumAndCountOfGradesForUniversity = new int[subjectsAllCount][2];
        double avg;
        int m;

        for (int i = 0; i < studentCountAll; i++) {
            //group changed
            if (!curGroupName.equals(students[i].getGroup().getGroupName())) {
                for (int j = 0; j < subjectsAllCount; j++) {
                    if (currentSumAndCountOfGradesForGroup[j][1] != 0) {
                        avg = (double) currentSumAndCountOfGradesForGroup[j][0] / currentSumAndCountOfGradesForGroup[j][1];
                        System.out.println(curGroupName + " mean grade from subject " + subjectsAll[j] + avg);
                        currentSumAndCountOfGradesForGroup[j][0] = 0;
                        currentSumAndCountOfGradesForGroup[j][1] = 0;
                    }
                }
                curGroupName = students[i].getGroup().getGroupName();
            }
            //faculty changed
            if (!curFacultyName.equals(students[i].getGroup().getFacultyName())) {
                for (int j = 0; j < subjectsAllCount; j++) {
                    if (currentSumAndCountOfGradesForFaculty[j][1] != 0) {
                        avg = (double) currentSumAndCountOfGradesForFaculty[j][0] / currentSumAndCountOfGradesForFaculty[j][1];
                        System.out.println(curFacultyName + " mean grade from subject " + subjectsAll[j] + avg);
                        currentSumAndCountOfGradesForFaculty[j][0] = 0;
                        currentSumAndCountOfGradesForFaculty[j][1] = 0;
                    }
                }
                curFacultyName = students[i].getGroup().getFacultyName();
            }

            //Adding grades to tables
            for (int j = 0; j < students[i].getGroup().getSubjects().length; j++) {
                for (int k = 0; k < students[i].getGrades().gradeTable[j].length && students[i].getGrades().gradeTable[j][k] != -1; k++) {
                    for (m = 0; m < subjectsAllCount; m++) {
                        if (students[i].getGroup().getSubjects()[j].equals(subjectsAll[m])) {
                            break;
                        }
                    }
                    currentSumAndCountOfGradesForStudent[m][0] += students[i].getGrades().gradeTable[j][k];
                    ++currentSumAndCountOfGradesForStudent[m][1];
                    currentSumAndCountOfGradesForGroup[m][0] += students[i].getGrades().gradeTable[j][k];
                    ++currentSumAndCountOfGradesForGroup[m][1];
                    currentSumAndCountOfGradesForFaculty[m][0] += students[i].getGrades().gradeTable[j][k];
                    ++currentSumAndCountOfGradesForFaculty[m][1];
                    currentSumAndCountOfGradesForUniversity[m][0] += students[i].getGrades().gradeTable[j][k];
                    ++currentSumAndCountOfGradesForUniversity[m][1];
                }
                for (int jj = 0; jj < subjectsAllCount; jj++) {
                    if (currentSumAndCountOfGradesForStudent[jj][1] != 0) {
                        avg = (double) currentSumAndCountOfGradesForStudent[jj][0] / currentSumAndCountOfGradesForStudent[jj][1];
                        System.out.println(students[i].getStudentName() + " mean grade from subject " + subjectsAll[j] + avg);
                        currentSumAndCountOfGradesForStudent[jj][0] = 0;
                        currentSumAndCountOfGradesForStudent[jj][1] = 0;
                    }
                }
            }

        }
        //last group
        for (int j = 0; j < subjectsAllCount; j++) {
            if (currentSumAndCountOfGradesForGroup[j][1] != 0) {
                avg = (double) currentSumAndCountOfGradesForGroup[j][0] / currentSumAndCountOfGradesForGroup[j][1];
                System.out.println(curGroupName + " mean grade from subject " + subjectsAll[j] + avg);
                currentSumAndCountOfGradesForGroup[j][0] = 0;
                currentSumAndCountOfGradesForGroup[j][1] = 0;
            }
        }
        //last faculty
        for (int j = 0; j < subjectsAllCount; j++) {
            if (currentSumAndCountOfGradesForFaculty[j][1] != 0) {
                avg = (double) currentSumAndCountOfGradesForFaculty[j][0] / currentSumAndCountOfGradesForFaculty[j][1];
                System.out.println(curFacultyName + " mean grade from subject " + subjectsAll[j] + avg);
                currentSumAndCountOfGradesForFaculty[j][0] = 0;
                currentSumAndCountOfGradesForFaculty[j][1] = 0;
            }
        }
        //uni
        for (int j = 0; j < subjectsAllCount; j++) {
            if (currentSumAndCountOfGradesForUniversity[j][1] != 0) {
                avg = (double) currentSumAndCountOfGradesForUniversity[j][0] / currentSumAndCountOfGradesForUniversity[j][1];
                System.out.println(universityName + " mean grade from subject " + subjectsAll[j] + avg);
                currentSumAndCountOfGradesForUniversity[j][0] = 0;
                currentSumAndCountOfGradesForUniversity[j][1] = 0;
            }
        }
    }

    public static void main(String[] args) {
        initializing();
        printStudentsGrades();
        calculateAndPrintMeanGrades();
    }
}
