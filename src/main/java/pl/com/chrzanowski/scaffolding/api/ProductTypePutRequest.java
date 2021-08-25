package pl.com.chrzanowski.scaffolding.api;

public class ProductTypePutRequest {
    private String type;

    public ProductTypePutRequest() {
    }

    public ProductTypePutRequest(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
