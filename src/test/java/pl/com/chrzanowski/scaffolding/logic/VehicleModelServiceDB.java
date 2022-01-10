package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class VehicleModelServiceDB {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createTable() {
        Connection connection;
        Statement statement;
        try{
            connection = ConnectToTestDB.getConnection();
            statement = connection.createStatement();

//            String sql = "DROP TABLE IF EXISTS vehicle_brand";
//            statement.executeUpdate(sql);

            String sql = "DROP TABLE IF EXISTS vehicle_model";
            statement.executeUpdate(sql);

//            sql = "CREATE TABLE vehicle_brand (\n" +
//                    "\tid int auto_increment,\n" +
//                    "\tname varchar(30) not null,\n" +
//                    "\tcreate_date datetime default now(),\n" +
//                    "\tmodify_date datetime,\n" +
//                    "\tremove_date datetime)";
//            statement.executeUpdate(sql);
//
//            sql = "ALTER TABLE vehicle_brand ADD PRIMARY KEY (id)";
//            statement.executeUpdate(sql);

            sql = "CREATE TABLE vehicle_model (\n" +
                    "\tid int auto_increment,\n" +
                    "\tbrand_id int not null,\n" +
                    "\tname varchar(30),\n" +
                    "\tprimary key (id),\n" +
                    "\tcreate_date datetime default now(),\n" +
                    "\tmodify_date datetime,\n" +
                    "\tremove_date datetime)";
            statement.executeUpdate(sql);

//            sql = "ALTER TABLE vehicle_model ADD FOREIGN KEY (brand_id) REFERENCES vehicle_brand";
//            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
