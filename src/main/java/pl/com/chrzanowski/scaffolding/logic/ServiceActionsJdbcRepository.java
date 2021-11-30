package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionsData;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionsFilter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.preparePaginationQuery;

@Service
public class ServiceActionsJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;
    private ServiceActionTypeJdbcRepository serviceActionTypeJdbcRepository;

    public ServiceActionsJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository, ServiceActionTypeJdbcRepository serviceActionTypeJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
        this.serviceActionTypeJdbcRepository = serviceActionTypeJdbcRepository;
    }

    public Long create(ServiceActionsData data) {

        String query = "INSERT INTO service_actions (" +
                "vehicle_id," +
                "car_mileage," +
                "service_date," +
                "invoice_no," +
                "invoice_gross_value," +
                "tax_value," +
                "invoice_net_value," +
                "tax_rate," +
                "workshop_id," +
                "description," +
                "create_date, " +
                "service_action_type_id) VALUES (" +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "? )";
        jdbcTemplate.update(query,
                data.getVehicleId(),
                data.getCarMileage(),
                data.getServiceDate(),
                data.getInvoiceNumber(),
                data.getInvoiceGrossValue(),
                data.getTaxValue(),
                data.getInvoiceNetValue(),
                data.getTaxRate(),
                data.getWorkshopId(),
                data.getServiceActionDescription(),
                data.getCreateDate(),
                data.getServiceActionTypeId());
        return commonJdbcRepository.getLastInsertedId();
    }

    public void update(ServiceActionsData data) {

        String query = "UPDATE service_actions SET " +
                "car_mileage = ?, " +
                "service_date = ?, " +
                "service_action_type_id = ?, " +
                "invoice_no = ?, " +
                "invoice_gross_value = ?, " +
                "tax_value = ?, " +
                "invoice_net_value = ?, " +
                "tax_rate = ?, " +
                "workshop_id = ?, " +
                "modify_date = ?, " +
                "description = ? WHERE " +
                "id = ?;";
        jdbcTemplate.update(query,
                data.getCarMileage(),
                data.getServiceDate(),
                data.getServiceActionTypeId(),
                data.getInvoiceNumber(),
                data.getInvoiceGrossValue(),
                data.getTaxValue(),
                data.getInvoiceNetValue(),
                data.getTaxRate(),
                data.getWorkshopId(),
                data.getModifyDate(),
                data.getServiceActionDescription(),
                data.getId());
    }

    List<Map<String, Object>> find(ServiceActionsFilter filter) {

        String query = "SELECT \n" +
                "service_actions.id,\n" +
                "service_actions.vehicle_id,\n" +
                "service_actions.car_mileage,\n" +
                "service_actions.service_date,\n" +
                "service_actions.invoice_no,\n" +
                "service_actions.invoice_gross_value,\n" +
                "service_actions.invoice_net_value,\n" +
                "service_actions.tax_value,\n" +
                "service_actions.tax_rate,\n" +
                "service_actions.workshop_id,\n" +
                "service_actions.description,\n" +
                "service_actions.service_action_type_id,\n" +
                "service_action_type.id,\n" +
                "service_action_type.name AS action_type,\n" +
                "workshops.id AS workshopId,\n" +
                "workshops.name AS workshop,\n" +
                "workshops.tax_number AS tax_number,\n" +
                "workshops.street AS street,\n" +
                "workshops.building_number AS building_number,\n" +
                "workshops.apartment_number AS apartment_number,\n" +
                "workshops.postal_code AS postal_code,\n" +
                "workshops.city AS city\n" +
                "FROM service_actions \n" +
                "LEFT JOIN service_action_type ON (service_actions.service_action_type_id = service_action_type.id)" +
                "LEFT JOIN workshops ON (service_actions.workshop_id = workshops.id)";

        query = prepareFilterQuery(filter, query);

        return jdbcTemplate.queryForList(query);
    }

    List<Map<String, Object>> findSummaryInvoiceValues(ServiceActionsFilter filter) {
        String query = "SELECT " +
                "SUM(invoice_gross_value) AS summaryGrossValue, " +
                "SUM(invoice_net_value) AS summaryNetValue, " +
                "SUM(tax_value) AS summaryTaxValue " +
                "FROM service_actions " +
                "LEFT JOIN service_action_type ON (service_actions.service_action_type_id = service_action_type.id)" +
                "LEFT JOIN workshops ON (service_actions.workshop_id = workshops.id)";

        query = prepareFilterQuery(filter, query);

        return jdbcTemplate.queryForList(query);

    }

    Map<String, Object> findLastDateOfServiceType(ServiceActionsFilter filter) {
        String query = "SELECT " +
                "service_actions.id," +
                "service_actions.vehicle_id," +
                "service_actions.service_date, " +
                "service_actions.service_action_type_id, " +
                "service_action_type.id, " +
                "service_action_type.name AS action_type " +
                "FROM service_actions " +
                "LEFT JOIN service_action_type ON (service_actions.service_action_type_id = service_action_type.id) " +
                "WHERE service_actions.vehicle_id = " + filter.getVehicleId() + " " +
                "AND service_action_type.id = " + filter.getServiceActionId() + " " +
                "ORDER BY service_actions.id DESC LIMIT 1";
        return jdbcTemplate.queryForMap(query);
    }

    private String prepareFilterQuery(ServiceActionsFilter filter, String query) {
        if (filter != null) {
            query += " WHERE 1=1";
            if (filter.getId() != null) {
                query += " AND service_actions.id = '" + filter.getId() + "'";
            }
            if (filter.getVehicleId() != null) {
                query += " AND service_actions.vehicle_id = '" + filter.getVehicleId() + "'";
            }
            if (filter.getWorkshopId() != null) {
                query += " AND service_actions.workshop_id = '" + filter.getWorkshopId() + "'";
            }
            if (filter.getWorkshopName() != null) {
                query += " AND workshops.name = '" + filter.getWorkshopName() + "'";
            }
            if (filter.getActionTypeName() != null) {
                query += " AND service_action_type.name = '" + filter.getActionTypeName() + "'";
            }
            if (filter.getDateFrom() != null || filter.getDateTo() != null) {
                query += prepareQueryForDateFiltering(filter.getDateFrom(), filter.getDateTo());
            }
            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }
        }
        return query;
    }

    private String prepareQueryForDateFiltering(LocalDate dateFrom, LocalDate dateTo) {
        String query = "";
        if (dateFrom != null && dateTo == null) {
            query += " AND service_actions.service_date BETWEEN '" + dateFrom + "' AND '" + LocalDate.now() + "'";
        }
        if (dateTo != null && dateFrom == null) {
            query += " AND service_actions.service_date BETWEEN '' AND '" + dateTo + "'";
        }
        if (dateTo != null && dateFrom != null) {
            query += " AND service_actions.service_date BETWEEN '" + dateFrom + "' AND '" + dateTo + "'";
        }
        return query;
    }

    private String prepareQueryForLastOilService(Long actionTypeId) {
        return " AND service_action_type.id = " + actionTypeId + " ORDER BY service_actions.id DESC LIMIT 1";
    }

}
