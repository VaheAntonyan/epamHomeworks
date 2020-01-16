package university;

import university.myExceptions.GradeException;

import java.util.Random;

public class GradeTable {
    int[][] gradeTable;
    String[] subjects;
    int gradeMaxCount;

    public GradeTable(String[] subjects, int gradeMaxCount) {
        this.subjects = subjects;
        this.gradeMaxCount = gradeMaxCount;
        gradeTable = new int[subjects.length][gradeMaxCount];
        for (int i = 0; i < subjects.length; i++) {
            for (int j = 0; j < gradeMaxCount; j++) {
                gradeTable[i][j] = -1;
            }
        }
    }

    public void printGrades() {
        for (int i = 0; i < gradeTable.length; i++) {
            System.out.print(subjects[i] + " ");
            for (int j = 0; j < gradeTable[i].length; j++) {
                System.out.print(gradeTable[i][j] + " ");
            }
            System.out.println();
        }
    }

    public GradeTable addGrades() throws GradeException {
        Random ran = new Random();
        for (int i = 0; i < subjects.length; i++) {
            for (int j = 0; j < gradeMaxCount - ran.nextInt(3); j++) {
                int grade = ran.nextInt(11);
                if (grade > Constants.MAX_GRADE || Constants.MIN_GRADE < 0) {
                    throw new GradeException(grade);
                }
                gradeTable[i][j] = grade;
            }
        }
        return this;
    }
}
