package university;

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

    public static void Initializing(){
        universityName = "YSU";

        facultyNames = new String[]{"Informatics and Applied Mathematics", "Mathematics and Mechanics"};
        facultyCount = facultyNames.length;

        groupNames = new String[]{"1501", "1502", "2501", "2502", "2503"};
        groupCount = new int[]{2, 3};

        subjectsForGroup = new String[][]{
                {"Physics", "Philosophy", "History"},
                {"Math", "Physics", "Philosophy"},
                {"Math", "Physics", "Philosophy"},
                {"Math", "Physics"},
                {"Math", "Physics", "Philosophy", "History"}};
        subjectsAll = new String[]{"Physics", "Philosophy", "History", "Math"};
        subjectsAllCount = subjectsAll.length;

        studentCountInGroup = new int[]{3, 5, 7, 4, 4};
        studentCountAll = 23;
        students = new Student[studentCountAll];
        for (int i = 0; i < studentCountAll; ++i) {
            students[i] = new Student("Student " + (i + 1));
        }

        int studentIndex = 0;
        Random ran = new Random();
        for (int i = 0; i < facultyCount; ++i) {
            for (int j = 0; j < groupCount[i]; ++j) {
                Group group = new Group(universityName, facultyNames[i], groupNames[i * facultyCount + j], subjectsForGroup[i * facultyCount + j]);
                for (int k = 0; k < studentCountInGroup[i * facultyCount + j]; ++k, ++studentIndex) {
                    int gradeMaxCount = ran.nextInt(6) + 5;
                    students[studentIndex].group = group;
                    students[studentIndex].grades = new GradeTable(subjectsForGroup[i * facultyCount + j], gradeMaxCount);
                }
            }
        }
    }

    public static void printStudentsGrades(){
        for (int i = 0; i < studentCountAll; i++) {
            System.out.println(students[i].studentName);
            students[i].grades.printGrades();
            System.out.println();
        }
    }

    private static void calculateAndPrintMeanGrades() {
        String curFacultyName = students[0].group.facultyName;
        String curGroupName = students[0].group.groupName;
        int[][] tableSt = new int[subjectsAllCount][2];
        int[][] tableGr = new int[subjectsAllCount][2];
        int[][] tableFc = new int[subjectsAllCount][2];
        int[][] tableUni = new int[subjectsAllCount][2];
        double avg;
        int m;

        for (int i = 0; i < studentCountAll; i++) {
            //group changed
            if (!curGroupName.equals(students[i].group.groupName)) {
                for (int j = 0; j < subjectsAllCount; j++) {
                    if (tableGr[j][1] != 0) {
                        avg = (double) tableGr[j][0] / tableGr[j][1];
                        System.out.println(curGroupName + " mean grade from subject " + subjectsAll[j] + avg);
                        tableGr[j][0] = 0;
                        tableGr[j][1] = 0;
                    }
                }
                curGroupName = students[i].group.groupName;
            }
            //faculty changed
            if (!curFacultyName.equals(students[i].group.facultyName)) {
                for (int j = 0; j < subjectsAllCount; j++) {
                    if (tableFc[j][1] != 0) {
                        avg = (double) tableFc[j][0] / tableFc[j][1];
                        System.out.println(curFacultyName + " mean grade from subject " + subjectsAll[j] + avg);
                        tableFc[j][0] = 0;
                        tableFc[j][1] = 0;
                    }
                }
                curFacultyName = students[i].group.facultyName;
            }

            //Adding grades to tables
            for (int j = 0; j < students[i].group.subjects.length; j++) {
                for (int k = 0; k < students[i].grades.gradeTable[j].length && students[i].grades.gradeTable[j][k] != -1; k++) {
                    for (m = 0; m < subjectsAllCount; m++) {
                        if (students[i].group.subjects[j].equals(subjectsAll[m])) {
                            break;
                        }
                    }
                    tableSt[m][0] += students[i].grades.gradeTable[j][k];
                    ++tableSt[m][1];
                    tableGr[m][0] += students[i].grades.gradeTable[j][k];
                    ++tableGr[m][1];
                    tableFc[m][0] += students[i].grades.gradeTable[j][k];
                    ++tableFc[m][1];
                    tableUni[m][0] += students[i].grades.gradeTable[j][k];
                    ++tableUni[m][1];
                }
                for (int jj = 0; jj < subjectsAllCount; jj++) {
                    if (tableSt[jj][1] != 0) {
                        avg = (double) tableSt[jj][0] / tableSt[jj][1];
                        System.out.println(students[i].studentName + " mean grade from subject " + subjectsAll[j] + avg);
                        tableSt[jj][0] = 0;
                        tableSt[jj][1] = 0;
                    }
                }
            }

        }
        //last group
        for (int j = 0; j < subjectsAllCount; j++) {
            if (tableGr[j][1] != 0) {
                avg = (double) tableGr[j][0] / tableGr[j][1];
                System.out.println(curGroupName + " mean grade from subject " + subjectsAll[j] + avg);
                tableGr[j][0] = 0;
                tableGr[j][1] = 0;
            }
        }
        //last faculty
        for (int j = 0; j < subjectsAllCount; j++) {
            if (tableFc[j][1] != 0) {
                avg = (double) tableFc[j][0] / tableFc[j][1];
                System.out.println(curFacultyName + " mean grade from subject " + subjectsAll[j] + avg);
                tableFc[j][0] = 0;
                tableFc[j][1] = 0;
            }
        }
        //uni
        for (int j = 0; j < subjectsAllCount; j++) {
            if (tableUni[j][1] != 0) {
                avg = (double) tableUni[j][0] / tableUni[j][1];
                System.out.println(universityName + " mean grade from subject " + subjectsAll[j] + avg);
                tableUni[j][0] = 0;
                tableUni[j][1] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Initializing();
        printStudentsGrades();
        calculateAndPrintMeanGrades();
    }
}
