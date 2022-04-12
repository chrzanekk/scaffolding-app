package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.tireseasons.TireSeasonData;
import pl.com.chrzanowski.scaffolding.domain.tireseasons.TireSeasonFilter;

import java.util.List;

public interface ITireSeasons {

    Long add(TireSeasonData data);
    void update(TireSeasonData data);
    List<TireSeasonData> find(TireSeasonFilter filter);

}
