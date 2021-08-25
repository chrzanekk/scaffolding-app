package pl.com.chrzanowski.scaffolding.api.courseplatform;

public class AuthorPutRequest {
    private Long id;
    private String firstName;
    private String lastName;

    public AuthorPutRequest() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
