package University;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String universityName = "YSU";

        String[] facultyNames = {"Informatics and Applied Mathematics", "Mathematics and Mechanics"};
        int facultyCount = facultyNames.length;

        String[] groupNames = {"1501", "1502", "2501", "2502", "2503"};
        int[] groupCount = {2, 3};

        String[][] subjectsArray = {
                {"Physics", "Philosophy", "History"},
                {"Math", "Physics", "Philosophy"},
                {"Math", "Physics", "Philosophy"},
                {"Math", "Physics"},
                {"Math", "Physics", "Philosophy", "History"}};
        String[] subjectsNames = {"Physics", "Philosophy", "History", "Math"};

        int[] studentCountInGroup = {3, 5, 7, 4, 4};
        int studentCountAll = 23;
        int studentIndex = 0;
        Student[] students = new Student[studentCountAll];
        for (int i = 0; i < studentCountAll; ++i) {
            students[i] = new Student("Student " + (i + 1));
        }
        Random ran = new Random();

        for (int i = 0; i < facultyCount; ++i) {
            for (int j = 0; j < groupCount[i]; ++j) {
                Group group = new Group(universityName, facultyNames[i], groupNames[i * facultyCount + j], subjectsArray[i * facultyCount + j]);
                for (int k = 0; k < studentCountInGroup[i * facultyCount + j]; ++k, ++studentIndex) {
                    int gradeMaxCount = ran.nextInt(6) + 5;
                    students[studentIndex].group = group;
                    students[studentIndex].grades = new GradeTable(subjectsArray[i * facultyCount + j], gradeMaxCount);
                }
            }
        }

        for (int i = 0; i < studentCountAll; i++) {
            System.out.println(students[i].studentName);
            students[i].grades.printGrades();
            System.out.println();
        }

        String curFacultyName = students[0].group.facultyName;
        String curGroupName = students[0].group.groupName;
        int[][] tableSt = new int[subjectsNames.length][2];
        int[][] tableGr = new int[subjectsNames.length][2];
        int[][] tableFc = new int[subjectsNames.length][2];
        int[][] tableUni = new int[subjectsNames.length][2];
        double avg;
        int m;

        for (int i = 0; i < studentCountAll; i++) {
            //group changed
            if (!curGroupName.equals(students[i].group.groupName)) {
                for (int j = 0; j < subjectsNames.length; j++) {
                    if (tableGr[j][1] != 0) {
                        avg = (double) tableGr[j][0] / tableGr[j][1];
                        System.out.println(curGroupName + " mean grade from subject " + subjectsNames[j] + avg);
                        tableGr[j][0] = 0;
                        tableGr[j][1] = 0;
                    }
                }
                curGroupName = students[i].group.groupName;
            }
            //faculty changed
            if (!curFacultyName.equals(students[i].group.facultyName)) {
                for (int j = 0; j < subjectsNames.length; j++) {
                    if (tableFc[j][1] != 0) {
                        avg = (double) tableFc[j][0] / tableFc[j][1];
                        System.out.println(curFacultyName + " mean grade from subject " + subjectsNames[j] + avg);
                        tableFc[j][0] = 0;
                        tableFc[j][1] = 0;
                    }
                }
                curFacultyName = students[i].group.facultyName;
            }

            //Adding grades to tables
            for (int j = 0; j < students[i].group.subjects.length; j++) {
                for (int k = 0; k < students[i].grades.gradeTable[j].length && students[i].grades.gradeTable[j][k] != -1; k++) {
                    for (m = 0; m < subjectsNames.length; m++) {
                        if (students[i].group.subjects[j].equals(subjectsNames[m])) {
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
                for (int jj = 0; jj < subjectsNames.length; jj++) {
                    if (tableSt[jj][1] != 0) {
                        avg = (double) tableSt[jj][0] / tableSt[jj][1];
                        System.out.println(students[i].studentName + " mean grade from subject " + subjectsNames[j] + avg);
                        tableSt[jj][0] = 0;
                        tableSt[jj][1] = 0;
                    }
                }
            }

        }
        //last group
        for (int j = 0; j < subjectsNames.length; j++) {
            if (tableGr[j][1] != 0) {
                avg = (double) tableGr[j][0] / tableGr[j][1];
                System.out.println(curGroupName + " mean grade from subject " + subjectsNames[j] + avg);
                tableGr[j][0] = 0;
                tableGr[j][1] = 0;
            }
        }
        //last faculty
        for (int j = 0; j < subjectsNames.length; j++) {
            if (tableFc[j][1] != 0) {
                avg = (double) tableFc[j][0] / tableFc[j][1];
                System.out.println(curFacultyName + " mean grade from subject " + subjectsNames[j] + avg);
                tableFc[j][0] = 0;
                tableFc[j][1] = 0;
            }
        }
        //uni
        for (int j = 0; j < subjectsNames.length; j++) {
            if (tableUni[j][1] != 0) {
                avg = (double) tableUni[j][0] / tableUni[j][1];
                System.out.println(universityName + " mean grade from subject " + subjectsNames[j] + avg);
                tableUni[j][0] = 0;
                tableUni[j][1] = 0;
            }
        }
    }
}
