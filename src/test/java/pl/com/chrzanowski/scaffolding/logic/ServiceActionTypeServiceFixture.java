package pl.com.chrzanowski.scaffolding.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypeData;


import javax.persistence.EntityManager;


@Service
public class ServiceActionTypeServiceFixture {

    @Autowired
    private ServiceActionTypeService serviceActionTypeService;

    @Autowired
    private EntityManager em;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createActionTypes() {

        ServiceActionTypeData data = new ServiceActionTypeData("serwis olejowy");
        serviceActionTypeService.add(data);

        ServiceActionTypeData data1 = new ServiceActionTypeData("wulkanizacja");
        serviceActionTypeService.add(data1);

        ServiceActionTypeData data2 = new ServiceActionTypeData("naprawy");
        serviceActionTypeService.add(data2);

        em.flush();

    }
}
