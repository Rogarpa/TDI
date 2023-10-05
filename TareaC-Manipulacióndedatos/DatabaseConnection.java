import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Class for getting connections to mysql database servlets on 3036 port
 */
public class DatabaseConnection{
    private static final String URL = "jdbc:mysql://localhost:3306/servlets?characterEncoding=latin1&useConfigs=maxPerformance";
    private static final String USER = "algo";
    private static final String PASSWORD = "algo";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}