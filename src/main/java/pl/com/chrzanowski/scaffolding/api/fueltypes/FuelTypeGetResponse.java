package pl.com.chrzanowski.scaffolding.api.fueltypes;

import pl.com.chrzanowski.scaffolding.domain.fueltypes.FuelTypeData;

public class FuelTypeGetResponse {

    private Long id;
    private String name;

    public FuelTypeGetResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public FuelTypeGetResponse() {
    }

    public FuelTypeGetResponse(Long id) {
        this.id = id;
    }

    public FuelTypeGetResponse(String name) {
        this.name = name;
    }

    public FuelTypeGetResponse(FuelTypeData data) {
        this.id = data.getId();
        this.name = data.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
