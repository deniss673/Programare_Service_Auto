package dao;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.car;
import model.client;

public class CarDao {
    private Connection connection = DataBaseConnection.getConnection();
    private static CarDao carDao;
    public CarDao() throws SQLException {

    }
    public static CarDao getInstance() throws SQLException {
        if (carDao == null) {
            carDao = new CarDao();
        }
        return carDao;
    }

    public void create(car car_, String username){
        String sql="INSERT INTO CAR VALUES(?,?,?,?)";
        String sql2="INSERT INTO CLIENTCARS VALUES(?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, car_.getManufacturer());
            statement.setString(2, car_.getPlate_number());
            statement.setInt(3, car_.getYear());
            statement.setString(4, car_.getEngine());
            statement.executeUpdate();
        } catch (SQLException error) {
            if (error.getErrorCode() == 1062) {
                System.out.println("Primary Key violation occurred: " + error.getMessage());
            }
            else {
                System.out.println(error.getMessage());
            }
        }

        try (PreparedStatement statement = connection.prepareStatement(sql2);) {
            statement.setString(1, username);
            statement.setString(2, car_.getPlate_number());
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

    public void deleteCar(String username){

        System.out.println("Select what car you want to delete");
        Scanner sc = new Scanner(System.in);
        String sql= "select * from clientcars where username=?";
        ResultSet rs=null;
        List<String> platen=new ArrayList<String>();
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, username);
            statement.executeQuery();
            rs=statement.getResultSet();
            int count=1;

            while(rs.next()){
                System.out.println("Car " +count+ ":" +rs.getString("plate_number"));
                platen.add(rs.getString("plate_number"));
            }
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
        }

        int choice=-1;
        choice=sc.nextInt();
        while(platen.size()<choice || choice<0 ){
            System.out.println("Please enter a valid option");
            choice=sc.nextInt();
        }

        delete(platen.get(choice-1));
    }


    public void delete(String plate_number){
        String sql="DELETE FROM CAR WHERE plate_number=?";
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, plate_number);
            statement.executeUpdate();
        }
        catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        sql="DELETE FROM CLIENTCARS WHERE plate_number=?";
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, plate_number);
            statement.executeUpdate();
        }
        catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }


    public void read(String plate_number){
        String sql="SELECT * FROM CAR WHERE plate_number=?";
        ResultSet rs = null;

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, plate_number);
            rs = statement.executeQuery();
            while (rs.next()){
                model.car car_ = new model.car(rs.getString("manufacturer"), rs.getString("plate_number"), rs.getInt("year"), rs.getString("engine"));
                car_.show_car();
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
