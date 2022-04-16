package pl.com.chrzanowski.scaffolding.domain.costaccounts;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsData;

import java.time.LocalDateTime;

public class CostAccountData extends CoreFieldsData {
    public CostAccountData(String name, Long createUserId) {
        super(name, createUserId);
    }

    public CostAccountData(Long id, String name, Long modifyUserId) {
        super(id, name, modifyUserId);
    }

    public CostAccountData(Long id, Long removeUserId) {
        super(id, removeUserId);
    }

    public CostAccountData(Long id,
                           String name,
                           LocalDateTime createDate,
                           LocalDateTime modifyDate,
                           LocalDateTime removeDate,
                           Long createUserId,
                           Long modifyUserId,
                           Long removeUserId) {
        super(id, name, createDate, modifyDate, removeDate, createUserId, modifyUserId, removeUserId);
    }
}
