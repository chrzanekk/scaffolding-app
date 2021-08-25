package pl.com.chrzanowski.scaffolding.logic.adviser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.adviser.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
class AdviserJdbcRepository {
    private static final Logger log = LoggerFactory.getLogger(AdviserJdbcRepository.class);
    private JdbcTemplate jdbcTemplate;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-LL-dd HH:mm:ss.S");

    public AdviserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void createAdvice(AdviceData data) {
        String query = "INSERT INTO advices (app_id, domain, content, type, scope, action, data_type, component, class, name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, data.getAppId(), data.getDomain(), data.getContent(), data.getType(), data.getScope(),
                data.getAction(), data.getDataType(), data.getComponent(), data.getAdviceClass(), data.getName());
    }

    AdviceData getAdvice(Long id) {
        String query = "SELECT * FROM advices WHERE id = ?;";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id},
                    (rs, rowNum) -> {
                        AdviceData data = new AdviceData(
                                rs.getLong("id"),
                                rs.getString("app_id"),
                                rs.getString("domain"),
                                rs.getString("content"),
                                AdviseType.fromCode(rs.getString("type")),
                                rs.getString("scope"),
                                rs.getString("action"),
                                rs.getString("data_type"),
                                rs.getString("component"),
                                rs.getString("class"),
                                rs.getString("name"),
                                AdviceStatus.fromCode(rs.getString("status")),
                                AdvicePeriod.fromCode(rs.getString("period")),
                                rs.getString("variable_name"));
                        return data;
                    });
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    List<AdviceData> findAdvices(AdvicesFilter filter) {
        String query = "SELECT * FROM advices";

        if (filter != null) {

            query += " WHERE 1=1";

            if (filter.getAppIdAsSubstring() != null) {
                query += " AND app_id LIKE '%" + filter.getAppIdAsSubstring() + "%'";
            }

            if (filter.getDomainAsSubstring() != null) {
                query += " AND domain LIKE '%" + filter.getDomainAsSubstring() + "%'";
            }

            if (filter.getNameAsSubstring() != null) {
                query += " AND name LIKE '%" + filter.getNameAsSubstring() + "%'";
            }

            if (filter.getAppId() != null) {
                query += " AND app_id = '" + filter.getAppId() + "'";
            }

            if (filter.getDomain() != null) {
                query += " AND domain = '" + filter.getDomain() + "'";
            }

            if (filter.getName() != null) {
                query += " AND name = '" + filter.getName() + "'";
            }

            if (filter.getStartDateTo() != null) {
                query += " AND start_date <= '" + filter.getStartDateTo() + "'";
            }

            if (filter.getStopDateFrom() != null) {
                query += " AND stop_date >= '" + filter.getStopDateFrom() + "'";
            }


            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += " LIMIT " + filter.getPageSize() * (filter.getPage() - 1L) + ", " + filter.getPageSize();
            }

            if (filter.getStatus() != null) {
                query += " AND status = '" + filter.getStatus().code() + "'";
            }

        }

        log.trace(query);

        return prepareAdvicesData(jdbcTemplate.queryForList(query));
    }

    private List<AdviceData> prepareAdvicesData(List<Map<String, Object>> rows) {
        List<AdviceData> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            AdviceData advice = null;
            if (AdviseDataComponent.SELECT.name().equals(String.valueOf(row.get("component")))) {
                List<AdviceAnswerOptionData> options = findAdviceAnswerOptions(Long.valueOf(String.valueOf(row.get("id"))));
                advice = new AdviceData(
                        Long.valueOf(String.valueOf(row.get("id"))),
                        String.valueOf(row.get("app_id")),
                        String.valueOf(row.get("domain")),
                        String.valueOf(row.get("content")),
                        AdviseType.fromCode(String.valueOf(row.get("type"))),
                        String.valueOf(row.get("scope")),
                        String.valueOf(row.get("action")),
                        String.valueOf(row.get("data_type")),
                        String.valueOf(row.get("component")),
                        String.valueOf(row.get("class")),
                        String.valueOf(row.get("name")),
                        AdviceStatus.fromCode(String.valueOf(row.get("status"))),
                        AdvicePeriod.fromCode(String.valueOf(row.get("period"))),
                        String.valueOf(row.get("variable_name")),
                        options);

            } else {
                advice = new AdviceData(
                        Long.valueOf(String.valueOf(row.get("id"))),
                        String.valueOf(row.get("app_id")),
                        String.valueOf(row.get("domain")),
                        String.valueOf(row.get("content")),
                        AdviseType.fromCode(String.valueOf(row.get("type"))),
                        String.valueOf(row.get("scope")),
                        String.valueOf(row.get("action")),
                        String.valueOf(row.get("data_type")),
                        String.valueOf(row.get("component")),
                        String.valueOf(row.get("class")),
                        String.valueOf(row.get("name")),
                        AdviceStatus.fromCode(String.valueOf(row.get("status"))),
                        AdvicePeriod.fromCode(String.valueOf(row.get("period"))),
                        String.valueOf(row.get("variable_name")));
            }
            list.add(advice);
        }
        return list;
    }

    List<AdviceAnswerOptionData> findAdviceAnswerOptions(Long adviceId) {
        String query = "SELECT * FROM advice_answer_options where advice_id = " + adviceId + " order by option_order";
        log.trace(query);

        return prepareAdviceAnswerOptionData(jdbcTemplate.queryForList(query));
    }

    private List<AdviceAnswerOptionData> prepareAdviceAnswerOptionData(List<Map<String, Object>> rows) {
        List<AdviceAnswerOptionData> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            list.add(new AdviceAnswerOptionData(
                            Long.valueOf(String.valueOf(row.get("id"))),
                            Long.valueOf(String.valueOf(row.get("advice_id"))),
                            String.valueOf(row.get("option_name")),
                            String.valueOf(row.get("value")),
                            Long.valueOf(String.valueOf(row.get("option_order")))
                    )
            );
        }
        return list;
    }


    void updateAdvice(AdviceData data) {
        String query = "UPDATE advices SET app_id = ?, domain = ?, content = ?, type = ?, scope = ?, action = ?, data_type = ?, component = ?, class = ?, name = ?, status = ? WHERE id = ?;";
        jdbcTemplate.update(query, data.getAppId(), data.getDomain(), data.getContent(), data.getType(),
                data.getScope(), data.getAction(), data.getDataType(), data.getComponent(), data.getAdviceClass(), data.getName(), data.getStatus().code(), data.getId());
    }

    void deleteAdvice(Long id) {
        String query = "DELETE FROM advices WHERE id=?;";
        jdbcTemplate.update(query, id);
    }


    void createTriggeredAdvice(TriggeredAdviceData data) {
        String query = "INSERT INTO triggered_advices (advice_id, app_id, domain, domain_id, content, type, lang, scope, action, data_type, component, score, status, trigger_datetime, name) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, data.getAdviceId(), data.getAppId(), data.getDomain(), data.getDomainId(), data.getContent(), data.getType(),
                data.getLang(), data.getScope(), data.getAction(), data.getDataType(), data.getComponent(), data.getScore(), data.getStatus(), data.getTriggerDateTime(), data.getName());
    }

    TriggeredAdviceData getTriggeredAdvice(Long id) {
        String query = "SELECT * FROM triggered_advices WHERE id = ?;";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id},
                    (rs, rowNum) -> {
                        TriggeredAdviceData data = new TriggeredAdviceData(
                                rs.getLong("id"),
                                rs.getLong("advice_id"),
                                rs.getString("app_id"),
                                rs.getString("domain"),
                                rs.getString("domain_id"),
                                rs.getString("content"),
                                rs.getString("type"),
                                rs.getString("lang"),
                                rs.getString("scope"),
                                rs.getString("action"),
                                rs.getString("data_type"),
                                rs.getString("component"),
                                rs.getLong("score"),
                                rs.getString("status"),
                                rs.getTimestamp("trigger_datetime") != null ? rs.getTimestamp("trigger_datetime").toLocalDateTime() : null,
                                rs.getString("name"),
                                rs.getTimestamp("response_date_time") != null ? rs.getTimestamp("response_date_time").toLocalDateTime() : null,
                                rs.getString("response_value")
                        );
                        return data;
                    });
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    List<TriggeredAdviceData> findTriggeredAdvices(TriggeredAdvicesFilter filter) {
        String query = "SELECT * FROM triggered_advices";

        if (filter != null) {

            query += " WHERE 1=1";

            if (filter.getNameAsSubstring() != null && !filter.getNameAsSubstring().isEmpty()) {
                query += " AND name LIKE '%" + filter.getNameAsSubstring() + "%'";
            }

            if (filter.getAppIdAsSubstring() != null && !filter.getAppIdAsSubstring().isEmpty()) {
                query += " AND app_id LIKE '%" + filter.getAppIdAsSubstring() + "%'";
            }

            if (filter.getDomainAsSubstring() != null && !filter.getDomainAsSubstring().isEmpty()) {
                query += " AND domain LIKE '%" + filter.getDomainAsSubstring() + "%'";
            }

            if (filter.getAppId() != null && !filter.getAppId().isEmpty()) {
                query += " AND app_id = '" + filter.getAppId() + "'";
            }

            if (filter.getDomain() != null && !filter.getDomain().isEmpty()) {
                query += " AND domain = '" + filter.getDomain() + "'";
            }

            if (filter.getDomainId() != null && !filter.getDomainId().isEmpty()) {
                query += " AND domain_id = '" + filter.getDomainId() + "'";
            }

            if (filter.getScore() != null) {
                query += " AND score = '" + filter.getScore() + "'";
            }

            if (filter.getTriggerDateTimeFrom() != null && filter.getTriggerDateTimeTo() != null) {
                query += " AND trigger_datetime BETWEEN '" + filter.getTriggerDateTimeFrom() + "' AND '" + filter.getTriggerDateTimeTo() + "'";
            } else if (filter.getTriggerDateTimeFrom() != null) {
                query += " AND trigger_datetime >= '" + filter.getTriggerDateTimeFrom() + "'";
            } else if (filter.getTriggerDateTimeTo() != null) {
                query += " AND trigger_datetime <= '" + filter.getTriggerDateTimeTo() + "'";
            }

            if (filter.getSortColumns() != null) {
                query += filter.getSortColumns().orderBy();
            }

            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += " LIMIT " + filter.getPageSize() * (filter.getPage() - 1L) + ", " + filter.getPageSize();
            }
        }
        log.trace(query);
        return prepareTriggeredAdvicesData(jdbcTemplate.queryForList(query));
    }

    private List<TriggeredAdviceData> prepareTriggeredAdvicesData(List<Map<String, Object>> rows) {
        List<TriggeredAdviceData> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            list.add(new TriggeredAdviceData(
                    Long.valueOf(String.valueOf(row.get("id"))),
                    Long.valueOf(String.valueOf(row.get("advice_id"))),
                    String.valueOf(row.get("app_id")),
                    String.valueOf(row.get("domain")),
                    String.valueOf(row.get("domain_id")),
                    String.valueOf(row.get("content")),
                    String.valueOf(row.get("type")),
                    String.valueOf(row.get("lang")),
                    String.valueOf(row.get("scope")),
                    String.valueOf(row.get("action")),
                    String.valueOf(row.get("data_type")),
                    String.valueOf(row.get("component")),
                    Long.valueOf(String.valueOf(row.get("score"))),
                    String.valueOf(row.get("status")),
                    row.get("trigger_datetime") != null ? LocalDateTime.parse(String.valueOf(row.get("trigger_datetime")), dateTimeFormatter) : null,
                    String.valueOf(row.get("name")),
                    row.get("response_date_time") != null ? LocalDateTime.parse(String.valueOf(row.get("response_date_time")), dateTimeFormatter) : null,
                    String.valueOf(row.get("response_value"))

            ));
        }

        return list;
    }

    void updateTriggeredAdvice(TriggeredAdviceData data) {
        String query = "UPDATE triggered_advices SET advice_id = ?, app_id = ?, domain = ?, domain_id = ?, " +
                "content = ?, type = ?, " +
                "lang = ?, scope = ?, action = ?, data_type = ?, component = ?, score = ?, status = ?, " +
                "trigger_datetime = ?, name = ?, response_value = ?, response_date_time = ? WHERE id = ?;";
        jdbcTemplate.update(query, data.getAdviceId(), data.getAppId(), data.getDomain(), data.getDomainId(),
                data.getContent(), data.getType(), data.getLang(),
                data.getScope(), data.getAction(), data.getDataType(), data.getComponent(), data.getScore(),
                data.getStatus(), data.getTriggerDateTime(), data.getName(), data.getResponseValue(),
                data.getResponseDateTime(), data.getId());
    }

    void deleteTriggeredAdvice(Long id) {
        String query = "DELETE FROM triggered_advices WHERE id=?;";
        jdbcTemplate.update(query, id);
    }

    Long getTriggeredAdvicesCount() {
        String query = "SELECT COUNT(id) AS count FROM triggered_advices";
        return jdbcTemplate.queryForObject(query, (rs, rowNum) -> Long.valueOf(rs.getString("count")));
    }

    Long getAdvicesCount() {
        String query = "SELECT COUNT(id) AS count FROM advices";
        return jdbcTemplate.queryForObject(query, (rs, rowNum) -> Long.valueOf(rs.getString("count")));
    }

    void log(TriggeredAdviceLogData data) {
        String query = "INSERT INTO advices_log (advice_id,\n" +
                "triggered_advice_id,\n" +
                "app_id,\n" +
                "domain,\n" +
                "message,\n" +
                "date_time) VALUES (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, data.getAdviceId(), data.getTriggeredAdviceId(), data.getAppId(), data.getDomain(), data.getMessage(),
                LocalDateTime.now());
    }
}
