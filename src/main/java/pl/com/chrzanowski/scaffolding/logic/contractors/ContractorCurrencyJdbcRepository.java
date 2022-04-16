package pl.com.chrzanowski.scaffolding.logic.contractors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorCurrencyData;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorCurrencyFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.List;
import java.util.Map;

@Service
public class ContractorCurrencyJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public ContractorCurrencyJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    void create(ContractorCurrencyData data) {
        String query = "INSERT INTO contractors_currency (contractor_id, currency_id) VALUES (?,?)";
        Long[] currencyList = data.getCurrencyList();
        for (Long currency : currencyList) {
            jdbcTemplate.update(query, data.getContractorId(), currency);
        }
    }

    void deleteCurrency(ContractorCurrencyFilter filter) {
        String query = "DELETE FROM contractors_currency WHERE contractor_id = " + filter.getContractorId();
        jdbcTemplate.update(query);
    }

    public List<Map<String, Object>> find(ContractorCurrencyFilter filter) {
        String query = "SELECT * FROM contractors_currency JOIN currency ON (contractors_currency.currency_id = " +
                "currency.id)";

        if (filter != null) {
            query += " WHERE 1=1";

            if (filter.getCurrencyId() != null) {
                query += " AND currency_id = '" + filter.getCurrencyId() + "'";
            }
            if (filter.getContractorId() != null) {
                query += " AND contractor_id = '" + filter.getContractorId() + "'";
            }
        }
        return jdbcTemplate.queryForList(query);
    }
}
