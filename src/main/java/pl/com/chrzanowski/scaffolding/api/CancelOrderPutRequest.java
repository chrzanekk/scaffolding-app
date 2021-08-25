package pl.com.chrzanowski.scaffolding.api;

public class CancelOrderPutRequest {
    private String reason;

    public CancelOrderPutRequest() {
    }

    public CancelOrderPutRequest(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
