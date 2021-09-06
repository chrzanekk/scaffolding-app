package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffVehicleBrandData {

    private Long id;
    private String name;

    public ScaffVehicleBrandData(Long id, String name) {
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
