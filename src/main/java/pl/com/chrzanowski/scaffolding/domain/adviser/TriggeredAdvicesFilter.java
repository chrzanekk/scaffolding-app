package pl.com.chrzanowski.scaffolding.domain.adviser;

import pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil;

import java.time.LocalDateTime;

public class TriggeredAdvicesFilter {
    private Long id;
    private Long adviceId;
    private String domainId;
    private String type;
    private String lang;
    private String scope;
    private String action;
    private Long page;
    private Long pageSize;
    private String nameAsSubstring;
    private String appIdAsSubstring;
    private String domainAsSubstring;
    private String appId;
    private String domain;
    private LocalDateTime triggerDateTimeFrom;
    private LocalDateTime triggerDateTimeTo;
    private JdbcUtil.SortColumns sortColumns;
    private Long score;


    public TriggeredAdvicesFilter(String domainId, Long page, Long pageSize, String appId, String domain, JdbcUtil.SortColumns sortColumns) {
        this.domainId = domainId;
        this.page = page;
        this.pageSize = pageSize;
        this.appId = appId;
        this.domain = domain;
        this.sortColumns = sortColumns;
    }

    public TriggeredAdvicesFilter(String domainId, Long page, Long pageSize, String appId, String domain, JdbcUtil.SortColumns sortColumns, Long score) {
        this.domainId = domainId;
        this.page = page;
        this.pageSize = pageSize;
        this.appId = appId;
        this.domain = domain;
        this.sortColumns = sortColumns;
        this.score = score;
    }

    public TriggeredAdvicesFilter(String domainId, String appId, String domain, Long id) {
        this.domainId = domainId;
        this.page = page;
        this.pageSize = pageSize;
        this.appId = appId;
        this.domain = domain;
        this.sortColumns = sortColumns;
        this.score = score;
        this.id = id;
    }

    public TriggeredAdvicesFilter(Long adviceId, String appIdAsSubstring, String domainAsSubstring, String domainId, String type, String lang, String scope, String action) {
        this.adviceId = adviceId;
        this.appIdAsSubstring = appIdAsSubstring;
        this.domainAsSubstring = domainAsSubstring;
        this.domainId = domainId;
        this.type = type;
        this.lang = lang;
        this.scope = scope;
        this.action = action;
    }

    public TriggeredAdvicesFilter(Long page, Long pageSize, String nameAsSubstring, String appIdAsSubstring, String domainAsSubstring, LocalDateTime triggerDateTimeFrom, LocalDateTime triggerDateTimeTo) {
        this.page = page;
        this.pageSize = pageSize;
        this.nameAsSubstring = nameAsSubstring;
        this.appIdAsSubstring = appIdAsSubstring;
        this.domainAsSubstring = domainAsSubstring;
        this.triggerDateTimeFrom = triggerDateTimeFrom;
        this.triggerDateTimeTo = triggerDateTimeTo;
    }

    public Long getAdviceId() {
        return adviceId;
    }

    public String getAppIdAsSubstring() {
        return appIdAsSubstring;
    }

    public String getDomainAsSubstring() {
        return domainAsSubstring;
    }

    public String getDomainId() {
        return domainId;
    }

    public String getType() {
        return type;
    }

    public String getLang() {
        return lang;
    }

    public String getScope() {
        return scope;
    }

    public String getAction() {
        return action;
    }

    public String getNameAsSubstring() {
        return nameAsSubstring;
    }

    public LocalDateTime getTriggerDateTimeFrom() {
        return triggerDateTimeFrom;
    }

    public LocalDateTime getTriggerDateTimeTo() {
        return triggerDateTimeTo;
    }

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public JdbcUtil.SortColumns getSortColumns() {
        return sortColumns;
    }

    public String getAppId() {
        return appId;
    }

    public String getDomain() {
        return domain;
    }

    public Long getScore() {
        return score;
    }

    public Long getId() {
        return id;
    }
}
