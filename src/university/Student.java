package university;

public class Student {
    private String studentName;
    private Group group;
    private GradeTable grades;

    private Student(StudentBuilder builder) {
        studentName = builder.studentName;
        group = builder.group;
        grades = builder.grades;
    }

    public static class StudentBuilder {
        private String studentName;
        private Group group;
        private GradeTable grades;

        public StudentBuilder(String studentName) {
            this.studentName = studentName;
        }

        public StudentBuilder group(Group group) {
            this.group = group;
            return this;
        }

        public StudentBuilder grades(GradeTable grades) {
            this.grades = grades;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }

    public String getStudentName() {
        return studentName;
    }

    public Group getGroup() {
        return group;
    }

    public GradeTable getGrades() {
        return grades;
    }
}
