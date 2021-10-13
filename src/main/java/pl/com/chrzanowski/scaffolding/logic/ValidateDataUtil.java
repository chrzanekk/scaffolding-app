package pl.com.chrzanowski.scaffolding.logic;


import pl.com.chrzanowski.scaffolding.domain.VehicleBrandFilter;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelFilter;


public class ValidateDataUtil {

    private static VehicleBrandJdbcRepository vehicleBrandJdbcRepository;
    private static VehicleModelJdbcRepository vehicleModelJdbcRepository;

    public ValidateDataUtil() {
    }

    public static boolean isBrandNameExist(String brandName) {
        return !vehicleBrandJdbcRepository.find(new VehicleBrandFilter(brandName)).isEmpty();
    }

    public static boolean isModelNameExist(String modelName) {
        return !vehicleModelJdbcRepository.find(new VehicleModelFilter(modelName)).isEmpty();
    }
}
