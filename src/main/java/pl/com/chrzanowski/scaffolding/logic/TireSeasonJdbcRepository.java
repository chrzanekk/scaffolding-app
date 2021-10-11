package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.TireSeasonData;
import pl.com.chrzanowski.scaffolding.domain.TireSeasonFilter;

import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.preparePaginationQuery;

@Service
public class TireSeasonJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public TireSeasonJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(TireSeasonData data) {
        String query = "INSERT INTO tire_season (name) VALUES (?);";
        jdbcTemplate.update(query, data.getName());
        return commonJdbcRepository.getLastInsertedId();
    }

    public void update(TireSeasonData data) {
        String query = "UPDATE tire_season SET name =?, modify_date = ? WHERE id = ?;";
        jdbcTemplate.update(query,data.getName(),data.getModifyDate());
    }

    public List<Map<String, Object>> find(TireSeasonFilter filter) {

        String query = "SELECT * FROM tire_season";

        if (filter != null) {
            query += " WHERE 1+1";

            if (filter.getId() != null) {
                query += " AND id = " + filter.getId();
            }

            if (filter.getName() != null) {
                query += " AND name = " + filter.getName();
            }

            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }
        }
        return jdbcTemplate.queryForList(query);
    }
}
