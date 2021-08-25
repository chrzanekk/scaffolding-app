package pl.com.chrzanowski.scaffolding.api.adviser;

import java.util.List;

public class AdviceCategoriesRequestGetResponse {

    private final List<AdviceCategoryGetResponse> adviceCategories;
    private final Long totalAdviceCategoriesCount;

    public AdviceCategoriesRequestGetResponse(List<AdviceCategoryGetResponse> adviceCategories, Long totalAdviceCategoriesCount) {
        this.adviceCategories = adviceCategories;
        this.totalAdviceCategoriesCount = totalAdviceCategoriesCount;
    }

    public List<AdviceCategoryGetResponse> getAdviceCategories() {
        return adviceCategories;
    }

    public Long getTotalAdviceCategoriesCount() {
        return totalAdviceCategoriesCount;
    }

}
