package pl.com.chrzanowski.scaffolding.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.chrzanowski.scaffolding.Application;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelData;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelFilter;
import pl.com.chrzanowski.scaffolding.logic.vehiclemodels.VehicleModelService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = Application.class)
public class VehicleModelServiceIT {

    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private VehicleModelServiceFixture vehicleModelServiceFixture;

    @Autowired
    private VehicleModelServiceDB vehicleModelServiceDB;


    @Test
    public void checkIsAnyDataExists() {


        vehicleModelServiceFixture.createVehicleBrandsAndModels();

        VehicleModelFilter filter = new VehicleModelFilter();

        Integer size = vehicleModelService.find(filter).size();

        assertThat(size).isNotZero();
    }

    @Test
    public void checkIfTheGivenNameExistsWithPositiveResult() {

        vehicleModelServiceFixture.createVehicleBrandsAndModels();

        VehicleModelFilter filter = new VehicleModelFilter("A4");

        List<VehicleModelData> result = vehicleModelService.find(filter);

        assertThat(result).hasSize(1);
    }

    @Test
    public void checkIfTheGivenNameExistsWithNegativeResult() {


        vehicleModelServiceFixture.createVehicleBrandsAndModels();

        VehicleModelFilter filter = new VehicleModelFilter("A5");

        List<VehicleModelData> result = vehicleModelService.find(filter);

        assertThat(result).hasSize(0);
    }

    @Test
    public void checkIfGivenModelNameExistsWithBrandIdWithNegativeResult() {


        vehicleModelServiceFixture.createVehicleBrandsAndModels();

        VehicleModelFilter filter = new VehicleModelFilter(null,12L,"A5");

        List<VehicleModelData> result = vehicleModelService.find(filter);

        assertThat(result).hasSize(0);
    }

    @Test
    public void checkIfGivenModelNameExistsWithBrandIdWithPositiveResult() {

        vehicleModelServiceFixture.createVehicleBrandsAndModels();

        VehicleModelFilter filter = new VehicleModelFilter(null,1L,"A4");

        List<VehicleModelData> result = vehicleModelService.find(filter);

        assertThat(result).hasSize(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNameWithoutBrandId() {

        vehicleModelServiceFixture.createVehicleBrandsAndModels();

        VehicleModelData data = new VehicleModelData("Mercedes",null);

        vehicleModelService.add(data);
    }


    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenEmptyStringAsNameAndNullAsBrandId() {


        vehicleModelServiceFixture.createVehicleBrandsAndModels();

        VehicleModelData data = new VehicleModelData("",null);

        vehicleModelService.add(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenEmptyStringAsNameAndBrandId() {


        vehicleModelServiceFixture.createVehicleBrandsAndModels();

        VehicleModelData data = new VehicleModelData("",1L);

        vehicleModelService.add(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsNameAndBrandId() {


        vehicleModelServiceFixture.createVehicleBrandsAndModels();

        VehicleModelData data = new VehicleModelData(null,1L);

        vehicleModelService.add(data);
    }



}
