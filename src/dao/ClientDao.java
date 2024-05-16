package dao;

import java.sql.*;


import model.client;

public class ClientDao {
    private Connection connection = DataBaseConnection.getConnection();

    private static ClientDao clDao;

    public ClientDao() throws SQLException {
    }

    public static ClientDao getInstance() throws SQLException {
        if(clDao == null) {
            clDao= new ClientDao();
        }
        return clDao;
    }



    public void show(String username) throws SQLException {
        String sql = "SELECT * FROM car c LEFT JOIN clientcars cl ON c.plate_number = cl.plate_number WHERE cl.username = ?";


        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, username);
            int count=0;
            rs = statement.executeQuery();
            while (rs.next()){
                System.out.println(count+".");
                model.car car_ = new model.car(rs.getString("manufacturer"), rs.getString("plate_number"), rs.getInt("year"), rs.getString("engine"));
                car_.show_car();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void setMembership(String username) throws SQLException {
        String sql="update client SET membership = 1 where username = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, username);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
