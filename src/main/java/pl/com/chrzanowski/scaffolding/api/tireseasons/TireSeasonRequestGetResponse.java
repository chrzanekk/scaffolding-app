package pl.com.chrzanowski.scaffolding.api.tireseasons;

public class TireSeasonRequestGetResponse {

    private TireSeasonGetResponse tireSeasonGetResponse;

    public TireSeasonRequestGetResponse(TireSeasonGetResponse tireSeasonGetResponse) {
        this.tireSeasonGetResponse = tireSeasonGetResponse;
    }

    public TireSeasonGetResponse getTireSeasonGetResponse() {
        return tireSeasonGetResponse;
    }
}
