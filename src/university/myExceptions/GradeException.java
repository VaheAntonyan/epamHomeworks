package university.myExceptions;

import university.Constants;

public class GradeException extends Exception {
    private int grade;

    public GradeException(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Invalid Grade: Grade must be between [" + Constants.MIN_GRADE + ", " + Constants.MAX_GRADE + "]: Your grade: " + grade;
    }
}
