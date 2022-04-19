package pl.com.chrzanowski.scaffolding.logic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.chrzanowski.scaffolding.Application;
import pl.com.chrzanowski.scaffolding.domain.users.UsersFilter;
import pl.com.chrzanowski.scaffolding.logic.user.UserService;

import static org.assertj.core.api.Assertions.assertThat;
//todo add mocking useragent on webutil
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class UserServiceIT {

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceFixture userServiceFixture;

    @Autowired
    private UserServiceDB userServiceDB;

    @Test
    public void checkIfUserWasAdded() {
        userServiceDB.createTable();

        userServiceFixture.createUsers();

        UsersFilter filter = new UsersFilter();

        Integer size = userService.find(filter).size();

        assertThat(size).isNotZero();
    }


}
