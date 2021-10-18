package pl.com.chrzanowski.scaffolding.logic;

public enum VehicleTireSpeed {
    Q('Q', 160),
    T('T',190),
    H('H',210),
    V('V',240),
    W('W',270),
    Y('Y',300);



    private Character name;
    private Integer speed;

    VehicleTireSpeed(Character name, Integer speed) {
        this.name = name;
        this.speed = speed;
    }

    public Character getName() {
        return name;
    }

    public Integer getSpeed() {
        return speed;
    }
}
