package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDao;

public class user {
    private String first_name;
    private String last_name;
    private String phone_number;
    private String user_name;
    private String password;

    public user(String first_name, String last_name, String phone_number, String user_name, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.user_name = user_name;
        this.password = password;
    }
    public user(){

    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {

        return phone_number;
    }
    public void setPhone_number(String phone_number) {

        this.phone_number = phone_number;
    }

    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getPassword() {
        return password;
    }






    public static user create_account(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println("First Name: ");
        String first_name = scanner.nextLine();
        System.out.println("Last Name: ");
        String last_name = scanner.nextLine();
        System.out.println("Phone Number: ");
        String phone_number = scanner.nextLine();

        System.out.println("Select a payment method, you can change that later:");
        System.out.println("1.Card");
        System.out.println("2.Cash");

        int choice = scanner.nextInt();
        String payment;
        switch(choice){
            case 1:
                payment="Card";
                break;
            case 2:
                payment="Cash";
                break;
            default:
                payment="N/A";
                break;
        }
        user temp_person= new user(first_name,last_name,phone_number,username,password);


        return temp_person;

    }
}
