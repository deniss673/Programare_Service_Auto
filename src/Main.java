import java.sql.*;


public class Main {
    public static void main(String[] args) {


        int user = 0;
        Serivce service = new Serivce();
        while (user != -1) {
            int command = service.service_options(user);
            user = service.command(command, user);
        }
        System.out.println("Have a nice day!");


    }
}
