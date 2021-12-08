package pl.com.chrzanowski.scaffolding.logic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.chrzanowski.scaffolding.Application;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelData;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresData;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresFilter;

import java.time.LocalDate;
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

    @Test
    public void checkIfTheGivenVehicleIdHasTireWithPositiveResult() {

        VehicleTiresFilter filter = new VehicleTiresFilter(null, 1L);

        List<VehicleTiresData> result = vehicleTiresService.find(filter);

        assertThat(result).hasSize(2);
    }

    @Test
    public void checkIfTheGivenVehicleIdHasTireWithNegativeResult() {

        VehicleTiresFilter filter = new VehicleTiresFilter(null, 4L);

        List<VehicleTiresData> result = vehicleTiresService.find(filter);

        assertThat(result).hasSize(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsBrandName() {

        VehicleTiresData data = new VehicleTiresData(3L, null, "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", false, 1L, "m");

        vehicleTiresService.create(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsModelName() {

        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", null, 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", false, 1L, "m");

        vehicleTiresService.create(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsProductionYear() {

        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", null, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", false, 1L, "m");

        vehicleTiresService.create(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsPurchaseDate() {

        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, null, 255, 65, 16, "r","V", 98, "no", false, 1L, "m");

        vehicleTiresService.create(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsWidth() {

        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), null, 65, 16, "r","V", 98, "no", false, 1L, "m");

        vehicleTiresService.create(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsProfile() {

        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, null, 16, "r","V", 98, "no", false, 1L, "m");

        vehicleTiresService.create(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsDiameter() {

        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, null, "r","V", 98, "no", false, 1L, "m");

        vehicleTiresService.create(data);
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsType() {

        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, null,"V", 98, "no", false, 1L, "m");

        vehicleTiresService.create(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsSpeedIndex() {

        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r",null, 98, "no", false, 1L, "m");

        vehicleTiresService.create(data);
    }


    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsCapacityIndex() {

        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", null, "no", false, 1L, "m");

        vehicleTiresService.create(data);
    }


    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsReinforced() {

        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, null, false, 1L, "m");

        vehicleTiresService.create(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsRunOnFlat() {

        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", null, 1L, "m");

        vehicleTiresService.create(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsSeasonId() {

        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", false, null, "m");

        vehicleTiresService.create(data);
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNullAsStatus() {

        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", false, 1L, null);

        vehicleTiresService.create(data);
    }


}
