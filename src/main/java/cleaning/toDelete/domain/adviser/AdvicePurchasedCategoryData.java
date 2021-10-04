package cleaning.toDelete.domain.adviser;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AdvicePurchasedCategoryData {
    private Long id;
    private String applicationId;
    private String domain;
    private String domainId;
    private String name;
    private String description;
    private BigDecimal price;
    private String currencyCode;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Long categoryId;
    private PurchasedCategoryStatus status;
    private PeriodType period;

    public AdvicePurchasedCategoryData(Long id, String applicationId, String domain, String domainId, String name, String description, BigDecimal price, String currencyCode, LocalDate dateFrom, LocalDate dateTo, Long categoryId, PurchasedCategoryStatus status, PeriodType period) {
        this.id = id;
        this.applicationId = applicationId;
        this.domain = domain;
        this.domainId = domainId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.currencyCode = currencyCode;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.categoryId = categoryId;
        this.status = status;
        this.period = period;
    }

    public Long getId() {
        return id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getDomain() {
        return domain;
    }

    public String getDomainId() {
        return domainId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public PurchasedCategoryStatus getStatus() {
        return status;
    }

    public PeriodType getPeriod() {
        return period;
    }
}
