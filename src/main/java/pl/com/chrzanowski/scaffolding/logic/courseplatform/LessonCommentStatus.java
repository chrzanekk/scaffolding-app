package pl.com.chrzanowski.scaffolding.logic.courseplatform;

public enum LessonCommentStatus {
    VISIBLE("v"),
    INVISIBLE("i");

    private String value;

    LessonCommentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
