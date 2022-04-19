package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class CurrencyServiceDB {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createTable() {

        Connection connection;
        Statement statement;
        try{
            connection = TestDB.getConnection();
            statement = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS currency";

            statement.executeUpdate(sql);


            sql = "CREATE TABLE currency(\n" +
                    "\tid INT AUTO_INCREMENT,\n" +
                    "\tNAME VARCHAR(60) NOT NULL,\n" +
                    "\tCODE VARCHAR(5) NOT NULL,\n" +
                    "\tcreate_date datetime default NOW(),\n" +
                    "\tcreate_user_id INT,\n" +
                    "\tmodify_date datetime,\n" +
                    "\tmodify_user_id INT,\n" +
                    "\tremove_date DATETIME,\n" +
                    "\tremove_user_id INT,\n" +
                    "\tPRIMARY KEY (id),\n" +
                    "\tFOREIGN KEY (create_user_id) REFERENCES users(id),\n" +
                    "\tFOREIGN KEY (modify_user_id) REFERENCES users(id),\n" +
                    "\tFOREIGN KEY (remove_user_id) REFERENCES users(id)\n" +
                    ");";

            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        }catch (SQLException se) {
            se.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
