package pl.com.chrzanowski.scaffolding.domain.adviser;

public class ContextVariableData {
    private final Long id;
    private final String applicationId;
    private final String context;
    private final String name;
    private final String type;
    private final String value;
    private final String contextId;

    public ContextVariableData(String applicationId, String context, String name, String type, String value, String contextId) {
        this.applicationId = applicationId;
        this.context = context;
        this.name = name;
        this.type = type;
        this.value = value;
        this.contextId = contextId;
        this.id = null;
    }

    public ContextVariableData(Long id, String applicationId, String context, String name, String type, String value, String contextId) {
        this.id = id;
        this.applicationId = applicationId;
        this.context = context;
        this.name = name;
        this.type = type;
        this.value = value;
        this.contextId = contextId;
    }

    public Long getId() {
        return id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getContext() {
        return context;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getContextId() {
        return contextId;
    }
}
