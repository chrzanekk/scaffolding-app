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
@ActiveProfiles("h2")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ServiceActionTypeIT {

    @Autowired
    private ServiceActionTypeService serviceActionTypeService;

    @Autowired
    private ServiceActionTypeFixture serviceActionTypeFixture;

    @Ignore
    @Test
    public void checkIsAnyDataExists() {
        serviceActionTypeFixture.createActionTypes();

        ServiceActionTypesFilter filter = new ServiceActionTypesFilter();

        Integer size = serviceActionTypeService.find(filter).size();

        assertThat(size).isNotZero();
    }

    @Test
    public void checkIfTheGivenNameExistsWithPositiveResult() {
        serviceActionTypeFixture.createActionTypes();

        ServiceActionTypesFilter filter = new ServiceActionTypesFilter("wulkanizacja");

        List<ServiceActionTypeData> result = serviceActionTypeService.find(filter);

        assertThat(result).hasSize(1);
    }
}
