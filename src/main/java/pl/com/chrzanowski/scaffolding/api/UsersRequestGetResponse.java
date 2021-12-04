package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class UsersRequestGetResponse {

    private List<UserGetResponse> users;

    public UsersRequestGetResponse(List<UserGetResponse> users) {
        this.users = users;
    }

    public List<UserGetResponse> getUsers() {
        return users;
    }
}
