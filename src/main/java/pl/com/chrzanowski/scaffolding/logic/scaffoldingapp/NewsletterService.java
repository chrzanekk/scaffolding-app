package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.UserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.NewsletterData;

@Service
public class NewsletterService {
    private static final Logger log = LoggerFactory.getLogger(NewsletterService.class);
    private SentEmailsService sentEmailsService;
    private EmailService emailService;

    public NewsletterService(SentEmailsService sentEmailsService, EmailService emailService) {
        this.sentEmailsService = sentEmailsService;
        this.emailService = emailService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendNewsletterToCustomer(UserData user, NewsletterData newsletter) {
        log.debug("Start sending newsletter message to customer: " + user.getId());
        emailService.sendNewsletterMail(user, newsletter);
        log.debug("Newsletter message sent to customer: " + user.getId());
    }
}
