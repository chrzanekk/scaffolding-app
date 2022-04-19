package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class UserServiceDB {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createTable() {
        Connection connection;
        Statement statement;
        try {
            connection = TestDB.getConnection();
            statement = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS users";

            statement.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS user_authorities";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE users(\n" +
                    "\t\tid int auto_increment,\n" +
                    "\t\tlogin varchar(255) not null UNIQUE,\n" +
                    "\t\tpassword_hash varchar(200) not null,\n" +
                    "\t\tfirst_name varchar(50) not null,\n" +
                    "\t\tsecond_name varchar(75) not null,\n" +
                    "\t\tlanguage varchar(20) not null,\n" +
                    "\t\tregulation_accepted boolean,\n" +
                    "\t\tnewsletter_accepted boolean,\n" +
                    "\t\tis_enabled boolean,\n" +
                    "\t\tregistration_datetime datetime default now(),\n" +
                    "\t\tregistration_ip varchar(200),\n" +
                    "\t\tregistration_user_agent varchar(1000),\n" +
                    "\t\temail_confirmed boolean not null,\n" +
                    "\t\tdelete_datetime datetime,\n" +
                    "\t\tprimary key (id)\n" +
                    "\t\t);";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE user_authorities(\n" +
                    "\t\tid int auto_increment,\n" +
                    "\t\tuser_id int not null,\n" +
                    "\t\tauthority varchar(50),\n" +
                    "\t\tcreate_date datetime default now(),\n" +
                    "\t\tmodify_date datetime,\n" +
                    "\t\tremove_date datetime,\n" +
                    "\t\tprimary key (id),\n" +
                    "\t\tforeign key (user_id) REFERENCES users(id)\n" +
                    ");\t\n";
            statement.executeUpdate(sql);

//            sql = "INSERT INTO users(" +
//                    "login, " +
//                    "password_hash, " +
//                    "first_name, " +
//                    "second_name, " +
//                    "language, " +
//                    "regulation_accepted, " +
//                    "newsletter_accepted, " +
//                    "is_enabled, " +
//                    "registration_datetime, " +
//                    "registration_ip, " +
//                    "registration_user_agent, " +
//                    "email_confirmed, " +
//                    "delete_datetime) VALUES (" +
//                    " 'admi@admin.admin',  " +
//                    " '1111',  " +
//                    " 'kondzio', " +
//                    " 'adminowski', " +
//                    " 'pl', " +
//                    " 'true', " +
//                    " 'true', " +
//                    " 'true', " +
//                    " null, " +
//                    " null, " +
//                    " null, " +
//                    " 'true', " +
//                    " null);";
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
