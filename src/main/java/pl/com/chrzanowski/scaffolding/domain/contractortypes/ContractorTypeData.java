package pl.com.chrzanowski.scaffolding.domain.contractortypes;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsData;

import java.time.LocalDateTime;

public class ContractorTypeData extends CoreFieldsData {
    public ContractorTypeData(String name, Long createUserId) {
        super(name, createUserId);
    }

    public ContractorTypeData(Long id, String name, Long modifyUserId) {
        super(id, name, modifyUserId);
    }

    public ContractorTypeData(Long id, Long removeUserId) {
        super(id, removeUserId);
    }

    public ContractorTypeData(Long id,
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
