package pl.com.chrzanowski.scaffolding.logic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.chrzanowski.scaffolding.Application;
import pl.com.chrzanowski.scaffolding.domain.serviceactiontypes.ServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.serviceactiontypes.ServiceActionTypesFilter;
import pl.com.chrzanowski.scaffolding.logic.serviceactiontypes.ServiceActionTypeService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class ServiceActionTypeServiceIT {

    @Autowired
    private ServiceActionTypeService serviceActionTypeService;

    @Autowired
    private ServiceActionTypeServiceDB serviceActionTypeServiceDB;

    @Autowired
    private ServiceActionTypeServiceFixture serviceActionTypeFixture;


    @Test
    public void checkIsAnyDataExists() {
        //given
        serviceActionTypeServiceDB.createTable();
        serviceActionTypeFixture.createActionTypes();
        ServiceActionTypesFilter filter = new ServiceActionTypesFilter();

        //when
        Integer size = serviceActionTypeService.find(filter).size();

        //then
        assertThat(size).isNotZero();
    }

    @Test
    public void checkIfTheGivenNameExistsWithPositiveResult() {
//        robienie tabel
        serviceActionTypeServiceDB.createTable();
//        dane
        serviceActionTypeFixture.createActionTypes();
//        test
        ServiceActionTypesFilter filter = new ServiceActionTypesFilter("naprawy");

        List<ServiceActionTypeData> result = serviceActionTypeService.find(filter);

        assertThat(result).hasSize(1);
    }

    @Test
    public void checkIfTheGivenNameExistsWithNegativeResult() {
//        robienie tabel
        serviceActionTypeServiceDB.createTable();
//        dane
        serviceActionTypeFixture.createActionTypes();
//        test
        ServiceActionTypesFilter filter = new ServiceActionTypesFilter("naprawa silnika");

        List<ServiceActionTypeData> result = serviceActionTypeService.find(filter);

        assertThat(result).hasSize(0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNameIsEmptyString() {
//        robienie tabel
        serviceActionTypeServiceDB.createTable();
//        dane
        serviceActionTypeFixture.createActionTypes();
//        test
        ServiceActionTypeData newData = new ServiceActionTypeData("");

        serviceActionTypeService.add(newData);

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNameIsNull() {
//        robienie tabel
        serviceActionTypeServiceDB.createTable();
//        dane
        serviceActionTypeFixture.createActionTypes();
//        test
        ServiceActionTypeData newData = new ServiceActionTypeData(null);

        serviceActionTypeService.add(newData);

    }

}
