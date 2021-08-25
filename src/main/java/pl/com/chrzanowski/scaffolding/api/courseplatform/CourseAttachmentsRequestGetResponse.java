package pl.com.chrzanowski.scaffolding.api.courseplatform;

import java.util.List;

public class CourseAttachmentsRequestGetResponse {

    private List<CourseAttachmentGetResponse> courseAttachments;

    public CourseAttachmentsRequestGetResponse(List<CourseAttachmentGetResponse> courseAttachments) {
        this.courseAttachments = courseAttachments;
    }

    public List<CourseAttachmentGetResponse> getCourseAttachments() {
        return courseAttachments;
    }
}
