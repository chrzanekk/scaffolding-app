package pl.com.chrzanowski.scaffolding.logic.tireseasons;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.tireseasons.TireSeasonData;
import pl.com.chrzanowski.scaffolding.domain.tireseasons.TireSeasonFilter;
import pl.com.chrzanowski.scaffolding.logic.ITireSeasons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getString;


@Service
public class TireSeasonsService implements ITireSeasons {

    private TireSeasonJdbcRepository tireSeasonJdbcRepository;

    public TireSeasonsService(TireSeasonJdbcRepository tireSeasonJdbcRepository) {
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
