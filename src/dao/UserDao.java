package dao;

import java.sql.*;

import model.user;

public class UserDao {
    private Connection connection = DataBaseConnection.getConnection();
    private static UserDao userDao;

    public UserDao() throws SQLException {
    }

    public static UserDao getInstance() throws SQLException {
        if(userDao == null) {
            userDao= new UserDao();
        }
        return userDao;
    }



    public void createAccount(user user_){
        String sql= "insert into user values(?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, user_.getFirst_name());
            statement.setString(2, user_.getLast_name());
            statement.setString(3, user_.getPhone_number());
            statement.setString(4, user_.getUser_name());
            statement.setString(5, user_.getPassword());
            statement.executeUpdate();
        } catch (SQLException error) {
            if (error.getErrorCode() == 1062) {
                System.out.println("Primary Key violation occurred: " + error.getMessage());
            }
            else {
                System.out.println(error.getMessage());
            }
        }
        sql= "insert into client values(?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, "N/A");
            statement.setInt(2, 0);
            statement.setString(3, user_.getUser_name());
            statement.executeUpdate();
        } catch (SQLException error) {
            if (error.getErrorCode() == 1062) {
                System.out.println("Primary Key violation occurred: " + error.getMessage());
            }
            else {
                System.out.println(error.getMessage());
            }
        }



    }

    public boolean checkUsername(String username){
        String sql= "select * from user where user_name = ?";

        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, username);
            rs = statement.executeQuery();
            if (rs!=null){
                return true;
            }
        } catch (SQLException e) {
            System.out.println("User not found");
            System.out.println(e.getMessage());
            return false;
        }

        System.out.println("User not found");
        return false;
    }

    public boolean checkPassword(String username,String password){
        String sql= "select * from user where user_name = ? and password = ?";

        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, username);
            statement.setString(2, password);
            rs = statement.executeQuery();
            while (rs.next()){
               return true;
            }
        } catch (SQLException e) {
            System.out.println("Wrong password, try again");

            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("Wrong password, try again");
        return false;
    }

    public user getUser(String username) throws SQLException{
        String sql= "select * from user where user_name = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1,username);
            rs=statement.executeQuery();
            while (rs.next()){
                user usr= new user(rs.getString("first_name"),rs.getString("last_name"),rs.getString("phone_number"),rs.getString("user_name"),rs.getString("password"));
                return usr;
            }
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            return null;
        }
        return null;
    }


    public int getRole(String username) throws SQLException{
        String sql= "select * from client where username = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, username);
            rs = statement.executeQuery();
            if(rs.next()){
                return 2;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 1;
        }

        return 1;
    }

    public void delete(String username){
        String sql= "delete from user where user_name = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, username);
            statement.executeUpdate();
        }
        catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        sql= "delete from client where user_name = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, username);
            statement.executeUpdate();
        }
        catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        sql= "delete from clientcars where user_name = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, username);
            statement.executeUpdate();
        }
        catch (SQLException error) {
            System.out.println(error.getMessage());
        }

    }

}
