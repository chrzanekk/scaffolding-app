package pl.com.chrzanowski.scaffolding.logic.vehiclebrands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.vehiclebrands.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.vehiclebrands.VehicleBrandFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Service
public class VehicleBrandJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public VehicleBrandJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(VehicleBrandData data) {
        KeyHolder holder = new GeneratedKeyHolder();
        String query = "INSERT INTO vehicle_brand (name) VALUES (?)";
//        jdbcTemplate.update(query,data.getName());
        jdbcTemplate.update(connection -> {
            final PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, data.getName());
            return statement;
        }, holder);

        log.info("Saved brand {} with id {}", data.getName(), holder.getKey());

        return holder.getKey().longValue();
    }

    public void update(VehicleBrandData data) {
        String query = "UPDATE vehicle_brand SET name = ?, modify_date = ? WHERE id = ?;";
        jdbcTemplate.update(query,data.getName(), data.getModifyDate() ,data.getId());
    }

    List<Map<String, Object>> find(VehicleBrandFilter filter) {

        String query = "SELECT * FROM vehicle_brand";

        if (filter != null) {
            query += " WHERE 1+1";

            if (filter.getId() != null) {
                query += " AND id = '" + filter.getId() + "'";
            }
            if (filter.getName() != null) {
                query += " AND name = '" + filter.getName() + "'";
            }
        }
        return jdbcTemplate.queryForList(query);
    }




}
