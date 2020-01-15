package university;

public class Group {
    private String universityName;
    private String facultyName;
    private String groupName;
    private String[] subjects;

    public Group(String universityName, String facultyName, String groupName, String[] subjects) {
        this.universityName = universityName;
        this.facultyName = facultyName;
        this.groupName = groupName;
        this.subjects = subjects;
    }

    public String getUniversityName() {
        return universityName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String[] getSubjects() {
        return subjects;
    }
}
