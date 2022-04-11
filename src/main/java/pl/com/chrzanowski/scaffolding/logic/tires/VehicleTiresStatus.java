package pl.com.chrzanowski.scaffolding.logic.tires;

public enum VehicleTiresStatus {
    MOUNTED("m"),
    STOKED("s"),
    DISPOSED("d");

    private String code;

    VehicleTiresStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
