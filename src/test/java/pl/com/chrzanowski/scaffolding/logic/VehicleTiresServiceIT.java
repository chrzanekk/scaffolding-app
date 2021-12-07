package pl.com.chrzanowski.scaffolding.logic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.chrzanowski.scaffolding.Application;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresData;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresFilter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class VehicleTiresServiceIT {

    @Autowired
    private VehicleTiresService vehicleTiresService;

    @Test
    public void checkIsAnyDataExists() {

        VehicleTiresFilter filter = new VehicleTiresFilter();

        Integer size = vehicleTiresService.find(filter).size();

        assertThat(size).isNotZero();
    }

    @Test
    public void checkIfTheGivenTireBrandExistsWithPositiveResult() {

        VehicleTiresFilter filter = new VehicleTiresFilter("Hancook", null);

        List<VehicleTiresData> result = vehicleTiresService.find(filter);

        assertThat(result).hasSize(2);
    }

    @Test
    public void checkIfTheGivenTireModelExistsWithPositiveResult() {

        VehicleTiresFilter filter = new VehicleTiresFilter(null, "Winter XL");

        List<VehicleTiresData> result = vehicleTiresService.find(filter);

        assertThat(result).hasSize(2);
    }


}
