/*
package DataSource;
import DataClass.*;
import UtilClass.Checking;

import java.util.*;


public class StudentFunctions {

    Authentication authentication = new Authentication();
    Checking checking = new Checking();
    int choice,seats;
    boolean success = false;
    static Scanner sc = new Scanner(System.in);
    static Scanner str = new Scanner(System.in);
    private static DataSource.StudentFunctions single_instance = null;
    String name;
    String mailId;
    String id;
    String departmentName;
    String dateOfBirth,subjectName;
    String gender,status;
    int cutOffMark, mark, check1,check2;
    String phoneNumber;
    String district;
    List<Student>students = new ArrayList<>();
    List<AdmissionDetails> admissionDetails = new ArrayList<>();
    List<Department> departmentList = new ArrayList<>();
    int year, departmentId;

    public static StudentFunctions getInstance() {
        // To ensure only one instance is created
        //a class that has only one instance and provides a global point of access to it
        if (single_instance == null) {
            single_instance = new DataSource.StudentFunctions();
        }
        return single_instance;
    }

    public void admin() {
        System.out.println("""
                Choose \s
                 1 --> for sign up
                 2 --> for login\s""");
        choice = checking.checkIntRange();
        if(choice == 1)
        {
            authentication.signup();
            success = true;
        }
        else if(choice == 2)
        {
            authentication.login();
            success = true;
        }
        if(success)
        {
           adminMenuOperation();
        }
    }

    public void student() {
        System.out.println("""
                Choose \s
                 1 --> for sign up
                 2 --> for login\s""");
        choice = sc.nextInt();checking.checkIntRange();
        if(choice == 1)
        {
            authentication.signup();
            success = true;
        }
        else if(choice == 2)
        {
            authentication.login();
            success = true;
        }
        if(success)
        {
            studentOperation();
        }
    }

    public void newAdmission() {
        System.out.println("""
                Choose \s
                 1 --> for sign up
                 2 --> for login\s""");
        choice = sc.nextInt();checking.checkIntRange();
        if(choice == 1)
        {
            authentication.signup();
            success = true;
        }
        else if(choice == 2)
        {
            authentication.login();
            success = true;
        }
        if(success)
        {
            newAdmissionOperation();
        }
    }

    public void adminMenuOperation() {
        System.out.println("Welcome to Admin Menu");
        boolean on = true;

        while(on) {
            System.out.println("----------------------------------------");
            System.out.println("""
                    Enter\s
                    1 -->  for view admission details
                    2 -->  for Add Departments
                    3 -->  for Show Departments
                    4 -->  for Add Students
                    5 -->  for fail students
                    6 -->  for View Students Details
                    7 -->  for Search By StudentId
                    8 -->  for Search By StudentName
                    9 -->  for View Students By Year
                    10 --> for view student by their department & year
                    11 --> log out""");
            System.out.println("----------------------------------------");
            System.out.println("Enter your choice: ");
            int choice = checking.checkIntRangeAdmin();
            switch (choice) {
                case 1 -> viewAdmissionDetails();
                case 2 -> addDepartments();
                case 3 -> showDepartments();
                case 4 -> addStudents();
                case 5 -> viewFailStudents();
                case 6 -> viewStudentDetails();
                case 7 -> searchByStudentId();
                case 8 -> searchByStudentName();
                case 9 -> viewStudentDetailsByYear();
                case 10 -> viewStudentByTheirDepartment();
                case 11 -> {
                    authentication.logOut();
                    on = false;
                }
            }
        }
    }

    private void addDepartments() {
        System.out.println("Enter the departmentId : ");
        departmentId = sc.nextInt();
        System.out.println("Enter the DataClass.Department Name : ");
        departmentName = sc.next();
        System.out.println("Enter the available seats in this department : ");
        int availableSeats = sc.nextInt();
        System.out.println("Enter the cutOff Mark for this department : ");
        cutOffMark = sc.nextInt();
        departmentList.add(new Department(departmentId,departmentName,availableSeats,cutOffMark));

    }

    private  void addStudents()
    {
        System.out.println("Enter the student roll Number : ");
        id = checkDuplicate(sc.next());
        System.out.println("Enter the student name : ");
        name = checking.isValidName(str.nextLine());
        System.out.println("Enter the DataClass.Department name : ");
        departmentName = checkDepartment(sc.next());
        detailsOfStudents();
        System.out.println("Enter the year of the student : ");
        year = sc.nextInt();
        printSemesterSubjectDetails();
        System.out.println("Enter how many semester you want to enter : ");
        int sem = sc.nextInt();
        HashMap<Integer, HashMap<String,Integer>> allSemMarks = new HashMap<>();
        for(int i = 1; i<= sem ; i++)
        {
            System.out.println("Enter how many subject in this Semester : ");
            int sub = sc.nextInt();
            HashMap<String,Integer> subjectMarks = new HashMap<>();
            for (int j = 0; j < sub; j++)
            {
                System.out.println("Enter the Subject Name : ");
                subjectName = str.nextLine();
                System.out.println("Enter the mark of this subject : ");
                mark = sc.nextInt();
                subjectMarks.put(subjectName,mark);
                System.out.println(subjectMarks);
            }
            allSemMarks.put(i,subjectMarks);
        }
        PersonalDetails personalDetails = new PersonalDetails(dateOfBirth,gender,phoneNumber,district);
        students.add(new Student(id,name,departmentName,year,mailId,personalDetails,allSemMarks));
    }


    private void detailsOfStudents() {

        System.out.println("Enter the DOB in format of 01/01/2000 : ");
        dateOfBirth = checking.checkDOB(sc.next());
        System.out.println("Enter the Gender in the format of (Male/Female/Others: ");
        gender = checking.checkGender(sc.next());
        System.out.println("Enter the phoneNumber (Starts with 6 - 9 & 10 digits) : ");
        phoneNumber = checking.checkPhoneNumber(sc.next());
        System.out.println("Enter the mailId : ");
        mailId = checking.checkMailId(sc.next());
        System.out.println("Enter the district : ");
        district = sc.next();
    }

    public  void printSemesterSubjectDetails()
    {
        System.out.println("""
            For 1st year 4 subject must
                2nd year 4 subject must
                3rd year 3 Subject must
                4th year 2 Subject must""");
    }
    public void viewStudentDetails() {
        for (Student student : students)
        {
            System.out.println(student.getRollNumber()+" "+student.getStudentName()+" "+student.getDepartmentName()+" "+student.getYear()+" "+student.getMailId()+" "+student.getPersonalDetails());
        }
    }

    private void showDepartments() {
        for(Department department : departmentList)
        {
            System.out.println(department);
        }
    }

    public void viewStudentByTheirDepartment()
    {
        System.out.println("Enter the department Name to search : ");
        departmentName = sc.next();
        System.out.println("Enter the year that you want to search : ");
        year = sc.nextInt();
        System.out.println("RollNumber   StudentName      DepartmentName       Year");
        for (Student student : students)
        {
            if(student.getDepartmentName().equalsIgnoreCase(departmentName) && student.getYear() == year)
            {
                System.out.printf("%-10s%10s%20s%10s\n",student.getRollNumber(),student.getStudentName(),student.getDepartmentName(),student.getYear());
            }
        }
    }

    public void viewFailStudents() {
        int failMark = 50;
        System.out.println("Enter the department name: ");
        departmentName = sc.next();
        System.out.println("Enter the year : ");
        year = sc.nextInt();
        System.out.println("Enter the sem : ");
         int sem = sc.nextInt();
        try {
            for (Student student : students) {
                if (student.getDepartmentName().equalsIgnoreCase(departmentName) && student.getYear() == year) {
                    HashMap<String, Integer> temp = student.getAllSemMarks().get(sem);
                    for (String i : temp.keySet()) {
                        int a = temp.get(i);
                        if (a <= failMark) {
                            System.out.println(student.getRollNumber() + "  " + student.getStudentName() + "  " + i + "  " + a);
                        } else {
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewStudentDetailsByYear() {
        System.out.println("Enter the year that you want : ");
        year = sc.nextInt();
        for(Student student : students)
        {
            if(student.getYear() == year)
            {
                System.out.println(student.getRollNumber()+" "+student.getStudentName()+" "+student.getDepartmentName());
            }
        }
   }

    public void searchByStudentName() {
        System.out.println("Enter the DataClass.Student Name you want to Search : ");
        name = checking.isValidName(str.nextLine());

        for(Student student : students)
        {
            if(student.getStudentName().equalsIgnoreCase(name))
            {
                System.out.println(student);
            }
        }
    }

    public void searchByStudentId() {
        System.out.println("Enter the DataClass.Student Id you want to search : ");
        id = sc.next();
        for(Student student : students)
        {
            if(student.getRollNumber().equalsIgnoreCase(id))
            {
                System.out.println(student);
            }
        }
    }

    public String checkDuplicate(String id)
    {
        for(Student student : students)
        {
            while (student.getRollNumber().equalsIgnoreCase(id))
            {
                System.out.println("Roll number is already exits..");
                id = sc.next();
            }
        }
        return id;
    }

    public String checkDepartment(String departmentName)
    {
        if(departmentList.isEmpty())
        {
            System.out.println("There is no departments..please try later");
        }
        else {
            for (Department department : departmentList) {
                while (!department.getDepartmentName().equalsIgnoreCase(departmentName)) {
                    System.out.println("No such a department is their");
                    departmentName = sc.next();
                }
            }
        }
        return departmentName;
    }

    private void viewAdmissionDetails() {
        if(admissionDetails.isEmpty())
        {
            System.out.println("There is no new admission record");
        }
        else {
            System.out.println("--------------------------------");
            System.out.println("Name   CutOff   Status   MailId ");
            System.out.println("--------------------------------");
            for (AdmissionDetails details : admissionDetails) {
                System.out.println(details);
            }
        }
        System.out.println("--------------------------------");
    }
    public void studentOperation() {
        System.out.println("Welcome to Student Menu");
        boolean on = true;
        while(on) {
            System.out.println("------------------------------");
            System.out.println("""
                    Enter your choice:\s
                    1 --> for View their details
                    2 --> for View their marks
                    3 --> for Log Out""");
            System.out.println("------------------------------");
            int choice = checking.checkIntRangeStudent();
            switch (choice) {
                case 1 -> viewTheirDetails();
                case 2 -> viewMarks();
                case 3 -> {
                    authentication.logOut();
                    on = false;
                }
            }
        }
    }

    public void viewTheirDetails() {
        System.out.println("Enter your Roll Number : ");
        id = sc.next();
        System.out.println("RollNumber    Name    Department   Year   MailId   DateOfBirth  Gender  PhoneNumber   District");
        for(Student student : students)
        {
            if(student.getRollNumber().equalsIgnoreCase(id)) {
                System.out.println(student.getRollNumber() + " " + student.getStudentName() + "  " + student.getDepartmentName() + "  " + student.getYear() + "  " + student.getMailId() + " " + student.getPersonalDetails());
            }
        }
    }

    public void viewMarks() {
        System.out.println("Enter the roll number : ");
        id = sc.next();
        System.out.println("Your Marks are : ");
        for(Student student : students) {
            if (student.getRollNumber().equalsIgnoreCase(id)) {
                System.out.println(student.getAllSemMarks());
            }
        }
    }
    public void newAdmissionOperation()
    {
        System.out.println("Welcome to New Admission Menu");
        boolean on = true;
        while(on) {
            System.out.println("-----------------------------------------------------");
            System.out.println("""
                    Enter your choice:\s
                    1 --> for View Department, Seat & cutoff marks for each department
                    2 --> for New Admission
                    3 --> for Log Out""");
            System.out.println("-----------------------------------------------------");
            int choice = checking.checkIntRangeNewAdmission();
            switch (choice) {
                case 1 -> showDepartments();
                case 2 -> newAdmissiondetails();
                case 3 -> {
                    authentication.logOut();
                    on = false;
                }

            }
        }
    }

    public void newAdmissiondetails() {
        System.out.println("Enter the student name : ");
        name = checking.isValidName(str.nextLine());
        detailsOfStudents();
        checkDuplicateAdmission();
        System.out.println("Enter the 12th Physics mark : ");
        int m1 = sc.nextInt();
        System.out.println("Enter the 12th Chemistry mark : ");
        int m2 = sc.nextInt();
        System.out.println("Enter the 12th Maths mark : ");
        int m3 = sc.nextInt();
        cutOffMark = (m1 / 2) + (m2 / 4 ) + (m3 / 4);
        System.out.println("Enter the department you want : ");
        departmentName = checkDepartment(sc.next());

        ListIterator<Department> li = departmentList.listIterator();
        while (li.hasNext()) {
            Department department = li.next();
        {
            if(department.getDepartmentName().equalsIgnoreCase(departmentName)) {
                if (department.getSeatAvailability() > 0) {
                    check1 = 1;
                    if (department.getCutOffMarks() <= cutOffMark) {
                        seats = department.getSeatAvailability();
                        System.out.println("You are selected");
                        status = "Selected";
                        seats = seats - 1;
                        li.set(new Department(department.getDepartmentId(), department.getDepartmentName(), seats, department.getCutOffMarks()));
                        check2 = 1;
                    }
                }else if(check2 == 1)
                {
                    System.out.println("Sorry , you have low cutoff marks");
                }
                else if(check1 == 1)
                {
                    System.out.println("Sorry No seats are available in this department");
                }
            }
            }
        }
        admissionDetails.add(new AdmissionDetails(name,cutOffMark,status,mailId));
        authentication.logOut();
    }

    public void checkDuplicateAdmission() {
        for(AdmissionDetails admissionDetail : admissionDetails)
        {
            while (admissionDetail.getMailId().equalsIgnoreCase(mailId))
            {
                System.out.println("duplicate found..");
            }authentication.logOut();
        }
    }

}*/
