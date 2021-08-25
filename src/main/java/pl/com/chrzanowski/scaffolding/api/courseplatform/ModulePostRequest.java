package pl.com.chrzanowski.scaffolding.api.courseplatform;

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
