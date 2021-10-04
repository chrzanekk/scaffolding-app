package cleaning.toDelete.api.courseplatform;

public class ModulePostRequest {
    private Long courseId;
    private String name;

    public ModulePostRequest() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }
}
