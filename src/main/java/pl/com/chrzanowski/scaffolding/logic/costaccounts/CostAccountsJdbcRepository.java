package pl.com.chrzanowski.scaffolding.logic.costaccounts;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.costaccounts.CostAccountData;
import pl.com.chrzanowski.scaffolding.domain.costaccounts.CostAccountFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.preparePaginationQuery;

@Service
public class CostAccountsJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public CostAccountsJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(CostAccountData data) {
        String query = "INSERT INTO cost_accounts (name, create_user_id) VALUES (?,?); ";
        jdbcTemplate.update(query, data.getName(), data.getCreateUserId());
        return commonJdbcRepository.getLastInsertedId();
    }

    public void update(CostAccountData data) {
        String query = "UPDATE cost_accounts SET name = ?, modify_date = ?, modify_user_id =? WHERE id = ?; ";
        jdbcTemplate.update(query, data.getName(), data.getModifyDate(), data.getModifyUserId(), data.getId());
    }

    public void remove(CostAccountData data) {
        String query = "UPDATE cost_accounts SET name = ?, remove_date = ?, remove_user_id =? WHERE id = ?; ";
        jdbcTemplate.update(query, data.getName(), data.getRemoveDate(), data.getRemoveUserId(), data.getId());
    }

    public List<Map<String, Object>> find(CostAccountFilter filter) {

        String query = "SELECT * FROM cost_accounts";

        if(filter != null) {
            query += " WHERE 1=1";

            if(filter.getId() != null) {
                query += " AND id = '" + filter.getId() + "'";
            }
            if(filter.getName() != null) {
                query += " AND name = '" + filter.getName() + "'";
            }
            if(filter.getIsRemoved() != null){
                if(!filter.getIsRemoved()) {
                    query += " AND remove_date IS NULL ";
                } else {
                    query += " AND remove_date IS NOT NULL";
                }
            }
            if(filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }

        }
        return jdbcTemplate.queryForList(query);
    }
}
