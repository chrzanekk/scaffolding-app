package pl.com.chrzanowski.scaffolding.logic.contractors;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorData;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorFilter;
import pl.com.chrzanowski.scaffolding.logic.IContractor;
import pl.com.chrzanowski.scaffolding.logic.utils.DataValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;


@Service
public class ContractorService implements IContractor {

    private ContractorJdbcRepository contractorJdbcRepository;
    private ContractorCurrencyService contractorCurrencyService;

    public ContractorService(ContractorJdbcRepository contractorJdbcRepository, ContractorCurrencyService contractorCurrencyService) {
        this.contractorJdbcRepository = contractorJdbcRepository;
        this.contractorCurrencyService = contractorCurrencyService;
    }

    @Override
    public Long create(ContractorData data) {
        ContractorData contractorData = formatFieldsForCreate(data);
        validateData(contractorData);
        Long contractorId = contractorJdbcRepository.create(data);
        contractorCurrencyService.validateAndCreateCurrencyList(new ContractorData(
                contractorId,
                contractorData.getName(),
                contractorData.getCreateDate(),
                contractorData.getCreateUserId(),
                contractorData));
        return contractorId;
    }

    @Override
    public void update(ContractorData data) {
        ContractorData contractorData = formatFieldsForUpdate(data);
        validateData(data);
        if (data.getCurrencyList() != null) {
            contractorCurrencyService.deleteCurrency(data);
            contractorCurrencyService.validateAndCreateCurrencyList(data);
        } else {
            contractorData = new ContractorData(contractorData,
                    contractorCurrencyService.getCurrencyListForContractors(data));
        }
        contractorJdbcRepository.update(contractorData);
    }

    @Override
    public void remove(ContractorData data) {
        contractorJdbcRepository.update(data);
    }

    @Override
    public List<ContractorData> find(ContractorFilter filter) {
        return getContractors(contractorJdbcRepository.find(filter));
    }

    @Override
    public List<ContractorData> findWithCurrency(List<ContractorData> contractors) {
        List<ContractorData> contractorsWithCurrency = new ArrayList<>();
        if (contractors == null) {
            contractorsWithCurrency = null;
        } else {
            for (ContractorData data : contractors) {
                contractorsWithCurrency.add(new ContractorData(
                        data.getId(),
                        data.getName(),
                        data.getCreateDate(),
                        data.getModifyDate(),
                        data.getRemoveDate(),
                        data.getCreateUserId(),
                        data.getModifyUserId(),
                        data.getRemoveUserId(),
                        data.getContractorType(),
                        data.getTaxNumber(),
                        data.getStreet(),
                        data.getBuildingNo(),
                        data.getApartmentNo(),
                        data.getPostalCode(),
                        data.getCity(),
                        data.getCountry(),
                        data.getBankAccount(),
                        data.getDescription(),
                        contractorCurrencyService.getCurrencyListForContractors(data)
                ));
            }
        }
        return contractorsWithCurrency;
    }

    private void validateData(ContractorData data) {
        DataValidationUtil.validateTextField(data.getName(), "Nazwa");
        DataValidationUtil.validateTextField(data.getContractorType(), "Typ kontrahenta");
        DataValidationUtil.validateTextField(data.getTaxNumber(), "NIP");
        DataValidationUtil.validateTextField(data.getStreet(), "Ulica");
        DataValidationUtil.validateTextField(data.getBuildingNo(), "Numer budynku");
        DataValidationUtil.validateTextField(data.getPostalCode(), "Kod pocztowy");
        DataValidationUtil.validateTextField(data.getCity(), "Miasto");
        DataValidationUtil.validateTextField(data.getCountry(), "Kraj");
        DataValidationUtil.validateTextField(data.getBankAccount(), "Konto bankowe");

    }

    private List<ContractorData> getContractors(List<Map<String, Object>> data) {
        List<ContractorData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new ContractorData(
                    getLong(row, "id"),
                    getString(row, "name"),
                    getDateTime(row, "create_date"),
                    getDateTime(row, "modify_date"),
                    getDateTime(row, "remove_date"),
                    getLong(row, "create_user_id"),
                    getLong(row, "modify_user_id"),
                    getLong(row, "remove_user_id"),
                    getString(row, "contractor_type"),
                    getString(row, "tax_number"),
                    getString(row, "street"),
                    getString(row, "building_number"),
                    getString(row, "apartment_number"),
                    getString(row, "postal_code"),
                    getString(row, "city"),
                    getString(row, "country"),
                    getString(row, "bank_account_number"),
                    getString(row, "description")
            ));
        }
        return list;
    }

    private ContractorData formatFieldsForUpdate(ContractorData data) {
        String formattedTaxNumber = DataValidationUtil.formatTaxNumber(data.getTaxNumber());
        String formattedPostalCode = DataValidationUtil.formatPostalCode(data.getPostalCode());
        return new ContractorData(
                data.getId(),
                data.getName(),
                data.getModifyUserId(),
                data.getContractorType(),
                formattedTaxNumber,
                data.getStreet(),
                data.getBuildingNo(),
                data.getApartmentNo(),
                formattedPostalCode,
                data.getCity(),
                data.getCountry(),
                data.getBankAccount(),
                data.getDescription(),
                data.getCurrencyList());
    }
    private ContractorData formatFieldsForCreate(ContractorData data) {
        String formattedTaxNumber = DataValidationUtil.formatTaxNumber(data.getTaxNumber());
        String formattedPostalCode = DataValidationUtil.formatPostalCode(data.getPostalCode());
        return new ContractorData(
                data.getName(),
                data.getCreateUserId(),
                data.getContractorType(),
                formattedTaxNumber,
                data.getStreet(),
                data.getBuildingNo(),
                data.getApartmentNo(),
                formattedPostalCode,
                data.getCity(),
                data.getCountry(),
                data.getBankAccount(),
                data.getDescription(),
                data.getCurrencyList());
    }


}
