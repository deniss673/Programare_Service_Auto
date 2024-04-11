package model;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class persoana {
    private String first_name;
    private String last_name;
    private String phone_number;
    private String user_name;
    private String password;

    public persoana(String first_name, String last_name, String phone_number, String user_name, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.user_name = user_name;
        this.password = password;
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

    public static boolean check_username(List<persoana> users,String username) {
        for(persoana usr : users) {
            if(Objects.equals(username,usr.getUser_name()))
                return true;
        }
        return false;
    }

    public static boolean check_password(List<persoana> users,String username, String password,persoana current_user) {
        for(persoana usr : users) {
            if(Objects.equals(username,usr.getUser_name()) && Objects.equals(password,usr.getPassword())){
                current_user = usr;
                return true;
                }
        }
        return false;
    }

    public static int get_role(List<persoana> users,String username, String password) {
        int role=0;
        for(persoana usr : users) {
            if(Objects.equals(username,usr.getUser_name()) && Objects.equals(password,usr.getPassword()))
                if(usr instanceof employee){
                    role=1;
                }
                else{
                    role=2;
                }

        }
        return role;
    }

    public static int log_in(List<persoana> users,persoana current_user){
        System.out.println("Username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        while (!check_username(users,username)){
            System.out.println("User not found");
            System.out.println("Username: ");
            username = scanner.nextLine();
        }
        System.out.println("Password: ");
        String password = scanner.nextLine();
        while(!check_password(users,username,password,current_user)){
            System.out.println("Wrong password, try again");
            System.out.println("Password: ");
            password = scanner.nextLine();
        }
        System.out.println("Logged in successfully");
        return get_role(users,username,password);
    }

    public static client create_account(){
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
        }
        persoana temp_person= new persoana(first_name,last_name,phone_number,username,password);

        return new client(temp_person);

    }
}
