package pl.com.chrzanowski.scaffolding.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableCaching
@EnableScheduling
public class ApplicationConfig {


    @Value("${reply-to-email}")
    private String replyToEmail;

    @Value("${from-email}")
    private String fromEmail;

    @Value("${company.name}")
    private String companyName;

    @Value("${company.address.first.line}")
    private String companyAddressFirstLine;

    @Value("${company.address.second.line}")
    private String companyAddressSecondLine;

    @Value("${company.nip}")
    private String companyNip;

    @Value("${company.regon}")
    private String companyRegon;

    @Value("${company.contact.mail}")
    private String companyContactMail;

    @Value("${company.contact.mailto}")
    private String companyContactMailto;

    @Value("${course-platform.url}")
    private String scaffoldingAppUrl;

    @Value("${password.reset.token.validity.time.in.minutes}")
    private Long passwordResetTokenValidityTimeInMinutes;

    @Value("${logo.front.path}")
    private String logoFrontPath;

    @Value("${logo.panel.path}")
    private String logoPanelPath;

    @Value("${favicon.path}")
    private String faviconPath;

    @Value("${app.name.front}")
    private String appNameFront;

    @Value("${app.name.panel}")
    private String appNamePanel;

    @Value("${company.country}")
    private String companyCountry;

    @Value("${company.page.url}")
    private String companyPageUrl;

    private String templateNameProductsDemandPdf = "products-demand-pdf.html";


    public String getReplyToEmail() {
        return replyToEmail;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddressFirstLine() {
        return companyAddressFirstLine;
    }

    public String getCompanyAddressSecondLine() {
        return companyAddressSecondLine;
    }

    public String getCompanyNip() {
        return companyNip;
    }

    public String getCompanyRegon() {
        return companyRegon;
    }

    public String getCompanyContactMail() {
        return companyContactMail;
    }

    public String getCompanyContactMailto() {
        return companyContactMailto;
    }

    public String getScaffoldingAppUrl() {
        return scaffoldingAppUrl;
    }



    public Long getPasswordResetTokenValidityTimeInMinutes() {
        return passwordResetTokenValidityTimeInMinutes;
    }

    public String getLogoFrontPath() {
        return logoFrontPath;
    }

    public String getLogoPanelPath() {
        return logoPanelPath;
    }

    public String getAppNameFront() {
        return appNameFront;
    }

    public String getAppNamePanel() {
        return appNamePanel;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public String getCompanyPageUrl() {
        return companyPageUrl;
    }

    public String getFaviconPath() {
        return faviconPath;
    }


    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
}
