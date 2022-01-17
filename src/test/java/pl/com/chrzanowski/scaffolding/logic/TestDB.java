package pl.com.chrzanowski.scaffolding.logic;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class TestDB {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:mem:testdb";
    static final String USER = "sa";
    static final String PASSWORD = "";


    public static Connection getConnection() {
        Connection connection = null;
        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
