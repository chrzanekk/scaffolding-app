//package pl.com.chrzanowski.scaffolding.logic;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//import pl.com.chrzanowski.scaffolding.domain.VehiclesBrandsAndModelsFilter;
//
//import java.util.List;
//import java.util.Map;
//
//import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.preparePaginationQuery;
//
//@Service
//public class VehiclesBrandsAndModelsJdbcRepository {
//
//    private JdbcTemplate jdbcTemplate;
//    private CommonJdbcRepository commonJdbcRepository;
//
//    public VehiclesBrandsAndModelsJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.commonJdbcRepository = commonJdbcRepository;
//    }
//
//
//
//    List<Map<String,Object>> find(VehiclesBrandsAndModelsFilter filter) {
//
//        String query = "SELECT " +
//                "vehicle_brand.id AS brandId, " +
//                "vehicle_brand.name AS brandName, " +
//                "vehicle_model.id AS modelId, " +
//                "vehicle_model.name AS modelName " +
//                "FROM vehicle_brand " +
//                "JOIN vehicle_model ON (vehicle_brand.id = vehicle_model.brand_id)";
//
//        if (filter != null) {
//            query += " WHERE 1+1";
//
//            if (filter.getBrandId() != null) {
//                query += " AND vehicle_brand.id = '" + filter.getBrandId() + "'";
//            }
//
//            if (filter.getBrandName() != null) {
//                query += " AND vehicle_brand.name = '" + filter.getBrandName() + "'";
//            }
//
//            if (filter.getModelId() != null) {
//                query += " AND vehicle_model.id = '" + filter.getModelId() + "'";
//            }
//
//            if (filter.getModelName() != null) {
//                query += " AND vehicle_model.name = '" + filter.getModelName() + "'";
//            }
//
//            if (filter.getPage() != null && filter.getPageSize() != null) {
//                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
//            }
//        }
//        return jdbcTemplate.queryForList(query);
//    }
//}
