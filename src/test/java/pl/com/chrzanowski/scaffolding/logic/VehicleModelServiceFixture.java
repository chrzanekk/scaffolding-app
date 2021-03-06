package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.com.chrzanowski.scaffolding.domain.vehiclebrands.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.vehiclemodels.VehicleModelData;
import pl.com.chrzanowski.scaffolding.logic.vehiclebrands.VehicleBrandService;
import pl.com.chrzanowski.scaffolding.logic.vehiclemodels.VehicleModelService;

import javax.persistence.EntityManager;

@Service
public class VehicleModelServiceFixture {

    @Autowired
    private VehicleBrandService vehicleBrandService;

    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private VehicleModelServiceDB vehicleModelServiceDB;

    @Autowired
    private EntityManager em;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createVehicleBrandsAndModels() {

        vehicleModelServiceDB.createTable();

        VehicleBrandData audi = new VehicleBrandData("Audi");
        Long audiId = vehicleBrandService.add(audi);

        VehicleBrandData bmw = new VehicleBrandData("BMW");
        Long bmwId = vehicleBrandService.add(bmw);

        VehicleBrandData mercedes = new VehicleBrandData("Mercedes");
        Long mercedesId = vehicleBrandService.add(mercedes);

        VehicleModelData a4 = new VehicleModelData(
                "A4",
                audiId);
        vehicleModelService.add(a4);

        VehicleModelData m3 = new VehicleModelData(
                "M3",
                bmwId);
        vehicleModelService.add(m3);

        VehicleModelData g68 = new VehicleModelData(
                "G68",
                mercedesId);
        vehicleModelService.add(g68);

        em.flush();

    }
}
