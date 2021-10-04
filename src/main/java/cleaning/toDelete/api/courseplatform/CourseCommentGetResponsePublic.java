package cleaning.toDelete.api.courseplatform;

import cleaning.toDelete.domain.courseplatform.CourseCommentData;

public class CourseCommentGetResponsePublic {
    private String text;
    private String createDatetime;

    public CourseCommentGetResponsePublic(CourseCommentData data) {
        this.text = data.getText();
        this.createDatetime = data.getCreateDatetime().toString();
    }

    public String getText() {
        return text;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }
}
