package cleaning.toDelete.api.lifeadviser;

public class MyCategoriesGetResponse {

    private Long id;

    private String name;
    private String description;
    private String price;
    private String period;
    private String dateFrom;
    private String dateTo;
    private String status;


    public MyCategoriesGetResponse(Long id, String name, String description, String price, String period, String dateFrom, String dateTo, String status) {
        this.id = id;
        this.name = name;

        this.description = description;
        this.price = price;
        this.period = period;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;

        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getPeriod() {
        return period;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }


    public String getStatus() {
        return status;
    }
}
