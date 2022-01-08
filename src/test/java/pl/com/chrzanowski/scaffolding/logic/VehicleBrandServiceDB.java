package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class VehicleBrandServiceDB {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:mem:testdb";
    static final String USER = "sa";
    static final String PASSWORD = "";

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createVehicleBrandTable() {
        Connection connection;
        Statement statement;
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to db...");
            connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            statement = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS vehicle_brand";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE vehicle_brand (\n" +
                    "\tid int auto_increment,\n" +
                    "\tname varchar(30) not null,\n" +
                    "\tcreate_date datetime default now(),\n" +
                    "\tmodify_date datetime,\n" +
                    "\tremove_date datetime,\n" +
                    "\tprimary key (id))";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
