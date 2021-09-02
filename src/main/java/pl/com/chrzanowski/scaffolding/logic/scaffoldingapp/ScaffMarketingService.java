package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffNewsletterData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUsersFilter;

@Service
public class ScaffMarketingService {
    private ScaffMarketingJdbcRepository scaffMarketingJdbcRepository;
    private ScaffUsersService scaffUsersService;
    private ScaffNewsletterService scaffNewsletterService;


    public ScaffMarketingService(ScaffMarketingJdbcRepository scaffMarketingJdbcRepository, ScaffUsersService scaffUsersService,
                                 ScaffNewsletterService scaffNewsletterService) {
        this.scaffMarketingJdbcRepository = scaffMarketingJdbcRepository;
        this.scaffUsersService = scaffUsersService;
        this.scaffNewsletterService = scaffNewsletterService;
    }

    public void sendNewsletter(ScaffNewsletterData newsletter) {
        if (newsletter == null || newsletter.getContent() == null || newsletter.getContent().size() == 0) {
            throw new IllegalArgumentException("Incorrect content");
        }
        for (ScaffUserData user : scaffUsersService.find(new ScaffUsersFilter(true, true, 10000l, true))) {
            scaffNewsletterService.sendNewsletterToCustomer(user, newsletter);
        }
    }
}
