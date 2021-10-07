package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.ServiceWorkshopsData;
import pl.com.chrzanowski.scaffolding.domain.ServiceWorkshopsFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class ServiceWorkshopsJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public ServiceWorkshopsJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(ServiceWorkshopsData data) {
        String query = "INSERT INTO service_workshops (" +
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

    public void update(ServiceWorkshopsData data) {

        String query = "UPDATE service_workshops SET " +
                "name = ?," +
                "tax_number = ?," +
                "street = ?," +
                "building_number = ?," +
                "apartment_number = ?," +
                "postal_code = ?," +
                "city = ?," +
                "modify_date =? WHERE " +
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
                data.getId());
    }

    public List<ServiceWorkshopsData> find(ServiceWorkshopsFilter filter) {

        String query = "SELECT * FROM service_workshops";

        if (filter != null) {
            query += " WHERE 1+1";

            if (filter.getId() != null) {
                query += " AND id = " + filter.getId();
            }
            if (filter.getName() != null) {
                query += " AND name = " + filter.getName();
            }
            if (filter.getStreet() != null) {
                query += " AND street = " + filter.getStreet();
            }
            if (filter.getCity() != null) {
                query += " AND city = " + filter.getCity();
            }

            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }
        }

        return prepareWorkshops(query);
    }

    private List<ServiceWorkshopsData> prepareWorkshops(String query) {
        List<Map<String,Object>> rows = jdbcTemplate.queryForList(query);
        List<ServiceWorkshopsData> list = new ArrayList<>();
        for (Map<String,Object> row : rows) {
            list.add(new ServiceWorkshopsData(
                    getLong(row,"id"),
                    getString(row,"name"),
                    getString(row,"tax_number"),
                    getString(row,"street"),
                    getString(row,"building_number"),
                    getString(row, "apartment_number"),
                    getString(row,"postal_code"),
                    getString(row,"city")
            ));
        }
        return list;
    }
}
