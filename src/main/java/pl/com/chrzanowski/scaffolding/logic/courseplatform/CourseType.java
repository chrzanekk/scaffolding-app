package pl.com.chrzanowski.scaffolding.logic.courseplatform;

public enum CourseType {
    COURSE("c"),
    BUY_OUR_CODE("b"),
    SELECTION("s");

    private String code;

    CourseType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
