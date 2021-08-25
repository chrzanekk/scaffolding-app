package pl.com.chrzanowski.scaffolding.api.courseplatform;

public class BasketCoursesQuantityGetResponse {
    private Long quantity;

    public BasketCoursesQuantityGetResponse(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }
}
