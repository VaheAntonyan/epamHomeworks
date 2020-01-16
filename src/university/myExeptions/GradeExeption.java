package university.myExeptions;

import university.Constants;

public class GradeExeption extends Exception {
    private int grade;

    public GradeExeption(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Invalid Grade: Grade must be between [" + Constants.MIN_GRADE + ", " + Constants.MAX_GRADE + "]: Your grade: " + grade;
    }
}
