package Model;


import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnection {

    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:HabitatForHumanity.sqlite");
            return connection;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
