package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffDictionaryData {

    private Long id;
    private String name;
    private String language;

    public ScaffDictionaryData(Long id) {
        this.id = id;
    }

    public ScaffDictionaryData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ScaffDictionaryData(String name) {
        this.name = name;
    }

    public ScaffDictionaryData(String name, String language) {
        this.name = name;
        this.language = language;
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }
}
