package pl.com.chrzanowski.scaffolding.domain;

import java.time.LocalDateTime;

public class CoreFieldsData {

    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;
    private Long createUserId;
    private Long modifyUserId;
    private Long removeUserId;

    public CoreFieldsData(Long id,
                          String name,
                          LocalDateTime createDate,
                          LocalDateTime modifyDate,
                          LocalDateTime removeDate,
                          Long createUserId,
                          Long modifyUserId,
                          Long removeUserId) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.removeDate = removeDate;
        this.createUserId = createUserId;
        this.modifyUserId = modifyUserId;
        this.removeUserId = removeUserId;
    }

    public CoreFieldsData(String name, Long createUserId) {
        this.name = name;
        this.createUserId = createUserId;
        this.createDate = LocalDateTime.now();
    }

    public CoreFieldsData(Long id, String name, Long modifyUserId) {
        this.id = id;
        this.name = name;
        this.modifyUserId = modifyUserId;
        this.modifyDate = LocalDateTime.now();
    }

    public CoreFieldsData(Long id, Long removeUserId) {
        this.id = id;
        this.removeUserId = removeUserId;
        this.removeDate = LocalDateTime.now();
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

    public Long getCreateUserId() {
        return createUserId;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public Long getRemoveUserId() {
        return removeUserId;
    }

    public CoreFieldsData constructorForPostRequest(String name, Long createUserId) {
        return new CoreFieldsData(name,createUserId);
    }

    public CoreFieldsData constructorForPutRequest(Long id, String name, Long modifyUserId) {
        return new CoreFieldsData(id, name, modifyUserId);
    }
}
