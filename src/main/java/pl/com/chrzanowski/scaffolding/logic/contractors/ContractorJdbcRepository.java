package pl.com.chrzanowski.scaffolding.logic.contractors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorData;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

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


}
