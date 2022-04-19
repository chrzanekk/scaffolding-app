package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.com.chrzanowski.scaffolding.domain.users.UserData;
import pl.com.chrzanowski.scaffolding.logic.user.UserService;

import javax.persistence.EntityManager;

@Service
public class UserServiceFixture {

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager em;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createUsers() {
        UserData admin = new UserData(
                "admin",
                "admin admin",
                "admin@admin.pl",
                "1111",
                "pl",
                true,
                true,
                true,
                true,
                null,
                null
        );
        userService.create(admin);

        em.flush();
    }

}
