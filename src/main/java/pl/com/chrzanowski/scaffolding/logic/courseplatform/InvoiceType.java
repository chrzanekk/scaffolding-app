package pl.com.chrzanowski.scaffolding.logic.courseplatform;

public enum InvoiceType {

    STANDARD("s"),
    CORRECTION("c");

    private String code;

    InvoiceType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
