package crawler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class datalayer {
    Connection dbConnection = null;

    public void createDatabaseConnetion() {
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://<filepath>?user=<userhere>&password=<passwordhere>");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public int readFromURLS() {
        try {
            Statement clicks = dbConnection.createStatement();
            ResultSet results = clicks.executeQuery("SELECT * FROM newurls");
            if (results.next()) {
                //get string 
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return 0;
    }
}
