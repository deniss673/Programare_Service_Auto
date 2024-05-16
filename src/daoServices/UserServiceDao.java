package daoServices;

import Audit.AuditService;
import dao.ClientDao;
import dao.UserDao;
import model.user;

import java.sql.SQLException;
import java.util.Scanner;

public class UserServiceDao {
    private UserDao userDao = UserDao.getInstance();


    public UserServiceDao() throws SQLException {

    }

    public user register(){
        user user_= new user();
        user_=user_.create_account();
        AuditService.addToAudit("audit.csv", user_.getUser_name() + " registered ");
        return user_;
    }

    public user login(){
        System.out.println("Username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();



        while (!userDao.checkUsername(username)){
            System.out.println("Username: ");
            username = scanner.nextLine();
        }
        System.out.println("Password: ");
        String password = scanner.nextLine();
        while(!userDao.checkPassword(username,password)){
            System.out.println("Password: ");
            password = scanner.nextLine();
        }
        System.out.println("Logged in successfully");

        user current_user=null;
        try{
            current_user=userDao.getUser(username);
        }
        catch(Exception e){
            System.out.println(e);
        }
        AuditService.addToAudit("audit.csv", username + " has logged in the system ");
        return current_user;
    }

    public int getRole(String username){
        try{
            return userDao.getRole(username);
        }
        catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }

    public void delete(String username){
        try{
            userDao.delete(username);
        }
        catch(Exception e){
            System.out.println(e);
        }
        AuditService.addToAudit("audit.csv", username + " deleted their account ");
    }

}
