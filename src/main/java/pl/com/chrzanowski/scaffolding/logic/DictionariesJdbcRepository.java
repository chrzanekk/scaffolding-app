package pl.com.chrzanowski.scaffolding.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.chrzanowski.scaffolding.domain.DictionaryData;
import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
class DictionariesJdbcRepository {
    private static final Logger log = LoggerFactory.getLogger(DictionariesJdbcRepository.class);
    private JdbcTemplate jdbcTemplate;

    public DictionariesJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}