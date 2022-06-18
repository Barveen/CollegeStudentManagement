package DataClass;

public class MarkDetails {
    String rollNumber;
    int departmentId;
    int semester;
    int subjectId;
    int marks;
    String subName;

    public MarkDetails(String rollNumber, int departmentId, int semester, int subjectId, int marks) {
        this.rollNumber = rollNumber;
        this.departmentId = departmentId;
        this.semester = semester;
        this.subjectId = subjectId;
        this.marks = marks;
    }

    public MarkDetails(int subjectCode, int mark) {
        this.subjectId = subjectCode;
        this.marks = mark;
    }

    public String getSubName() {
        return subName;
    }

    public MarkDetails(String rollNumber, int departmentId, int semester, int subjectCode, int mark, String subName) {
        this.rollNumber = rollNumber;
        this.departmentId = departmentId;
        this.semester = semester;
        this.subjectId = subjectCode;
        this.marks = mark;
        this.subName = subName;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getSemester() {
        return semester;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "("+rollNumber +", "+departmentId+","+semester+","+subjectId+","+marks+")";


    }
}
