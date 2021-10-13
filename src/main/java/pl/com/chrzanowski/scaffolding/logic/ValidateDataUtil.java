package pl.com.chrzanowski.scaffolding.logic;


import pl.com.chrzanowski.scaffolding.domain.VehicleBrandFilter;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelFilter;


public class ValidateDataUtil {

    private static IVehicleBrand iVehicleBrand;
    private static IVehicleModel iVehicleModel;



    public static boolean isBrandNameExist(String brandName) {
        return !iVehicleBrand.find(new VehicleBrandFilter(brandName)).isEmpty();
    }

    public static boolean isModelNameExist(String modelName) {
        return !iVehicleModel.find(new VehicleModelFilter(modelName)).isEmpty();
    }
}
