package pl.com.chrzanowski.scaffolding.api.serviceactions;

import java.math.BigDecimal;

public class ServiceActionsSummaryValueGetResponse {

    private String summaryNetValue;
    private String summaryTaxValue;
    private String summaryGrossValue;

    public ServiceActionsSummaryValueGetResponse(BigDecimal summaryNetValue,
                                                 BigDecimal summaryTaxValue,
                                                 BigDecimal summaryGrossValue) {
        this.summaryNetValue = summaryNetValue.toString();
        this.summaryTaxValue = summaryTaxValue.toString();
        this.summaryGrossValue = summaryGrossValue.toString();
    }

    public String getSummaryNetValue() {
        return summaryNetValue;
    }

    public String getSummaryTaxValue() {
        return summaryTaxValue;
    }

    public String getSummaryGrossValue() {
        return summaryGrossValue;
    }
}
