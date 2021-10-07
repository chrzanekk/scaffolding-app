package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.NewsletterData;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.UsersFilter;

@Service
public class MarketingService {
    private MarketingJdbcRepository marketingJdbcRepository;
    private ScaffUsersService scaffUsersService;
    private NewsletterService newsletterService;


    public MarketingService(MarketingJdbcRepository marketingJdbcRepository, ScaffUsersService scaffUsersService,
                            NewsletterService newsletterService) {
        this.marketingJdbcRepository = marketingJdbcRepository;
        this.scaffUsersService = scaffUsersService;
        this.newsletterService = newsletterService;
    }

    public void sendNewsletter(NewsletterData newsletter) {
        if (newsletter == null || newsletter.getContent() == null || newsletter.getContent().size() == 0) {
            throw new IllegalArgumentException("Incorrect content");
        }
        for (UserData user : scaffUsersService.find(new UsersFilter(true, true, 10000l, true))) {
            newsletterService.sendNewsletterToCustomer(user, newsletter);
        }
    }
}
