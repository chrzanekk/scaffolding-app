package pl.com.chrzanowski.scaffolding.domain.adviser;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AdvisePurchaseData {
    private Long id;
    private String domainId;
    private Long categoryId;
    private String applicationId;
    private String domain;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private BigDecimal price;
    private String currencyCode;
    private Long accountId;
    private String externalTransactionId;
    private PurchasedCategoryStatus status;
    private PeriodType period;


    public AdvisePurchaseData(Long id, String externalTransactionId, PurchasedCategoryStatus status) {
        this.id = id;
        this.externalTransactionId = externalTransactionId;
        this.status = status;
    }

    public AdvisePurchaseData(String domainId, Long categoryId, String applicationId, String domain, LocalDate dateFrom, LocalDate dateTo, BigDecimal price, String currencyCode, Long accountId, PeriodType period) {
        this.domainId = domainId;
        this.categoryId = categoryId;
        this.applicationId = applicationId;
        this.domain = domain;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.price = price;
        this.currencyCode = currencyCode;
        this.accountId = accountId;
        this.period = period;
    }

    public String getDomainId() {
        return domainId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getDomain() {
        return domain;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getExternalTransactionId() {
        return externalTransactionId;
    }

    public PurchasedCategoryStatus getStatus() {
        return status;
    }

    public PeriodType getPeriod() {
        return period;
    }
}
