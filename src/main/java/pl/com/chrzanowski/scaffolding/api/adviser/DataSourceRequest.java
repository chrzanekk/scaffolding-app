package pl.com.chrzanowski.scaffolding.api.adviser;

import java.util.HashSet;
import java.util.Set;

public class DataSourceRequest {
    private Set<DataSourceElement> elements = new HashSet<>();

    public DataSourceRequest(Set<DataSourceElement> elements) {
        this.elements = elements;
    }

    public Set<DataSourceElement> getElements() {
        return elements;
    }
}
