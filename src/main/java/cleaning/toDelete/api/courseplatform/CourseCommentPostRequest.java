package cleaning.toDelete.api.courseplatform;

public class CourseCommentPostRequest {
    private String text;
    private Long courseId;

    public CourseCommentPostRequest() {
    }

    public String getText() {
        return text;
    }

    public Long getCourseId() {
        return courseId;
    }
}
