package pl.com.chrzanowski.scaffolding.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.com.chrzanowski.scaffolding.domain.tires.VehicleTiresData;
import pl.com.chrzanowski.scaffolding.logic.dictionaries.DictionariesService;
import pl.com.chrzanowski.scaffolding.logic.tires.VehicleTiresService;

import javax.persistence.EntityManager;
import java.time.LocalDate;

@Service
public class VehicleTiresServiceFixture {

    @Autowired
    private VehicleTiresServiceDB vehicleTiresServiceDB;

    @Autowired
    private VehicleTiresService vehicleTiresService;

    @Autowired
    private DictionariesService dictionariesService;

    @Autowired
    private EntityManager em;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createTires() {

        vehicleTiresServiceDB.createTables();

        VehicleTiresData setOne = new VehicleTiresData(3L,
                "Pirelli",
                "Summer Sport",
                2019,
                LocalDate.parse("2021-11-20"),
                255,
                65,
                16,
                "r",
                "V",
                98,
                "no",
                false,
                1L,
                "m");

        vehicleTiresService.create(setOne);


        em.flush();
    }
}
