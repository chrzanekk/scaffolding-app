package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.NewsletterData;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.UsersFilter;

@Service
public class MarketingService {
    private MarketingJdbcRepository marketingJdbcRepository;
    private UserService userService;
    private NewsletterService newsletterService;


    public MarketingService(MarketingJdbcRepository marketingJdbcRepository, UserService userService,
                            NewsletterService newsletterService) {
        this.marketingJdbcRepository = marketingJdbcRepository;
        this.userService = userService;
        this.newsletterService = newsletterService;
    }

    public void sendNewsletter(NewsletterData newsletter) {
        if (newsletter == null || newsletter.getContent() == null || newsletter.getContent().size() == 0) {
            throw new IllegalArgumentException("Incorrect content");
        }
        for (UserData user : userService.find(new UsersFilter(true, true, 10000l, true))) {
            newsletterService.sendNewsletterToCustomer(user, newsletter);
        }
    }
}
