package pl.com.chrzanowski.scaffolding.api;

public class ProductTypePostRequest {
    private String type;

    public ProductTypePostRequest() {
    }

    public ProductTypePostRequest(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
