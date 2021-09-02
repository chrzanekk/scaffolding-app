package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.courseplatform.*;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffStatisticJdbcRepository;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffStatisticType;

import java.util.List;

@Service
public class ScaffStatisticService {
    private ScaffStatisticJdbcRepository statisticJdbcRepository;

    public ScaffStatisticService(ScaffStatisticJdbcRepository statisticJdbcRepository) {
        this.statisticJdbcRepository = statisticJdbcRepository;
    }

//    public StatisticData refresh(ScaffStatisticType type, String param, Integer number) {
//        return statisticJdbcRepository.refresh(type, param, number);
//    }

    public List<StatisticSellingData> findTopSellingProductsForMoney() {
        return statisticJdbcRepository.findTopSellingProductsForMoney();
    }

    public List<StatisticNewCustomerData> findNewCustomers() {
        return statisticJdbcRepository.findNewCustomers();
    }

    public List<StatisticItemCountData> findVisitedLandings() {
        return statisticJdbcRepository.findVisitedLandings();
    }

    public List<StatisticCourseCompletionData> findCourseCompletion(StatisticCompletionFilter filter) {
        return statisticJdbcRepository.findCourseCompletion(filter);
    }
}
