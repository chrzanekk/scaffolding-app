package pl.com.chrzanowski.scaffolding.logic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.chrzanowski.scaffolding.Application;
import pl.com.chrzanowski.scaffolding.domain.vehiclebrands.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.vehiclebrands.VehicleBrandFilter;
import pl.com.chrzanowski.scaffolding.logic.vehiclebrands.VehicleBrandService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class VehicleBrandServiceIT {

    @Autowired
    private VehicleBrandService vehicleBrandService;

    @Autowired
    private VehicleBrandServiceFixture vehicleBrandServiceFixture;

    @Autowired
    private VehicleBrandServiceDB vehicleBrandServiceDB;

    @Test
    public void checkIsAnyDataExists() {
        vehicleBrandServiceDB.createTable();

        vehicleBrandServiceFixture.createVehicleBrands();

        VehicleBrandFilter filter = new VehicleBrandFilter();

        Integer size = vehicleBrandService.find(filter).size();

        assertThat(size).isNotZero();
    }

    @Test
    public void checkIfTheGivenNameExistsWithPositiveResult() {
        vehicleBrandServiceDB.createTable();

        vehicleBrandServiceFixture.createVehicleBrands();

        VehicleBrandFilter filter = new VehicleBrandFilter("Audi");

        List<VehicleBrandData> result = vehicleBrandService.find(filter);

        assertThat(result).hasSize(1);

    }

    @Test
    public void checkIfTheGivenNameExistsWithNegativeResult() {
        vehicleBrandServiceDB.createTable();

        vehicleBrandServiceFixture.createVehicleBrands();

        VehicleBrandFilter filter = new VehicleBrandFilter("Moskwicz");

        List<VehicleBrandData> result = vehicleBrandService.find(filter);

        assertThat(result).hasSize(0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNameIsEmptyString() {
        vehicleBrandServiceDB.createTable();

        vehicleBrandServiceFixture.createVehicleBrands();

        VehicleBrandData data = new VehicleBrandData("");

        vehicleBrandService.add(data);
    }


    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNameIsNull() {
        vehicleBrandServiceDB.createTable();

        vehicleBrandServiceFixture.createVehicleBrands();

        VehicleBrandData data = new VehicleBrandData(null);

        vehicleBrandService.add(data);
    }

}
