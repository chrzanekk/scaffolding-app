package pl.com.chrzanowski.scaffolding.logic;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.chrzanowski.scaffolding.Application;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypesFilter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class ServiceActionTypeServiceIT {

    @Autowired
    private ServiceActionTypeService serviceActionTypeService;

//    @Autowired
//    private ServiceActionTypeFixture serviceActionTypeFixture;


    @Test
    public void checkIsAnyDataExists() {
//        serviceActionTypeFixture.createActionTypes();

        ServiceActionTypesFilter filter = new ServiceActionTypesFilter();

        Integer size = serviceActionTypeService.find(filter).size();

        assertThat(size).isNotZero();
    }

    @Test
    public void checkIfTheGivenNameExistsWithPositiveResult() {
//        serviceActionTypeFixture.createActionTypes();

        ServiceActionTypesFilter filter = new ServiceActionTypesFilter("inne");

        List<ServiceActionTypeData> result = serviceActionTypeService.find(filter);

        assertThat(result).hasSize(1);
    }

    @Test
    public void checkIfTheGivenNameExistsWithNegativeResult() {

        ServiceActionTypesFilter filter = new ServiceActionTypesFilter("naprawa silnika");

        List<ServiceActionTypeData> result = serviceActionTypeService.find(filter);

        assertThat(result).hasSize(0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNameIsEmptyString() {

        ServiceActionTypeData newData = new ServiceActionTypeData("");

        serviceActionTypeService.add(newData);

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNameIsNull() {

        ServiceActionTypeData newData = new ServiceActionTypeData(null);

        serviceActionTypeService.add(newData);

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfDataValidationWorksWhenGivenNameProtectedAgainstRemove() {

        ServiceActionTypeData newData = new ServiceActionTypeData(8L,"inne");

        serviceActionTypeService.remove(newData);

    }
}
