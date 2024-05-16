package daoServices;

import java.sql.*;
import dao.AppoimentDao;

import model.client;
import Audit.AuditService;

public class AppoimentServiceDao {

    private AppoimentDao appDao = AppoimentDao.getInstance();

    public AppoimentServiceDao() throws SQLException {

    }

    public void createAppoiment(String username) throws SQLException{
        appDao.create(username);
        AuditService.addToAudit("audit.csv", username + " created an appoiment");
    }

    public void showAppoiment(String username) throws SQLException{
        appDao.show(username);

        AuditService.addToAudit("audit.csv", username + " viewed all appoiments");
    }

    public void deleteAppoiment(String username) throws SQLException{
        appDao.delete(username);

        AuditService.addToAudit("audit.csv", username + " deleted an appoiment");
    }

}
