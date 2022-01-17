package pl.com.chrzanowski.scaffolding.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DbTables {

    public void createTireSeasons(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String sql = "DROP TABLE IF EXISTS tire_season";
        statement.executeUpdate(sql);

        statement = connection.createStatement();
        sql = "CREATE TABLE tire_season(" +
                "id int auto_increment, " +
                "name varchar(20), " +
                "create_date datetime default now(), " +
                "modify_date datetime, " +
                "remove_date datetime, " +
                "primary key (id))";
        statement.executeUpdate(sql);
    }
}
