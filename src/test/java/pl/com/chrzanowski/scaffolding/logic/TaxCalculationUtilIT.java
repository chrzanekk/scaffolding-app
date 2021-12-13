package pl.com.chrzanowski.scaffolding.logic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.chrzanowski.scaffolding.Application;


import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class TaxCalculationUtilIT {

    @Test
    public void calculateTaxValue() {

        BigDecimal result = TaxCalculationUtil.calculateTaxValue(new BigDecimal("100"), new BigDecimal("0.23"));

        assertEquals(new BigDecimal("23.00"), result);

    }

    @Test
    public void calculateGrossValue() {

        BigDecimal result = TaxCalculationUtil.calculateGrossValue(new BigDecimal("100"), new BigDecimal("0.23"));

        assertEquals(new BigDecimal("123.00"), result);

    }


}
