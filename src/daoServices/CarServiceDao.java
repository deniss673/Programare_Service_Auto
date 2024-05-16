package daoServices;

import Audit.AuditService;
import dao.AppoimentDao;
import dao.CarDao;
import dao.ClientDao;
import model.car;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CarServiceDao {
    private CarDao carDao = CarDao.getInstance();
    private ClientDao clientDao = ClientDao.getInstance();

    public CarServiceDao() throws SQLException {
    }

    public void createCar(String username) throws SQLException {
        car car_= new car();
        car_=car_.create_car();
        carDao.create(car_,username);
        AuditService.addToAudit("audit.csv", username + " Added a car in the system ");
    }

    public void deleteCar(String username) throws SQLException {
        carDao.deleteCar(username);
        AuditService.addToAudit("audit.csv", username + " deleted a car from the system ");
    }

    public void showCar(String plate_number) throws SQLException {
        carDao.read(plate_number);

    }
}
