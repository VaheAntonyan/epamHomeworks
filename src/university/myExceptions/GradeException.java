package university.myExceptions;

import university.Constants;

public class GradeException extends Exception {
    private static String ERROR_MSG = "Invalid Grade: Grade must be between [" + Constants.MIN_GRADE + ", " + Constants.MAX_GRADE + "]: Provided grade: %s";
    public GradeException(int grade) {
        super(String.format(ERROR_MSG, grade));
    }
}
