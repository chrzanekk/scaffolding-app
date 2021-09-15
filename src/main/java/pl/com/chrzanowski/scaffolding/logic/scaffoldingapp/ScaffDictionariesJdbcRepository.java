package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScaffDictionariesJdbcRepository {

    private static final Logger log = LoggerFactory.getLogger(ScaffDictionariesJdbcRepository.class);
    private JdbcTemplate jdbcTemplate;

    public ScaffDictionariesJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
