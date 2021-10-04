package cleaning.toDelete.domain.adviser;

public enum AdviseType {
    PLAIN("P"),
    QUESTION("Q");

    private String code;

    AdviseType(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    public static AdviseType fromCode(String code) {
        if (AdviseType.PLAIN.code.equals(code)) {
            return AdviseType.PLAIN;
        } else if (AdviseType.QUESTION.code.equals(code)) {
            return AdviseType.QUESTION;
        } else {
            throw new IllegalArgumentException("Unexpected code: " + code);
        }
    }
}
