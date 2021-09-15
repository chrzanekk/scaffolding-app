package pl.com.chrzanowski.scaffolding.api.scaffolding;

import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffFuelTypeData;

public class ScaffFuelTypeGetResponse {

    private Long id;
    private String name;

    public ScaffFuelTypeGetResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ScaffFuelTypeGetResponse() {
    }

    public ScaffFuelTypeGetResponse(Long id) {
        this.id = id;
    }

    public ScaffFuelTypeGetResponse(String name) {
        this.name = name;
    }

    public ScaffFuelTypeGetResponse(ScaffFuelTypeData data) {
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
