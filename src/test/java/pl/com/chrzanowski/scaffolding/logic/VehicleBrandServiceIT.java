package pl.com.chrzanowski.scaffolding.logic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.chrzanowski.scaffolding.Application;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandFilter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class VehicleBrandServiceIT {

    @Autowired
    private VehicleBrandService vehicleBrandService;

    @Test
    public void checkIsAnyDataExists() {
        VehicleBrandFilter filter = new VehicleBrandFilter();

        Integer size = vehicleBrandService.find(filter).size();

        assertThat(size).isNotZero();
    }

    @Test
    public void checkIfTheGivenNameExistsWithPositiveResult() {

        VehicleBrandFilter filter = new VehicleBrandFilter("Audi");

        List<VehicleBrandData> result = vehicleBrandService.find(filter);

        assertThat(result).hasSize(1);

    }

    @Test
    public void checkIfTheGivenNameExistsWithNegativeResult() {

        VehicleBrandFilter filter = new VehicleBrandFilter("Moskwicz");

        List<VehicleBrandData> result = vehicleBrandService.find(filter);

        assertThat(result).hasSize(0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNameIsEmptyString() {

        VehicleBrandData data = new VehicleBrandData("");

        vehicleBrandService.add(data);
    }


    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNameIsNull() {

        VehicleBrandData data = new VehicleBrandData(null);

        vehicleBrandService.add(data);
    }

}
