package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ScaffFuelType {
    BENZINE_PL("BENZINE_PL", "Benzyna"),
    BENZINE_EN("BENZINE_EN", "Petrol"),
    DIESEL_PL("DIESEL_PL", "OLEJ NAPĘDOWY"),
    DIESEL_EN("DIESEL_EN", "Diesel");


    private String lang;
    private String type;


    ScaffFuelType(String lang, String type) {
        this.lang = lang;
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public String getLang() {
        return lang;
    }

    private static final Map<String, ScaffFuelType> authoritiesWithCodeWithRole = new HashMap<>();

    static {
        for (ScaffFuelType scaffFuelType : ScaffFuelType.values()) {
            authoritiesWithCodeWithRole.put(scaffFuelType.type, scaffFuelType);
        }
    }

    public static ScaffFuelType findByType (String type) {
        return authoritiesWithCodeWithRole.get(type);
    }

}
