package dao;

import model.car;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.client;


public class AppoimentDao {
    private Connection connection = DataBaseConnection.getConnection();
    private static AppoimentDao appDao;

    public AppoimentDao() throws SQLException {

    }

    public static AppoimentDao getInstance() throws SQLException {
        if(appDao == null) {
            appDao= new AppoimentDao();
        }
        return appDao;
    }

    public void create(String username) throws SQLException {
        String sql="select * from clientcars where username=?";
        ResultSet rs=null;
        List<String> cars=new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, username);
            rs = statement.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No cars found");
                return;
            }

            int count=1;
            while (rs.next()){
                System.out.println(count+".");
                String plate_number = rs.getString("plate_number");
                System.out.println(plate_number);
                cars.add(plate_number);
                count++;
            }
        }
        catch(Exception e){
            System.out.println(e);
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Select your car:");
        int choice= sc.nextInt();
        String plate_number;

        while(choice >cars.size() || choice <1){
            System.out.println("Select a valid car!");
            choice= sc.nextInt();

        }
        plate_number=cars.get(choice-1);
        Date date=new Date();
        sc.nextLine();
        System.out.println("Sumbit the car problems: ");
        String problems=sc.nextLine();

        sql="Select count(*) from appoiment";
        rs=null;
        int id=0;
        try(PreparedStatement statement = connection.prepareStatement(sql);){
            rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getInt("count(*)");
            }
        }
        catch(Exception e){

            System.out.println(e.getMessage());
            return;

        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        sql="insert into appoiment values(?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setDate(1, sqlDate);
            statement.setInt(2, -1);
            statement.setString(3,problems);
            statement.setInt(4, id);
            statement.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
            return;
        }

        sql="insert into car_appoiment values(?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setInt(1, id);
            statement.setString(2, plate_number);
            statement.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
            return;
        }

        return;
    }

    public void show(String username) throws SQLException {
        String sql="select * from appoiment app LEFT JOIN car_appoiment c ON app.appoment_id=c.appoiment_id LEFT JOIN clientcars ca ON ca.plate_number = c.plate_number where ca.username=?";
        try(PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, username);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("appoiment_id"));
                System.out.println(rs.getString("problems"));
                System.out.println(rs.getDate("date"));
                System.out.println(rs.getInt("expected_days"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void delete(String username) throws SQLException {
        String sql="select * from appoiment app LEFT JOIN car_appoiment c ON app.appoment_id=c.appoiment_id LEFT JOIN clientcars ca ON ca.plate_number = c.plate_number where ca.username=?";
        List<Integer> apps_id= new ArrayList<Integer>();
        try(PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, username);
            ResultSet rs=statement.executeQuery();
            int count=1;
            while(rs.next()){
                System.out.println(count+".");
                System.out.println(rs.getInt("appoiment_id"));
                System.out.println(rs.getString("problems"));
                System.out.println(rs.getDate("date"));
                System.out.println(rs.getInt("expected_days"));
                apps_id.add(rs.getInt("appoiment_id"));
                count++;
            }
        }
        catch(Exception e){
            System.out.println(e);
            return;
        }
        System.out.println("Choose appoiment to delete:");
        Scanner sc = new Scanner(System.in);
        int choice= sc.nextInt();

        while(choice >apps_id.size() || choice <1){
            System.out.println("Select a valid appoiment!");
        }
        int id=apps_id.get(choice-1);
        sql="delete from appoiment where appoment_id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
        sql="delete from car_appoiment where appoiment_id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setInt(1, id);
            statement.executeUpdate();

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
