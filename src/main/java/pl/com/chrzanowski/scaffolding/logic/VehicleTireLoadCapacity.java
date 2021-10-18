package pl.com.chrzanowski.scaffolding.logic;

public enum VehicleTireLoadCapacity {
    NINETY_ONE(91,615),
    NINETY_FOUR(94,670);

    private Integer index;
    private Integer load;

    VehicleTireLoadCapacity(Integer index, Integer load) {
        this.index = index;
        this.load = load;
    }

    public Integer getIndex() {
        return index;
    }

    public Integer getLoad() {
        return load;
    }
}
