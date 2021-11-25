package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.ServiceActionsData;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionsFilter;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionsInvoiceSummaryData;

import java.math.BigDecimal;
import java.util.List;

public interface IServiceActions {
    List<ServiceActionsData> find(ServiceActionsFilter filter);
    ServiceActionsData findById(ServiceActionsFilter filter);
    ServiceActionsInvoiceSummaryData getActionInvoicesSummary(ServiceActionsFilter filter);
    Long add(ServiceActionsData data);
    void update(ServiceActionsData data);
    void delete(ServiceActionsData data);
}
