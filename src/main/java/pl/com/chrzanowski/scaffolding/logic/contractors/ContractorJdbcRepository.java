package pl.com.chrzanowski.scaffolding.logic.contractors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorData;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.preparePaginationQuery;

@Service
public class ContractorJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public ContractorJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(ContractorData data) {
        String query = "INSERT INTO contractors (" +
                "name," +
                "contractor_type," +
                "tax_number," +
                "street," +
                "building_number," +
                "apartment_number," +
                "postal_code," +
                "city," +
                "country," +
                "bank_account_number," +
                "description," +
                "create_user_id" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.update(query,
                data.getName(),
                data.getContractorType(),
                data.getTaxNumber(),
                data.getStreet(),
                data.getBuildingNo(),
                data.getApartmentNo(),
                data.getPostalCode(),
                data.getCity(),
                data.getCountry(),
                data.getBankAccount(),
                data.getDescription(),
                data.getCreateUserId());
        return commonJdbcRepository.getLastInsertedId();
    }

    public void update(ContractorData data) {
        String query = "UPDATE contractors SET " +
                "name = ?," +
                "contractor_type = ?," +
                "tax_number = ?," +
                "street = ?," +
                "building_number = ?," +
                "apartment_number = ?," +
                "postal_code = ?," +
                "city = ?," +
                "country = ?," +
                "bank_account = ?," +
                "description = ?," +
                "modify_date = ?," +
                "modify_user_id = ?," +
                "remove_date = ?," +
                "remove_user_id = ? WHERE id = ?";
        jdbcTemplate.update(query,
                data.getName(),
                data.getContractorType(),
                data.getTaxNumber(),
                data.getStreet(),
                data.getBuildingNo(),
                data.getApartmentNo(),
                data.getPostalCode(),
                data.getCountry(),
                data.getBankAccount(),
                data.getDescription(),
                data.getModifyDate(),
                data.getModifyUserId(),
                data.getRemoveDate(),
                data.getRemoveUserId(),
                data.getId());
    }

    public List<Map<String, Object>> find(ContractorFilter filter) {
        String query = "SELECT * FROM contractors";

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
            if (filter.getCountry() != null) {
                query += " AND country = '" + filter.getCountry() + "'";
            }
            if (filter.getTaxNumber() != null) {
                query += " AND tax_number = '" + filter.getTaxNumber() + "'";
            }
            if (filter.getContractorType() != null) {
                query += " AND contractor_type = '" + filter.getContractorType() + "'";
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
