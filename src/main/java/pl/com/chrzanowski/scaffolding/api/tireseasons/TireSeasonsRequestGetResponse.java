package pl.com.chrzanowski.scaffolding.api.tireseasons;

import java.util.List;

public class TireSeasonsRequestGetResponse {

    private List<TireSeasonGetResponse> tireSeasons;

    public TireSeasonsRequestGetResponse(List<TireSeasonGetResponse> tireSeasons) {
        this.tireSeasons = tireSeasons;
    }

    public List<TireSeasonGetResponse> getTireSeasons() {
        return tireSeasons;
    }
}
