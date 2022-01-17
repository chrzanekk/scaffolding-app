package pl.com.chrzanowski.scaffolding.domain;

import java.math.BigDecimal;
import java.util.List;

public class ServiceActionsDemandResultData {
    private BigDecimal summaryNetValue;
    private BigDecimal summaryTaxValue;
    private BigDecimal summaryGrossValue;

    private List<ServiceActionsData> serviceActions;

    public ServiceActionsDemandResultData(BigDecimal summaryNetValue, BigDecimal summaryTaxValue, BigDecimal summaryGrossValue, List<ServiceActionsData> serviceActions) {
        this.summaryNetValue = summaryNetValue;
        this.summaryTaxValue = summaryTaxValue;
        this.summaryGrossValue = summaryGrossValue;
        this.serviceActions = serviceActions;
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

    public List<ServiceActionsData> getServiceActions() {
        return serviceActions;
    }
}
