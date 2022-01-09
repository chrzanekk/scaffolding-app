package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ServiceActionTypeServiceDB {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createServiceTypeTable() {
        Connection connection;
        Statement statement;
        try{
            connection = ConnectToTestDB.connectToTestDB();
            statement = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS service_action_type";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE service_action_type (\n" +
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



