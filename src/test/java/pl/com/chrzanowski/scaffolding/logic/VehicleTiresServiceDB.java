package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.Statement;

@Service
public class VehicleTiresServiceDB {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createTables() {
        Connection connection;
        Statement statement;

        try{
            connection = ConnectToTestDB.getConnection();
            statement = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS tire_season";
            statement.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS tires";
            statement.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS vehicle_tires";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE tire_season(" +
                    "id int auto_increment, " +
                    "name varchar(20), " +
                    "create_date datetime default now(), " +
                    "modify_date datetime, " +
                    "remove_date datetime, " +
                    "primary key (id))";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE tires (\n" +
                    "\tid int auto_increment,\n" +
                    "\tbrand varchar(50) not null,\n" +
                    "\tmodel varchar(40) not null,\n" +
                    "\twidth int not null,\n" +
                    "\tprofile int not null,\n" +
                    "\tdiameter int not null,\n" +
                    "\tspeed_index varchar(2) not null,\n" +
                    "\tcapacity_index int not null,\n" +
                    "\treinforced varchar(5) not null,\n" +
                    "\trun_on_flat boolean not null,\n" +
                    "\tseason_id int not null,\n" +
                    "\tcreate_date datetime default now(),\n" +
                    "\tmodify_date datetime,\n" +
                    "\tremove_date datetime," +
                    "\tprimary key (id), \n" +
                    "\tforeign key (season_id) references tire_season(id))";
            statement.executeUpdate(sql);

            sql = "ALTER TABLE tires \n" +
                    "ADD type VARCHAR(2) NOT NULL";
            statement.executeUpdate(sql);


            sql = "CREATE TABLE vehicles (\n" +
                    "    id int AUTO_INCREMENT, \n" +
                    "    brand_id int not null, \n" +
                    "    model_id int not null, \n" +
                    "    registration_number varchar(15) not null, \n" +
                    "    vin varchar(20) not null, \n" +
                    "    production_year int not null,\n" +
                    "    first_registration_date date not null,\n" +
                    "    free_places_for_technical_inspections int not null,\n" +
                    "    fuel_type_id int not null,\n" +
                    "    vehicle_type_id int not null,\n" +
                    "\tcreate_date datetime default now(),\n" +
                    "\tmodify_date datetime,\n" +
                    "\tremove_date datetime,\n" +
                    "\tprimary key (id))";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE vehicle_tires(\n" +
                    "\tid int auto_increment,\n" +
                    "\tvehicle_id int not null,\n" +
                    "\ttire_id int not null,\n" +
                    "\tstatus varchar(30),\n" +
                    "\tproduction_year int not null,\n" +
                    "\tpurchase_date date not null,\n" +
                    "\tcreate_date datetime default now(),\n" +
                    "\tmodify_date datetime,\n" +
                    "\tremove_date datetime,\n" +
                    "\tprimary key (id),\n" +
                    "\tforeign key (vehicle_id) references vehicles(id),\n" +
                    "\tforeign key (tire_id) references tires(id))";
            statement.executeUpdate(sql);


            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
