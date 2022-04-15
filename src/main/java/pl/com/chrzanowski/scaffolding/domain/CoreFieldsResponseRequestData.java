package pl.com.chrzanowski.scaffolding.domain;

public class CoreFieldsResponseRequestData {

    private Long id;
    private String name;
    private String createDate;
    private String modifyDate;
    private String removeDate;
    private Long createUserId;
    private Long modifyUserId;
    private Long removeUserId;

    public CoreFieldsResponseRequestData(Long id,
                                         String name,
                                         String createDate,
                                         String modifyDate,
                                         String removeDate,
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public String getRemoveDate() {
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
}
