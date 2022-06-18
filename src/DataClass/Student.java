package DataClass;

import java.util.HashMap;

public class Student {
    String rollNumber;
    String studentName;
    String departmentName;
    int year;
    String mailId;
    String phoneNumber;
    PersonalDetails personalDetails;
    HashMap<Integer,HashMap<String,Integer>> allSemMarks;

    public Student(String rollNumber, String studentName, String departmentName, int year, String mailId, PersonalDetails personalDetails, HashMap<Integer, HashMap<String, Integer>> allSemMarks) {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.departmentName = departmentName;
        this.year = year;
        this.mailId = mailId;
        this.personalDetails = personalDetails;
        this.allSemMarks = allSemMarks;
    }



    public Student(String rollNumber, String name, String departmentName, int year, String mailId, String phoneNumber) {
        this.rollNumber = rollNumber;
        this.studentName = name;
        this.departmentName = departmentName;
        this.year = year;
        this.mailId = mailId;
        this.phoneNumber = phoneNumber;
    }

    public Student() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public HashMap<Integer, HashMap<String, Integer>> getAllSemMarks() {
        return allSemMarks;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getYear() {
        return year;
    }

    public String getMailId() {
        return mailId;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    @Override
    public String toString() {
        return "DataClass.Student{" +
                "rollNumber='" + rollNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", departName='" + departmentName + '\'' +
                ", year=" + year +
                ", mailId='" + mailId + '\'' +
                ", allSemMarks=" + allSemMarks +
                '}';
    }

}