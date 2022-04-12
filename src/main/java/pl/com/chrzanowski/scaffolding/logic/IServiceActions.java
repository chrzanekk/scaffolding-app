package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.serviceactions.ServiceActionsData;
import pl.com.chrzanowski.scaffolding.domain.serviceactions.ServiceActionsFilter;
import pl.com.chrzanowski.scaffolding.domain.serviceactions.ServiceActionsInvoiceSummaryData;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface IServiceActions {
    List<ServiceActionsData> find(ServiceActionsFilter filter);
    ServiceActionsData findById(ServiceActionsFilter filter);
    ServiceActionsInvoiceSummaryData findActionInvoicesSummary(ServiceActionsFilter filter);
    ByteArrayInputStream getActionsDemandWithSummaryPdf(ServiceActionsFilter filter);
    Long add(ServiceActionsData data);
    void update(ServiceActionsData data);
}
