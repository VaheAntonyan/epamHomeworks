package university.myExeptions;

public class GradeExeption extends Exception {
    private int grade;

    public GradeExeption(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Invalid Grade: Grade must be between [0, 10]: Your grade: " + grade;
    }
}
