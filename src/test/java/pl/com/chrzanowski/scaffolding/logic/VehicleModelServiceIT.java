package pl.com.chrzanowski.scaffolding.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.chrzanowski.scaffolding.Application;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelData;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelFilter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = Application.class)
public class VehicleModelServiceIT {

    @Autowired
    private VehicleModelsService vehicleModelsService;


    @Test
    public void checkIsAnyDataExists() {
        VehicleModelFilter filter = new VehicleModelFilter();

        Integer size = vehicleModelsService.find(filter).size();

        assertThat(size).isNotZero();
    }

    @Test
    public void checkIfTheGivenNameExistsWithPositiveResult() {

        VehicleModelFilter filter = new VehicleModelFilter("A4");

        List<VehicleModelData> result = vehicleModelsService.find(filter);

        assertThat(result).hasSize(1);
    }

    @Test
    public void checkIfTheGivenNameExistsWithNegativeResult() {

        VehicleModelFilter filter = new VehicleModelFilter("A5");

        List<VehicleModelData> result = vehicleModelsService.find(filter);

        assertThat(result).hasSize(0);
    }

}
