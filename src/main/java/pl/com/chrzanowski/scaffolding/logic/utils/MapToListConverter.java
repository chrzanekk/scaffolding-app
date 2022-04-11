package pl.com.chrzanowski.scaffolding.logic.utils;

import pl.com.chrzanowski.scaffolding.domain.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;

public class MapToListConverter {

    public MapToListConverter() {
    }

    public static List<UserData> getUserList(List<Map<String,Object>> data) {
            List<UserData> users = new ArrayList<>();

            for (Map<String, Object> row : data) {
                users.add(new UserData(
                        getLong(row, "id"),
                        getString(row, "first_name"),
                        getString(row, "second_name"),
                        getString(row, "login"),
                        getString(row, "password_hash"),
                        getString(row, "language"),
                        getBoolean(row, "regulation_accepted"),
                        getBoolean(row, "newsletter_accepted"),
                        getBoolean(row, "is_enabled"),
                        getDateTime(row, "registration_datetime"),
                        getString(row, "registration_ip"),
                        getString(row, "registration_user_agent"),
                        getBoolean(row, "email_confirmed")
                ));
            }
            return users;
    }


}
