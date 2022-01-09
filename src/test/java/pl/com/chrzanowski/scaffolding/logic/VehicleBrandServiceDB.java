package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class VehicleBrandServiceDB {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createVehicleBrandTable() {
        Connection connection;
        Statement statement;
        try{
            connection = ConnectToTestDB.connectToTestDB();
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
