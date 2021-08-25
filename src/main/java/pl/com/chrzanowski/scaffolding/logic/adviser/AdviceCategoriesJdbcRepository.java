package pl.com.chrzanowski.scaffolding.logic.adviser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.adviser.*;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.*;

@Service
public class AdviceCategoriesJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(AdviceCategoriesJdbcRepository.class);
    private CommonJdbcRepository commonJdbcRepository;

    public AdviceCategoriesJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    List<AdviceCategoryData> find(AdviceCategoriesFilter filter) {

        String query = "SELECT * FROM advice_categories";

        if (filter != null) {

            query += " WHERE 1=1";

            if (filter.getTagsAsSubstring() != null && !filter.getTagsAsSubstring().isEmpty()) {
                query += " AND tags LIKE '%" + filter.getTagsAsSubstring() + "%'";
            }

            if (filter.getSearchAsSubstring() != null && !filter.getSearchAsSubstring().isEmpty()) {
                query += " AND tags LIKE '%" + filter.getSearchAsSubstring() + "%' or " +
                        "description LIKE '%" + filter.getSearchAsSubstring() + "%' ";
            }

            if (filter.getApplicationId() != null) {
                query += " AND application_id = '" + filter.getApplicationId() + "'";
            }

            if (filter.getContext() != null) {
                query += " AND context = '" + filter.getContext() + "'";
            }

            if (filter.getId() != null) {
                query += " AND id = '" + filter.getId() + "'";
            }

            query += preparePaginationQuery(filter.getPage(), filter.getPageSize());

        }

        log.trace(query);

        return prepareAdviceCategoriesData(jdbcTemplate.queryForList(query));
    }

    List<AdvicePurchasedCategoryData> findPurchasedCategories(AdvicePurchasedCategoriesFilter filter) {

        String query = "SELECT  " +
                "acu.id as id, " +
                "acu.application_id as application_id, " +
                "acu.context as context, " +
                "acu.context_id as context_id, " +
                "date_from, " +
                "date_to, " +
                "acu.price as price, " +
                "acu.currency_code as currency_code, " +
                "acu.category_id as category_id, " +
                "account_id, " +
                "status, " +
                "period, " +
                "ac.name as category_name, " +
                "ac.description as category_description " +
                "FROM advice_categories as ac, advice_categories_used as acu";

        if (filter != null) {

            query += " WHERE ac.id = acu.category_id ";

            if (filter.getApplicationId() != null) {
                query += " AND acu.application_id = '" + filter.getApplicationId() + "'";
            }

            if (filter.getDomain() != null) {
                query += " AND acu.context = '" + filter.getDomain() + "'";
            }

            if (filter.getDomainId() != null) {
                query += " AND acu.context_id = '" + filter.getDomainId() + "'";
            }

            query += preparePaginationQuery(filter.getPage(), filter.getPageSize());

        }

        log.trace(query);

        return prepareAdvicePurchasedCategoriesData(jdbcTemplate.queryForList(query));
    }

    Long getTotalAdviceCategoriesCount() {
        String query = "SELECT COUNT(id) AS count FROM advice_categories;";
        try {
            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> getLong(rs, "count"));
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private List<AdviceCategoryData> prepareAdviceCategoriesData(List<Map<String, Object>> rows) {
        List<AdviceCategoryData> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            list.add(new AdviceCategoryData(
                    getLong(row, "id"),
                    getString(row, "application_id"),
                    getString(row, "context"),
                    getString(row, "name"),
                    getString(row, "description"),
                    getString(row, "tags"),
                    getBigDecimal(row, "price"),
                    getString(row, "currency_code"),
                    getString(row, "period_code"),
                    getBigDecimal(row, "price_per_month"),
                    getBigDecimal(row, "price_per_quarter"),
                    getBigDecimal(row, "price_per_half_year"),
                    getBigDecimal(row, "price_per_year")));
        }
        return list;
    }

    private List<AdvicePurchasedCategoryData> prepareAdvicePurchasedCategoriesData(List<Map<String, Object>> rows) {
        List<AdvicePurchasedCategoryData> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            list.add(new AdvicePurchasedCategoryData(
                    getLong(row, "id"),//Long id,
                    getString(row, "application_id"),// String applicationId,
                    getString(row, "context"),// String domain,
                    getString(row, "context_id"),// String domainId,
                    getString(row, "category_name"),// String name,
                    getString(row, "category_description"),// String description,
                    getBigDecimal(row, "price"),// BigDecimal price,
                    getString(row, "currency_code"),// String currencyCode,
                    getDate(row, "date_from"),// LocalDate dateFrom,
                    getDate(row, "date_to"),// LocalDate dateTo,
                    getLong(row, "category_id"),// Long categoryId,
                    PurchasedCategoryStatus.fromCode(getString(row, "status")),// PurchasedCategoryStatus status,
                    PeriodType.fromCode(getString(row, "period"))// PeriodType period

            ));
        }
        return list;
    }

    public Long storePurchase(AdvisePurchaseData data) {
        String query = "INSERT INTO advice_categories_used (" +
                "application_id,	context, context_id, date_from,	date_to, price,	currency_code, category_id,	account_id, period) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, data.getApplicationId(), data.getDomain(), data.getDomainId(), data.getDateFrom(), data.getDateTo(),
                data.getPrice(), data.getCurrencyCode(), data.getCategoryId(), data.getAccountId(), data.getPeriod().code());
        return commonJdbcRepository.getLastInsertedId();
    }

    public void updatePurchase(AdvisePurchaseData data) {
        String query = "UPDATE advice_categories_used SET external_transaction_id = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(query, data.getExternalTransactionId(), data.getStatus().code(), data.getId());
    }

}
