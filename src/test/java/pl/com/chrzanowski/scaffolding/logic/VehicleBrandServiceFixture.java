package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.com.chrzanowski.scaffolding.domain.vehiclebrands.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.logic.vehiclebrands.VehicleBrandService;

import javax.persistence.EntityManager;

@Service
public class VehicleBrandServiceFixture {

    @Autowired
    private VehicleBrandService vehicleBrandService;

    @Autowired
    private EntityManager em;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createVehicleBrands() {
        VehicleBrandData audi = new VehicleBrandData("Audi");
        vehicleBrandService.add(audi);

        VehicleBrandData bmw = new VehicleBrandData("BMW");
        vehicleBrandService.add(bmw);

        VehicleBrandData mercedes = new VehicleBrandData("Mercedes");
        vehicleBrandService.add(mercedes);

        em.flush();

    }
}
