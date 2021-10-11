package pl.com.chrzanowski.scaffolding.api;

public class TireSeasonRequestGetResponse {

    private TireSeasonGetResponse tireSeasonGetResponse;

    public TireSeasonRequestGetResponse(TireSeasonGetResponse tireSeasonGetResponse) {
        this.tireSeasonGetResponse = tireSeasonGetResponse;
    }

    public TireSeasonGetResponse getTireSeasonGetResponse() {
        return tireSeasonGetResponse;
    }
}
