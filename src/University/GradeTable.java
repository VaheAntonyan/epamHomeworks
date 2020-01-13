package University;

import java.util.Random;

public class GradeTable {
    int[][] gradeTable;
    String[] subjects;

    public GradeTable(String[] subjects, int gradeMaxCount) {
        this.subjects = subjects;
        gradeTable = new int[subjects.length][gradeMaxCount];
        for (int i = 0; i < subjects.length; i++) {
            for (int j = 0; j < gradeMaxCount; j++) {
                gradeTable[i][j] = -1;
            }
        }
        Random ran = new Random();
        for (int i = 0; i < subjects.length; i++) {
            for (int j = 0; j < gradeMaxCount - ran.nextInt(3); j++) {
                gradeTable[i][j] = ran.nextInt(11);
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
}
