package pl.com.chrzanowski.scaffolding.api.scaffolding;

import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceActionsData;

import java.time.LocalDateTime;

public class ScaffServiceActionTypesGetResponse {

    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public ScaffServiceActionTypesGetResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public LocalDateTime getRemoveDate() {
        return removeDate;
    }
}
