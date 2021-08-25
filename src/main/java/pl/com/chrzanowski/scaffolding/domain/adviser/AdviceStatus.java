package pl.com.chrzanowski.scaffolding.domain.adviser;

public enum AdviceStatus {
    ACTIVE("A"),
    ONE_TIME_TRIGGERED("O"),
    INACTIVE("I");
    private String code;

    AdviceStatus(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    public static AdviceStatus fromCode(String code) {
        if (AdviceStatus.ACTIVE.code.equals(code)) {
            return AdviceStatus.ACTIVE;
        } else if (AdviceStatus.ONE_TIME_TRIGGERED.code.equals(code)) {
            return AdviceStatus.ONE_TIME_TRIGGERED;
        } else if (AdviceStatus.INACTIVE.code.equals(code)) {
            return AdviceStatus.INACTIVE;
        } else {
            throw new IllegalArgumentException("Unexpected code: " + code);
        }
    }
}
