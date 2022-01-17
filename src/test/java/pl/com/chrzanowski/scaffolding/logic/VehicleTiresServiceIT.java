package pl.com.chrzanowski.scaffolding.logic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.chrzanowski.scaffolding.Application;
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

    @Autowired
    private VehicleTiresServiceFixture vehicleTiresServiceFixture;

    @Test
    public void checkIsAnyDataExists() {

        vehicleTiresServiceFixture.createTires();

        VehicleTiresFilter filter = new VehicleTiresFilter();

        Integer size = vehicleTiresService.find(filter).size();

        assertThat(size).isNotZero();
    }

    @Test
    public void checkIfTheGivenTireBrandExistsWithPositiveResult() {
        //given
        VehicleTiresFilter filter = new VehicleTiresFilter("Pirelli", null);
        //when
        List<VehicleTiresData> result = vehicleTiresService.find(filter);
        //then
        assertThat(result).hasSize(1);
    }

//    @Test
//    public void checkIfTheGivenTireModelExistsWithPositiveResult() {
//
//        VehicleTiresFilter filter = new VehicleTiresFilter(null, "Winter XL");
//
//        List<VehicleTiresData> result = vehicleTiresService.find(filter);
//
//        assertThat(result).hasSize(2);
//    }
//
//    @Test
//    public void checkIfTheGivenVehicleIdHasTireWithPositiveResult() {
//
//        VehicleTiresFilter filter = new VehicleTiresFilter(null, 1L);
//
//        List<VehicleTiresData> result = vehicleTiresService.find(filter);
//
//        assertThat(result).hasSize(2);
//    }
//
//    @Test
//    public void checkIfTheGivenVehicleIdHasTireWithNegativeResult() {
//
//        VehicleTiresFilter filter = new VehicleTiresFilter(null, 4L);
//
//        List<VehicleTiresData> result = vehicleTiresService.find(filter);
//
//        assertThat(result).hasSize(0);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenNullAsBrandName() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, null, "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenEmptyStringAsBrandName() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenNullAsModelName() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", null, 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenEmptyStringAsModelName() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenNullAsProductionYear() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", null, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenProductionYearOutOfRange5() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2016, LocalDate.parse("2021-11-20"),255, 65, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenNullAsPurchaseDate() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, null, 255, 65, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenFutureDateAsPurchaseDate() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2022-11-20"), 255, 65, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenValueIsNullAsWidth() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), null, 65, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenValueIsBelowLowerLimitValueAsWidth() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019,
//                LocalDate.parse("2021-11-20"), 110, 65, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenValueIsAboveHighestLimitAsWidth() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019,
//                LocalDate.parse("2021-11-20"), 400, 65, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenValueIsNullAsProfile() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, null, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenValueIsBelowLowerLimitAsProfile() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019,
//                LocalDate.parse("2021-11-20"), 255, 20, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenValueIsAboveHighestLimitAsProfile() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019,
//                LocalDate.parse("2021-11-20"), 255, 90, 16, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenNullAsDiameter() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, null, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenValueIsBelowLowerLimitAsDiameter() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019,
//                LocalDate.parse("2021-11-20"), 255, 65, 9, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenValueIsAboveHighestLimitAsDiameter() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019,
//                LocalDate.parse("2021-11-20"), 255, 65, 26, "r","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenNullAsType() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, null,"V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenEmptyStringAsType() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "","V", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenNullAsSpeedIndex() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r",null, 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenEmptyStringAsSpeedIndex() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","", 98, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenNullAsCapacityIndex() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", null, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenValueIsBelowLowestLimitAsCapacityIndex() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019,
//                LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 74, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenValueIsAboveHighestLimitAsCapacityIndex() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019,
//                LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 111, "no", false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenNullAsReinforced() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, null, false, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenNullAsRunOnFlat() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", null, 1L, "m");
//
//        vehicleTiresService.create(data);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenNullAsSeasonId() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", false, null, "m");
//
//        vehicleTiresService.create(data);
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void checkIfDataValidationWorksWhenGivenNullAsStatus() {
//
//        VehicleTiresData data = new VehicleTiresData(3L, "Dębica", "Summer Sport", 2019, LocalDate.parse("2021-11-20"), 255, 65, 16, "r","V", 98, "no", false, 1L, null);
//
//        vehicleTiresService.create(data);
//    }
//

}
