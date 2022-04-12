package pl.com.chrzanowski.scaffolding.domain.serviceactions;

import pl.com.chrzanowski.scaffolding.domain.vehicles.VehicleData;

import java.math.BigDecimal;
import java.util.List;

public class ServiceActionsDemandResultData {
    private BigDecimal summaryNetValue;
    private BigDecimal summaryTaxValue;
    private BigDecimal summaryGrossValue;
    private VehicleData vehicleData;
    private List<ServiceActionsData> serviceActions;
    private String createDate;

    public ServiceActionsDemandResultData(BigDecimal summaryNetValue,
                                          BigDecimal summaryTaxValue,
                                          BigDecimal summaryGrossValue,
                                          List<ServiceActionsData> serviceActions,
                                          VehicleData vehicleData,
                                          String createDate) {
        this.summaryNetValue = summaryNetValue;
        this.summaryTaxValue = summaryTaxValue;
        this.summaryGrossValue = summaryGrossValue;
        this.serviceActions = serviceActions;
        this.vehicleData = vehicleData;
        this.createDate = createDate;
    }

    public BigDecimal getSummaryNetValue() {
        return summaryNetValue;
    }

    public BigDecimal getSummaryTaxValue() {
        return summaryTaxValue;
    }

    public BigDecimal getSummaryGrossValue() {
        return summaryGrossValue;
    }

    public List<ServiceActionsData> getServiceActions() {
        return serviceActions;
    }

    public VehicleData getVehicleData() {
        return vehicleData;
    }

    public String getCreateDate() {
        return createDate;
    }
}
