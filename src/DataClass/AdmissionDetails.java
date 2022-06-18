package DataClass;

import DataSource.GenderType;

public class AdmissionDetails {
    String name;
    int cutoff;
    GenderType gender;
    String dateOfBirth;
    String status;
    String mailId, district, departmentName;

//SerialNumber, Name, DOB, Gender, CutOffMark, DepartmentName, MailId, Status, District
    public AdmissionDetails(String name, int cutoff,String status,String mailId) {
        this.name = name;
        this.cutoff = cutoff;
        this.status = status;
        this.mailId = mailId;
    }

    public String getName() {
        return name;
    }

    public int getCutoff() {
        return cutoff;
    }

    public String getStatus() {
        return status;
    }

    public GenderType getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDistrict() {
        return district;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public AdmissionDetails( String name, String dateOfBirth, GenderType gender, int cutOffMark, String departmentName, String mailId, String status, String district) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.cutoff = cutOffMark;
        this.departmentName = departmentName;
        this.status = status;
        this.mailId = mailId;
        this.district = district;
    }


    public String getMailId() {
        return mailId;
    }

    @Override
    public String toString() {
        return name+"  "+cutoff+"  "+status+"  "+mailId;
    }
}
