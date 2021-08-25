package pl.com.chrzanowski.scaffolding.api.lifeadviser;

public class RegistrationPostResponse {

    private Long userId;

    public RegistrationPostResponse(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
