package pl.com.chrzanowski.scaffolding.domain;

import java.math.BigDecimal;

public class ServiceActionsInvoiceSummaryData {

    private BigDecimal summaryNetValue;
    private BigDecimal summaryTaxValue;
    private BigDecimal summaryGrossValue;


    public ServiceActionsInvoiceSummaryData(BigDecimal summaryNetValue, BigDecimal summaryTaxValue, BigDecimal summaryGrossValue) {
        this.summaryNetValue = summaryNetValue;
        this.summaryTaxValue = summaryTaxValue;
        this.summaryGrossValue = summaryGrossValue;
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
