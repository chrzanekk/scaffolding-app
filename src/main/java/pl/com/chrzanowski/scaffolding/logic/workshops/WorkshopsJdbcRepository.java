package pl.com.chrzanowski.scaffolding.logic.workshops;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.workshops.WorkshopsData;
import pl.com.chrzanowski.scaffolding.domain.workshops.WorkshopsFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.preparePaginationQuery;

@Service
public class WorkshopsJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public WorkshopsJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(WorkshopsData data) {
        String query = "INSERT INTO workshops (" +
                "name," +
                "tax_number," +
                "street," +
                "building_number," +
                "apartment_number," +
                "postal_code," +
                "city) VALUES (" +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?) ";
        jdbcTemplate.update(query,
                data.getName(),
                data.getTaxNumber(),
                data.getStreet(),
                data.getBuildingNo(),
                data.getApartmentNo(),
                data.getPostalCode(),
                data.getCity());
        return commonJdbcRepository.getLastInsertedId();
    }

    public void update(WorkshopsData data) {

        String query = "UPDATE workshops SET " +
                "name = ?," +
                "tax_number = ?," +
                "street = ?," +
                "building_number = ?," +
                "apartment_number = ?," +
                "postal_code = ?," +
                "city = ?," +
                "modify_date = ?, " +
                "remove_date = ? WHERE " +
                "id = ?";
        jdbcTemplate.update(query,
                data.getName(),
                data.getTaxNumber(),
                data.getStreet(),
                data.getBuildingNo(),
                data.getApartmentNo(),
                data.getPostalCode(),
                data.getCity(),
                data.getModifyDate(),
                data.getRemoveDate(),
                data.getId());
    }


    public List<Map<String, Object>> find(WorkshopsFilter filter) {

        String query = "SELECT * FROM workshops";

        if (filter != null) {
            query += " WHERE 1=1";

            if (filter.getId() != null) {
                query += " AND id = '" + filter.getId() + "'";
            }
            if (filter.getName() != null) {
                query += " AND name = '" + filter.getName() + "'";
            }
            if (filter.getStreet() != null) {
                query += " AND street = '" + filter.getStreet() + "'";
            }
            if (filter.getCity() != null) {
                query += " AND city = '" + filter.getCity() + "'";
            }
            if (filter.getItContainsRemoveDate() != null) {
                if (!filter.getItContainsRemoveDate()) {
                    query += " AND remove_date IS NULL ";
                } else {
                    query += " AND remove_date IS NOT NUll ";
                }
            }
            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }
        }

        return jdbcTemplate.queryForList(query);
    }

}
