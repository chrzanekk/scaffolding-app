package pl.com.chrzanowski.scaffolding.api.fueltypes;

import java.util.List;

public class FuelTypeRequestGetResponse {

    List<FuelTypeGetResponse> fuelTypeGetResponseList;

    public FuelTypeRequestGetResponse(List<FuelTypeGetResponse> fuelTypeGetResponseList) {
        this.fuelTypeGetResponseList = fuelTypeGetResponseList;
    }

    public List<FuelTypeGetResponse> getFuelTypeGetResponseList() {
        return fuelTypeGetResponseList;
    }
}
