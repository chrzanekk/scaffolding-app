package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.util.List;

public class ScaffFuelTypeRequestGetResponse {

    List<ScaffFuelTypeGetResponse> fuelTypeGetResponseList;

    public ScaffFuelTypeRequestGetResponse(List<ScaffFuelTypeGetResponse> fuelTypeGetResponseList) {
        this.fuelTypeGetResponseList = fuelTypeGetResponseList;
    }

    public List<ScaffFuelTypeGetResponse> getFuelTypeGetResponseList() {
        return fuelTypeGetResponseList;
    }
}
