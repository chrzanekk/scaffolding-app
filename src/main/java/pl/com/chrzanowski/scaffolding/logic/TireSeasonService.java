package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.TireSeasonData;
import pl.com.chrzanowski.scaffolding.domain.TireSeasonFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getString;


@Service
public class TireSeasonService implements ITireSeason {

    private TireSeasonJdbcRepository tireSeasonJdbcRepository;

    public TireSeasonService(TireSeasonJdbcRepository tireSeasonJdbcRepository) {
        this.tireSeasonJdbcRepository = tireSeasonJdbcRepository;
    }


    public Long add(TireSeasonData data) {
        return tireSeasonJdbcRepository.create(data);
    }


    public void update(TireSeasonData data) {
        tireSeasonJdbcRepository.update(data);
    }


    public List<TireSeasonData> find(TireSeasonFilter filter) {
        return getTireSeasons(tireSeasonJdbcRepository.find(filter));
    }

    private List<TireSeasonData> getTireSeasons(List<Map<String, Object>> data) {

        List<TireSeasonData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new TireSeasonData(
                    getLong(row, "id"),
                    getString(row, "name")
            ));
        }
        return list;
    }
}
