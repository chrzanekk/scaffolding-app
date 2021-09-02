package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffNewsletterData;

@Service
public class ScaffNewsletterService {
    private static final Logger log = LoggerFactory.getLogger(ScaffNewsletterService.class);
    private ScaffSentEmailsService scaffSentEmailsService;
    private ScaffEmailService scaffEmailService;

    public ScaffNewsletterService(ScaffSentEmailsService scaffSentEmailsService, ScaffEmailService scaffEmailService) {
        this.scaffSentEmailsService = scaffSentEmailsService;
        this.scaffEmailService = scaffEmailService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendNewsletterToCustomer(ScaffUserData user, ScaffNewsletterData newsletter) {
        log.debug("Start sending newsletter message to customer: " + user.getId());
        scaffEmailService.sendNewsletterMail(user, newsletter);
        log.debug("Newsletter message sent to customer: " + user.getId());
    }
}
