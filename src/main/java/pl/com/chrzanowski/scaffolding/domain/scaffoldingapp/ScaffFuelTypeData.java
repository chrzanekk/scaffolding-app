package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffFuelTypeData {

    private Long id;
    private String name;

    public ScaffFuelTypeData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ScaffFuelTypeData(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
