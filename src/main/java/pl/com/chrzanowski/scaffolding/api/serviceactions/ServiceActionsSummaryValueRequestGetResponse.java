package pl.com.chrzanowski.scaffolding.api.serviceactions;

public class ServiceActionsSummaryValueRequestGetResponse {

    private ServiceActionsSummaryValueGetResponse summaryValue;

    public ServiceActionsSummaryValueRequestGetResponse(ServiceActionsSummaryValueGetResponse summaryValue) {
        this.summaryValue = summaryValue;
    }

    public ServiceActionsSummaryValueGetResponse getSummaryValue() {
        return summaryValue;
    }
}
