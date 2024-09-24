package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DBManagement {

    private static Connection connection;
    private static PreparedStatement statement;

    public static Connection ConToDB(){

    String userName = "root";
    String password = "root";
    String url = "jdbc:sqlserver://DESKTOP-H8O0MRC\\SQLEXPRESS:1434;database=mokykla;trustServerCertificate=true;";
    connection = null;
        try {
        connection = DriverManager.getConnection(url, userName, password);
    } catch (
    SQLException ex) {
        ex.printStackTrace();
    }
        return connection;
    }

    public static void disconFromDB (Connection connection, Statement statement){
        try{
            if(connection != null && statement != null){
                connection.close();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
