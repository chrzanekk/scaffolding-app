package pl.com.chrzanowski.scaffolding.logic.paymenttypes;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.paymenttypes.PaymentTypeData;
import pl.com.chrzanowski.scaffolding.domain.paymenttypes.PaymentTypeFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.preparePaginationQuery;

@Service
public class PaymentTypeJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public PaymentTypeJdbcRepository(JdbcTemplate jdbcTemplate,
                                     CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(PaymentTypeData data) {
        String query = "INSERT INTO payment_types(name, create_user_id) VALUES (?,?); ";
        jdbcTemplate.update(query, data.getName(), data.getCreateUserId());
        return commonJdbcRepository.getLastInsertedId();
    }

    public void update(PaymentTypeData data) {
        String query = "UPDATE payment_types SET name = ?, modify_date = ?, modify_user_id =? WHERE id = ?; ";
        jdbcTemplate.update(query, data.getName(), data.getModifyDate(), data.getModifyUserId(), data.getId());
    }

    public void remove(PaymentTypeData data) {
        String query = "UPDATE payment_types SET name = ?, remove_date = ?, remove_user_id =? WHERE id = ?; ";
        jdbcTemplate.update(query, data.getName(), data.getRemoveDate(), data.getRemoveUserId(), data.getId());
    }

    public List<Map<String, Object>> find(PaymentTypeFilter filter) {

        String query = "SELECT * FROM payment_types";

        if (filter != null) {
            query += " WHERE 1=1";

            if (filter.getId() != null) {
                query += " AND id = '" + filter.getId() + "'";
            }
            if (filter.getName() != null) {
                query += " AND name = '" + filter.getName() + "'";
            }
            if (filter.getIsRemoved() != null) {
                if (!filter.getIsRemoved()) {
                    query += " AND remove_date IS NULL ";
                } else {
                    query += " AND remove_date IS NOT NULL";
                }
            }
            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }

        }
        return jdbcTemplate.queryForList(query);
    }
}
