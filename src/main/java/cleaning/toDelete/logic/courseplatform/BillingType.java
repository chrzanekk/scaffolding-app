package cleaning.toDelete.logic.courseplatform;

public enum BillingType {
    COMPANY("c"),
    PRIVATE_PERSON("p");

    private String code;

    BillingType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
