package pl.com.chrzanowski.scaffolding.api.courseplatform;

public class ModulePutRequest {

    private String name;
    private String visibilityStatus;

    public ModulePutRequest() {
    }

    public String getName() {
        return name;
    }

    public String getVisibilityStatus() {
        return visibilityStatus;
    }
}
