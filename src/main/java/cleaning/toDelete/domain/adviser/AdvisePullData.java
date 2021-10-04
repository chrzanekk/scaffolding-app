package cleaning.toDelete.domain.adviser;

public class AdvisePullData {
    private String id;
    private String context;

    public AdvisePullData(String id, String context) {
        this.id = id;
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public String getContext() {
        return context;
    }
}
