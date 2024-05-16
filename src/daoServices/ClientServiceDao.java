package daoServices;

import Audit.AuditService;
import dao.ClientDao;

import java.sql.SQLException;

public class ClientServiceDao {
    private ClientDao clDao = ClientDao.getInstance();

    public ClientServiceDao() throws SQLException {

    }

    public void show(String username) throws SQLException {
        clDao.show(username);
        AuditService.addToAudit("audit.csv", username + " saw their cars ");
    }

    public void setMembership(String username) throws SQLException {
        clDao.setMembership(username);
        AuditService.addToAudit("audit.csv", username + " bought membership ");
    }

}
