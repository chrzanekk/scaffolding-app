package pl.com.chrzanowski.scaffolding.domain.serviceactions;

import java.math.BigDecimal;

public class ServiceActionsInvoiceSummaryFilter {

    private BigDecimal summaryNetValue;
    private BigDecimal summaryTaxValue;
    private BigDecimal summaryGrossValue;

    public ServiceActionsInvoiceSummaryFilter() {
    }

    public BigDecimal getSummaryNetValue() {
        return summaryNetValue;
    }

    public BigDecimal getSummaryTaxValue() {
        return summaryTaxValue;
    }

    public BigDecimal getSummaryGrossValue() {
        return summaryGrossValue;
    }
}
