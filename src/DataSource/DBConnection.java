package DataSource;

import DataClass.*;
import UtilClass.MD5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBConnection implements StudentInterface {
    Connection con = null;
    PreparedStatement ps;
    public ResultSet rs;


    boolean rowUpdated, success;

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/collegestudentmanagement?" + "user=root&password=Allah786$");
            System.out.println("Connection Established");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean insertStudentAcademicDetails(Student student) throws SQLException {
        ps = con.prepareStatement("INSERT INTO  studentacademicdetails(RollNumber,Name,DepartmentName,Year,PhoneNumber,MailId) VALUES(?,?,?,?,?,?)");
        ps.setString(1, student.getRollNumber());
        ps.setString(2, student.getStudentName());
        ps.setString(3, student.getDepartmentName());
        ps.setInt(4, student.getYear());
        ps.setString(5, student.getPhoneNumber());
        ps.setString(6, student.getMailId());

        success = ps.executeUpdate() > 0;
        return success;
    }

    public boolean insertDepartment(int departmentId, String departmentName, int cutOff, int seatavailability) throws SQLException {
        ps = con.prepareStatement("INSERT INTO  departmentdetails(DepartmentId, DepartmentName, CutOffMark, SeatAvailability) VALUES(?,?,?,?)");
        ps.setInt(1, departmentId);
        ps.setString(2, departmentName);
        ps.setInt(3, cutOff);
        ps.setInt(4, seatavailability);

        success = ps.executeUpdate() > 0;
        return success;
    }

    public boolean insertPersonalDetails(PersonalDetails personalDetails) throws SQLException {
        ps = con.prepareStatement("INSERT INTO  personaldetails(RollNumber, DOB, Gender, FatherName, District) VALUES(?,?,?,?,?)");
        ps.setString(1, personalDetails.getRollNumber());
        ps.setString(2, personalDetails.getDateOfBirth());
        ps.setString(3, String.valueOf(personalDetails.getGender()));
        ps.setString(4, personalDetails.getFatherName());
        ps.setString(5, personalDetails.getDistrict());
        success = ps.executeUpdate() > 0;
        return success;
    }

    public boolean insertFacultyDetails(Faculty faculty) throws SQLException {
        ps = con.prepareStatement("INSERT INTO  classinchargedetails(FacultyId, FacultyName, DepartmentName, Year, MailId, PhoneNumber) VALUES(?,?,?,?,?,?)");
        ps.setString(1, faculty.getFacultyId());
        ps.setString(2, faculty.getFacultyName());
        ps.setString(3, faculty.getDepartmentName());
        ps.setInt(4, faculty.getYear());
        ps.setString(5, faculty.getMailId());
        ps.setString(6, faculty.getPhoneNumber());

        success = ps.executeUpdate() > 0;
        return success;
    }

    public boolean insertFacultyPersonalDetails(String id, String gender, String date, String district) throws SQLException {
        ps = con.prepareStatement("INSERT INTO  facultypersonaldetails(FacultyId, JoiningDate, Gender, District) VALUES(?,?,?,?)");
        ps.setString(1, id);
        ps.setString(2, gender);
        ps.setString(3, date);
        ps.setString(4, district);

        success = ps.executeUpdate() > 0;
        return success;
    }

    public boolean insertNewAdmissionDetails(String name, int cutOff, String departmentName, String mailId, String status) throws SQLException {
        ps = con.prepareStatement("INSERT INTO  newadmissiondetails(SerialNumber, Name, CutOffMark, DepartmentName, MailId, Status) VALUES(?,?,?,?,?,?)");
        ps.setString(2, name);
        ps.setInt(3, cutOff);
        ps.setString(4, departmentName);
        ps.setString(5, mailId);
        ps.setString(6, status);

        success = ps.executeUpdate() > 0;
        return success;
    }

    public boolean insertSubjectDetails(int subjectId, String subjectName, int departmentId, int semester) throws SQLException {
        ps = con.prepareStatement("INSERT INTO subjectdetails(SubjectId, SubjectName, DepartmentId, Semester) VALUES (?,?,?,?)");
        ps.setInt(1, subjectId);
        ps.setString(2, subjectName);
        ps.setInt(3, departmentId);
        ps.setInt(4, semester);
        success = ps.executeUpdate() > 0;
        return success;
    }

    public boolean insertMarks(MarkDetails[] markDetails) throws SQLException {
        StringBuilder a = new StringBuilder("INSERT INTO studentmarkdetails(RollNumber, DepartmentId, Semester, SubjectId, Mark) VALUES ");
        int n = markDetails.length;
        for (int i = 0; i < n; i++) {
            String b = markDetails[i].toString();
            a.append(b);
            if (i < n - 1) {
                a.append(",");
            }
        }
        System.out.println(a);
        ps = con.prepareStatement(a.toString());
        ps.executeUpdate();
        return success;
    }

    public List<AdmissionDetails> getNewAdmissionDetails() throws SQLException {
        List<AdmissionDetails> admissionDetails = new ArrayList<>();
        ps = con.prepareStatement("SELECT * from newadmissiondetails ");
        rs = ps.executeQuery();

        while (rs.next()) {
            String name = rs.getString("Name");
            String dateOfBirth = rs.getString("DOB");
            GenderType gender = GenderType.valueOf(rs.getString("Gender"));
            int cutOffMark = rs.getInt("CutOffMark");
            String departmentName = rs.getString("DepartmentName");
            String mailId = rs.getString("MailId");
            String status = rs.getString("Status");
            String district = rs.getString("District");
            admissionDetails.add(new AdmissionDetails(name, dateOfBirth, gender, cutOffMark, departmentName, mailId, status, district));
        }

        return admissionDetails;
    }

    public List<Student> getStudentDetails() throws SQLException {
        List<Student> students = new ArrayList<>();
        ps = con.prepareStatement("Select RollNumber, Name, DepartmentName, Year, PhoneNumber, MailId from studentacademicdetails");
        rs = ps.executeQuery();
        while (rs.next()) {
            String rollNumber = rs.getString("RollNumber");
            String studentName = rs.getString("Name");
            String departmentName = rs.getString("DepartmentName");
            int year = rs.getInt("Year");
            String phoneNumber = rs.getString("PhoneNumber");
            String mailId = rs.getString("MailId");
            students.add(new Student(rollNumber, studentName, departmentName, year, phoneNumber, mailId));
        }
        return students;
    }

    public List<Department> getDepartments() throws SQLException {
        List<Department> departmentList = new ArrayList<>();
        ps = con.prepareStatement(" Select DepartmentId, DepartmentName, CutOffMark, SeatAvailability from departmentdetails");
        rs = ps.executeQuery();
        while (rs.next()) {

            int departmentId = rs.getInt("DepartmentId");
            String departmentName = rs.getString("DepartmentName");
            int cutOff = rs.getInt("CutOffMark");
            int seatsAvailability = rs.getInt("SeatAvailability");
            departmentList.add(new Department(departmentId, departmentName, seatsAvailability, cutOff));
        }
        return departmentList;
    }

    public List<PersonalDetails> getPersonalDetails() throws SQLException {
        List<PersonalDetails> personalDetails = new ArrayList<>();
        ps = con.prepareStatement(" Select RollNumber, DOB, Gender, FatherName, District from personaldetails");
        rs = ps.executeQuery();
        while (rs.next()) {
            String rollNumber = rs.getString("RollNumber");
            String dateOfBirth = rs.getString("DOB");
            String gender = rs.getString("Gender");
            String fatherName = rs.getString("FatherName");
            String district = rs.getString("District");
            personalDetails.add(new PersonalDetails(rollNumber, dateOfBirth, gender, fatherName, district));
        }
        return personalDetails;
    }

    public List<Faculty> getFacultyDetails() throws Exception {
        List<Faculty> faculties = new ArrayList<>();
        ps = con.prepareStatement("Select FacultyId, FacultyName, DepartmentName, Year, MailId, PhoneNumber from classinchargedetails");
        rs = ps.executeQuery();
        while (rs.next()) {
            String id = rs.getString("FacultyId");
            String name = rs.getString("FacultyName");
            String departmentName = rs.getString("DepartmentName");
            int year = rs.getInt("Year");
            String mailId = rs.getString("MailId");
            String phoneNumber = rs.getString("PhoneNumber");
            faculties.add(new Faculty(id, name, departmentName, year, mailId, phoneNumber));
        }
        return faculties;
    }

    public List<Faculty> getFacultyPersonalDetails() throws Exception {
        List<Faculty> faculties = new ArrayList<>();
        ps = con.prepareStatement(" Select * from FacultyId, JoiningDate, Gender, District from facultypersonaldetails");
        rs = ps.executeQuery();
        while (rs.next()) {
            String rollNumber = rs.getString("FacultyId");
            String date = rs.getString("JoiningDate");
            GenderType gender = GenderType.valueOf(rs.getString("Gender"));
            String district = rs.getString("District");
            faculties.add(new Faculty(rollNumber, date, gender, district));
        }
        return faculties;

    }

    public Faculty getByFacultyId(String id) throws SQLException {
        Faculty faculty = null;
        ps = con.prepareStatement("SELECT  classinchargedetails.FacultyId,FacultyName,DepartmentName,Year,MailId,PhoneNumber  ,facultypersonaldetails.JoiningDate,Gender,District FROM classinchargedetails right join facultypersonaldetails on classinchargedetails.FacultyId = ?  where facultypersonaldetails.FacultyId = ?");
        ps.setString(1, id);
        rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString("FacultyName");
            String departmentName = rs.getString("DepartmentName");
            int year = rs.getInt("Year");
            String mailId = rs.getString("MailId");
            String phoneNumber = rs.getString("PhoneNumber");
            String joiningDate = rs.getString("JoiningDate");
            GenderType gender = (GenderType) rs.getObject("Gender");
            String district = rs.getString("District");
            faculty = new Faculty(id, name, departmentName, year, mailId, phoneNumber, joiningDate, gender, district);
        }
        return faculty;
    }

    public Student getByStudentId(String rollNumber) throws Exception {
        Student student = null;
        ps = con.prepareStatement("SELECT * FROM studentacademicdetails where RollNumber = ?");
        ps.setString(1, rollNumber);
        rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString("Name");
            String departmentName = rs.getString("DepartmentName");
            int year = rs.getInt("Year");
            String phoneNumber = rs.getString("PhoneNumber");
            String mailId = rs.getString("MailId");
            student = new Student(rollNumber, name, departmentName, year, phoneNumber, mailId);

        }
        return student;
    }

    public Student getByStudentName(String name) throws Exception {
        Student student = null;
        ps = con.prepareStatement("SELECT * FROM studentacademicdetails where Name = ?");
        ps.setString(1, name);
        rs = ps.executeQuery();
        while (rs.next()) {
            String id = rs.getString("RollNumber");
            String departmentName = rs.getString("DepartmentName");
            int year = rs.getInt("Year");
            String phoneNumber = rs.getString("PhoneNumber");
            String mailId = rs.getString("MailId");
            student = new Student(id, name, departmentName, year, phoneNumber, mailId);

        }
        return student;
    }

    public boolean updateSeats(int seats, String departmentName) throws SQLException {
        ps = con.prepareStatement("UPDATE departmentdetails SET SeatAvailability = ? WHERE DepartmentName = ?");
        ps.setString(2, departmentName);
        ps.setInt(1, seats);
        rowUpdated = ps.executeUpdate() > 0;
        return rowUpdated;
    }

    public boolean removeStudents(String rollNumber) throws SQLException {
        ps = con.prepareStatement("DELETE FROM studentacademicdetails WHERE RollNumber = ?");
        ps.setString(1, rollNumber);
        // execute the delete statement
        success = ps.executeUpdate() > 0;
        return success;
    }

    public List<MarkDetails> getMarksByStudentId(String rollNumber) throws SQLException {

        List<MarkDetails> markDetails = new ArrayList<>();
        ps = con.prepareStatement("select st.RollNumber,studentacademicdetails.Name,st.DepartmentId,st.Semester,st.SubjectId,st.Mark,subjectdetails.SubjectName from studentmarkdetails St inner join subjectdetails using(SubjectId)inner join studentacademicdetails using(RollNumber) where st.RollNumber = ?");
        ps.setString(1, rollNumber);
        rs = ps.executeQuery();
        while (rs.next()) {
            int departmentId = rs.getInt("DepartmentId");
            int semester = rs.getInt("Semester");
            int subjectCode = rs.getInt("SubjectId");
            int mark = rs.getInt("Mark");
            String subName = rs.getString("SubjectName");
            markDetails.add(new MarkDetails(rollNumber, departmentId, semester, subjectCode, mark, subName));
        }

        return markDetails;
    }

    public void close() throws Exception {
        con.close();
        System.out.println("Connection Closed");
    }

    public boolean newUser(String userId, String password) throws SQLException {
        String passWordHash = MD5.getMd5(password);
        ps = con.prepareStatement("INSERT INTO userdetails(UserId, UserPassword) VALUES (?,?)");
        ps.setString(1, userId);
        ps.setString(2, passWordHash);
        success = ps.executeUpdate() > 0;
        return success;
    }

    public UserDetails userSignIn(String userId) throws SQLException {
        UserDetails userDetails = null;
        ps = con.prepareStatement("Select UserId, UserPassword from userdetails where UserId = ? ");
        ps.setString(1, userId);
        rs = ps.executeQuery();
        while (rs.next()) {
            String passwrd = rs.getString("UserPassword");
            String hashcde = MD5.getMd5(passwrd);
            userDetails = new UserDetails(userId, hashcde);
            System.out.println(hashcde);
        }
        return userDetails;
    }
}