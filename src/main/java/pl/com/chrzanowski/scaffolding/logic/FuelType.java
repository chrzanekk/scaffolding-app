package pl.com.chrzanowski.scaffolding.logic;

import java.util.HashMap;
import java.util.Map;

public enum FuelType {
    BENZINE_PL("BENZINE_PL", "Benzyna"),
    BENZINE_EN("BENZINE_EN", "Petrol"),
    DIESEL_PL("DIESEL_PL", "OLEJ NAPĘDOWY"),
    DIESEL_EN("DIESEL_EN", "Diesel");


    private String lang;
    private String type;


    FuelType(String lang, String type) {
        this.lang = lang;
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public String getLang() {
        return lang;
    }

    private static final Map<String, FuelType> authoritiesWithCodeWithRole = new HashMap<>();

    static {
        for (FuelType fuelType : FuelType.values()) {
            authoritiesWithCodeWithRole.put(fuelType.type, fuelType);
        }
    }

    public static FuelType findByType (String type) {
        return authoritiesWithCodeWithRole.get(type);
    }

}
