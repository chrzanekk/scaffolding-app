package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffVehicleTypeData {

    private Long id;
    private String name;

    public ScaffVehicleTypeData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ScaffVehicleTypeData(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
