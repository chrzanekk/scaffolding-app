package pl.com.chrzanowski.scaffolding.domain.courseplatform;

import pl.com.chrzanowski.scaffolding.logic.courseplatform.StatisticType;

import java.math.BigDecimal;

public class StatisticData {
    private StatisticType type;
    private BigDecimal value;

    public StatisticData(StatisticType type, BigDecimal value) {
        this.type = type;
        this.value = value;
    }

    public StatisticType getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }
}


