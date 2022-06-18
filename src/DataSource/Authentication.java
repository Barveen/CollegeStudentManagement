package DataSource;

import DataClass.UserDetails;
import UtilClass.Checking;

import java.sql.SQLException;
import java.util.Scanner;

public class Authentication {

    Checking checking = new Checking();
    DBConnection dbConnection = new DBConnection();
    Scanner sc = new Scanner(System.in);
    String userId;
    String password;
    String name;
    static long idCounter = 1;
    static long newAdmissionId = 100;
    static long studentId = 1000;
    static int facultyId = 2000;

    public void login() throws SQLException {
        Scanner userInput = new Scanner(System.in);
        Scanner userPass = new Scanner(System.in);
        String passwrod;
        System.out.println("Enter your user Id : ");
        userId = userInput.next();
        System.out.println("Enter your PassWord: ");
        passwrod = sc.next();
        UserDetails userDetails1;
        userDetails1 = dbConnection.userSignIn(userId);
        if(userDetails1.getUserId().equalsIgnoreCase(userId)) {
            if(userDetails1.getPassword().equalsIgnoreCase(passwrod))
            {
                System.out.println("Your successfully logged in");
            }else
            {
                System.out.println("Your password is wrong , Re-enter the password");
                userPass.next();
            }
        }else
        {
            System.out.println("Your user name is wrong , re enter your user name : ");
            userInput.next();
        }
    }

    public void signup() throws SQLException {
        System.out.println("Enter your name : ");
        name = checking.isValidName(sc.next());
        System.out.println("""
                Enter\s
                 1 --> for Admin
                 2 --> for Student
                 3 --> for New Admission
                 4 --> for Faculty""");
        int type = sc.nextInt();
        if(type == 1)
        {
            userId = createAdminId();
            System.out.println("Your user Id is : "+userId);

        }
        else if(type == 2)
        {
            userId = createStudentId();
            System.out.println("Your user Id is : "+userId);
        }else if(type == 3)
        {
            userId = createNewAdmissionId();
            System.out.println("Your user Id is : "+userId);
        }
        else if(type == 4)
        {
            userId = createFacultyId();
            System.out.println("Your user Id is : "+userId);
        }
        System.out.println("Enter the password : ");
        password = checking.isValidPassWord(sc.next());
        dbConnection.newUser(userId,password);
        System.out.println("Please sign in and use your account");
        login();
    }

    void logOut() {
        System.out.println("........Successfully Logged Out.......");
        Main.menu();
    }

    public static  String createAdminId()
    {

        return String.valueOf(idCounter++);
    }

    public static  String createNewAdmissionId()
    {
        return String.valueOf(newAdmissionId++);
    }

    public static  String createStudentId()
    {
        return String.valueOf(studentId++);
    }

    public static  String createFacultyId()
    {
        return String.valueOf(facultyId++);
    }

}
