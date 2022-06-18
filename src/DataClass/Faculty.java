package DataClass;

import DataSource.GenderType;

public class Faculty {
    String facultyId;
    String facultyName;
    String departmentName;
    Department department;
    int year;
    String mailId;
    String phoneNumber;
    String dateOfJoining;
    GenderType gender;
    String district;

    public Faculty(String id, String name, String departmentName, int year, String mailId, String phoneNumber, String joiningDate, GenderType gender, String district) {
        this.facultyId = id;
        this.gender = gender;
        this.dateOfJoining = joiningDate;
        this.district = district;
        this.facultyName = name;
        this.departmentName = departmentName;
        this.year = year;
        this.mailId = mailId;
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public GenderType getGender() {
        return gender;
    }

    public String getDistrict() {
        return district;
    }

    public Faculty(String id, String date,GenderType gender, String district)
{
    this.facultyId = id;
    this.gender = gender;
    this.dateOfJoining = date;
    this.district = district;
}

    public Faculty(String facultyName, Department department, int year) {
        this.facultyName = facultyName;
        this.department = department;
        this.year = year;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Faculty(String id, String name, String departmentName, int year,String mailId, String phoneNumber) {
        this.facultyId = id;
        this.facultyName = name;
        this.departmentName = departmentName;
        this.year = year;
        this.mailId = mailId;
        this.phoneNumber = phoneNumber;
    }

    public String getMailId() {
        return mailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "DataClass.Faculty{" +
                "facultyName='" + facultyName + '\'' +
                ", department=" + department +
                ", year=" + year +
                '}';
    }
}
