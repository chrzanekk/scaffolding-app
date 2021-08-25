package pl.com.chrzanowski.scaffolding.api.adviser;

public class AccountGetResponse {
    private final Long id;
    private final String name;

    public AccountGetResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
