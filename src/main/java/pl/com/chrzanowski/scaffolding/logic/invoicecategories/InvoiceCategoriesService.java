package pl.com.chrzanowski.scaffolding.logic.invoicecategories;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.invoicecategories.InvoiceCategoryData;
import pl.com.chrzanowski.scaffolding.domain.invoicecategories.InvoiceCategoryFilter;
import pl.com.chrzanowski.scaffolding.logic.IInvoiceCategories;
import pl.com.chrzanowski.scaffolding.logic.utils.DataValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;

@Service
public class InvoiceCategoriesService implements IInvoiceCategories {

    private InvoiceCategoriesJdbcRepository invoiceCategoriesJdbcRepository;

    public InvoiceCategoriesService(InvoiceCategoriesJdbcRepository invoiceCategoriesJdbcRepository) {
        this.invoiceCategoriesJdbcRepository = invoiceCategoriesJdbcRepository;
    }

    @Override
    public Long add(InvoiceCategoryData data) {
        validateData(data);
        return invoiceCategoriesJdbcRepository.create(data);

    }

    @Override
    public void update(InvoiceCategoryData data) {
        validateData(data);
        invoiceCategoriesJdbcRepository.update(data);
    }

    public void remove(InvoiceCategoryData data) {
        invoiceCategoriesJdbcRepository.remove(data);
    }

    @Override
    public List<InvoiceCategoryData> find(InvoiceCategoryFilter filter) {
        return getContractorTypes(invoiceCategoriesJdbcRepository.find(filter));
    }

    private List<InvoiceCategoryData> getContractorTypes(List<Map<String, Object>> data) {
        List<InvoiceCategoryData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new InvoiceCategoryData(
                    getLong(row, "id"),
                    getString(row, "name"),
                    getDateTime(row, "create_date"),
                    getDateTime(row, "modify_date"),
                    getDateTime(row, "remove_date"),
                    getLong(row, "create_user_id"),
                    getLong(row, "modify_user_id"),
                    getLong(row, "remove_user_id")
            ));
        }
        return list;
    }

    private void validateData(InvoiceCategoryData data) {
        DataValidationUtil.validateTextField(data.getName(), "Typ kontrahenta");
    }
}
