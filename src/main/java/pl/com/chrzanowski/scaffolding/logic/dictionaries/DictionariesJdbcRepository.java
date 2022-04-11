package pl.com.chrzanowski.scaffolding.logic.dictionaries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class DictionariesJdbcRepository {
    private static final Logger log = LoggerFactory.getLogger(DictionariesJdbcRepository.class);
    private JdbcTemplate jdbcTemplate;

    public DictionariesJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}