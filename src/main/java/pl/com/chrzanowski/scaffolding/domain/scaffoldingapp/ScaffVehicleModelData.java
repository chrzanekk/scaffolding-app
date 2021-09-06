package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffVehicleModelData {

    private Long id;
    private String name;

    public ScaffVehicleModelData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
