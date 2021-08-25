package pl.com.chrzanowski.scaffolding.logic;

public enum DiscountType {
    PERCENT("percent"),
    PRICE("price"),
    DIET("diet"),
    CITY("city"),
    SALE("sale"),
    DELIVERY_PERSONAL("personal");

    private String code;

    DiscountType(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
