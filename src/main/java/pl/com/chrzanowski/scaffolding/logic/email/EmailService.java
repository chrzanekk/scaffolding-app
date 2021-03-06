package pl.com.chrzanowski.scaffolding.logic.email;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.com.chrzanowski.scaffolding.config.ApplicationConfig;
import pl.com.chrzanowski.scaffolding.domain.*;
import pl.com.chrzanowski.scaffolding.domain.dictionaries.DictionaryData;
import pl.com.chrzanowski.scaffolding.domain.newsletter.NewsletterData;
import pl.com.chrzanowski.scaffolding.domain.users.PasswordResetTokenData;
import pl.com.chrzanowski.scaffolding.domain.users.UserData;
import pl.com.chrzanowski.scaffolding.logic.user.TokensService;
import pl.com.chrzanowski.scaffolding.logic.dictionaries.DictionariesService;
import pl.com.chrzanowski.scaffolding.logic.dictionaries.DictionaryType;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Locale;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private JavaMailSender javaMailSender;
    private ApplicationConfig applicationConfig;
    private TemplateEngine templateEngine;
    private DictionariesService dictionariesService;
    private SentEmailsService sentEmailsService;
    private TokensService tokensService;

    public EmailService(JavaMailSender javaMailSender, ApplicationConfig applicationConfig, TemplateEngine templateEngine, DictionariesService dictionariesService, SentEmailsService sentEmailsService, TokensService tokensService) {
        this.javaMailSender = javaMailSender;
        this.applicationConfig = applicationConfig;
        this.templateEngine = templateEngine;
        this.dictionariesService = dictionariesService;
        this.sentEmailsService = sentEmailsService;
        this.tokensService = tokensService;
    }

    public void sendAfterRegistrationMail(UserData recipient, Locale locale) {
        Context context = new Context(locale);
        context.setVariable("loginPageUrl", applicationConfig.getScaffoldingAppUrl() + "/login");
        context.setVariable("emailConfirmationLink", applicationConfig.getScaffoldingAppUrl() + "/confirm-email/?token=" + tokensService.prepareAndCreate(recipient).getValue());
        String content = templateEngine.process("mail-after-registration", context);
        String title = chooseTitle(EmailTitle.AFTER_REGISTRATION, locale);
        sendEmail(recipient.getLogin(), title, content);
        sentEmailsService.create(new SentEmailData(recipient.getId(), title, content, locale.toLanguageTag(), MailEvent.AFTER_REGISTRATION.getCode()));
    }

    public void sendAfterPasswordChangeMail(UserData recipient) {
        Locale locale = Locale.forLanguageTag(recipient.getLanguage());
        Context context = new Context(locale);
        context.setVariable("pageUrl", applicationConfig.getScaffoldingAppUrl());
        String content = templateEngine.process("mail-after-password-change", context);
        String title = chooseTitle(EmailTitle.AFTER_PASSWORD_CHANGE, locale);
        sendEmail(recipient.getLogin(), title, content);
        sentEmailsService.create(new SentEmailData(recipient.getId(), title, content, locale.toLanguageTag(), MailEvent.AFTER_PASSWORD_CHANGE.getCode()));
    }

    public void sendPasswordResetMail(PasswordResetTokenData token) {
        Locale locale = Locale.forLanguageTag(token.getUser().getLanguage());
        Context context = new Context(locale);
        context.setVariable("passwordRestartUrl", applicationConfig.getScaffoldingAppUrl() + "/reset-password?token=" + token.getValue());
        context.setVariable("tokenValidityTime", applicationConfig.getPasswordResetTokenValidityTimeInMinutes());
        String content = templateEngine.process("mail-password-reset", context);
        String title = chooseTitle(EmailTitle.PASSWORD_RESET, locale);
        sendEmail(token.getUser().getLogin(), title, content);
        sentEmailsService.create(new SentEmailData(token.getUser().getId(), title, content, locale.toLanguageTag()
                , MailEvent.PASSWORD_RESET.getCode()));
    }

    @Transactional
    public void sendNewsletterMail(UserData user, NewsletterData newsletter) {
        Locale locale = Locale.forLanguageTag(user.getLanguage());
        Context context = getContextForNewsletter(newsletter, locale);
        String content = templateEngine.process("mail-newsletter", context);
        String title = chooseTitle(EmailTitle.NEWSLETTER, locale);
        sendEmail(user.getLogin(), title, content);
        sentEmailsService.create(new SentEmailData(user.getId(), title, content, locale.toLanguageTag(), MailEvent.NEWSLETTER.getCode()));
    }

    public void sendEmailConfirmationLink(UserData user) {
        Locale locale = Locale.forLanguageTag(user.getLanguage());
        Context context = new Context(locale);
        context.setVariable("emailConfirmationLink", applicationConfig.getScaffoldingAppUrl() + "/confirm-email/?token=" + tokensService.prepareAndCreate(user).getValue());
        String content = templateEngine.process("mail-email-confirmation-link", context);
        String title = chooseTitle(EmailTitle.EMAIL_CONFIRMATION_LINK, locale);
        sendEmail(user.getLogin(), title, content);
        sentEmailsService.create(new SentEmailData(user.getId(), title, content, locale.toLanguageTag(), MailEvent.EMAIL_CONFIRMATION_LINK.getCode()));
        log.info("Successfully sent email confirmation link to user with ID {}", user.getId());
    }

    private Context getContextForNewsletter(NewsletterData newsletter, Locale locale) {
        Context context = new Context(locale);
        context.setVariable("loginPageUrl", applicationConfig.getScaffoldingAppUrl() + "/login");
        context.setVariable("title", newsletter.getContent().get(locale.getLanguage()).getTitle());
        context.setVariable("content", newsletter.getContent().get(locale.getLanguage()).getHtml());
        return context;
    }

    private void sendEmail(String recipientMail, String subject, String content) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(recipientMail);
            helper.setReplyTo(applicationConfig.getReplyToEmail());
            helper.setFrom(applicationConfig.getFromEmail());
            helper.setSubject(subject);
            helper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            javaMailSender.send(mail);
        }).start();

    }

    private String chooseTitle(EmailTitle emailTitle, Locale locale) {

        List<DictionaryData> list = dictionariesService.getDictionary(DictionaryType.EMAIL_TITLES, locale);

        for (DictionaryData dictionaryData : list) {
            if (dictionaryData.getCode().equals(emailTitle.getCode())) {
                return dictionaryData.getValue();
            }
        }

        throw new IllegalArgumentException("No email title!");

    }

}
