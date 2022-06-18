package DataSource;

import UtilClass.Checking;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    static void menu() {
        System.out.println("""
                1) for Admin
                2) for New Admission
                3) for Student
                4) for ClassIncharge(Faculty)
                5) for menu
                6) for Exit the program""");
    }

    public static void main(String[] args) throws Exception {
        boolean main = true;
        int choice;
        StudentFunction1 object = StudentFunction1.getInstance();
        Checking check = new Checking();
        menu();
        while (main) {
            System.out.println("Enter your choice : ");
            choice = check.inputChoice();
            switch (choice) {
                case 1 -> object.admin();
                case 2 -> object.newAdmission();
                case 3 -> object.student();
                case 4 -> object.facultyOperation();
                case 5 -> menu();
                case 6-> {
                    object.close();
                    System.out.println("Program finished");
                    main = false;
                }
                default -> System.out.println("You enter the wrong number");

            }
        }
    }

}

