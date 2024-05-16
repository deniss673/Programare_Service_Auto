package Audit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditService {
    public static void addToAudit(String audit, String operation) {

        try (FileWriter write = new FileWriter(audit, true);
            BufferedWriter buff = new BufferedWriter(write)) {
            buff.write(operation);
            buff.write(" Date:");
            buff.write(LocalDateTime.now()+"");
            buff.newLine();
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }
}
