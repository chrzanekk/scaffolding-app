package pl.com.chrzanowski.scaffolding.logic.courseplatform;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.courseplatform.CustomerData;
import pl.com.chrzanowski.scaffolding.domain.courseplatform.TraceData;

@Service
public class TraceService {

    private TraceJdbcRepository traceJdbcRepository;
    private CourseCustomersService courseCustomersService;

    public TraceService(TraceJdbcRepository traceJdbcRepository, CourseCustomersService courseCustomersService) {
        this.traceJdbcRepository = traceJdbcRepository;
        this.courseCustomersService = courseCustomersService;
    }

    public void trace(TraceData trace) {
        TraceData preparedTrace = prepareTrace(trace);
        validateTrace(preparedTrace);
        traceJdbcRepository.create(preparedTrace);
    }

    private TraceData prepareTrace(TraceData trace) {
        return new TraceData(trace.getWhat(), trace.getValue(), getWho(trace.getWho()), trace.getIpAddress(), trace.getBrowser(), trace.getOperatingSystem());
    }

    private String getWho(String who) {
        if (who != null) {
            return who;
        } else {
            CustomerData loggedUser = courseCustomersService.getLoggedCustomer();
            if (loggedUser == null || loggedUser.getId() == null) {
                return null;
            } else {
                return String.valueOf(courseCustomersService.getLoggedCustomer().getId());
            }
        }
    }

    private void validateTrace(TraceData data) {

        String ipAddress = data.getIpAddress();
        if (ipAddress != null) {
            if (ipAddress.length() > 50) {
                throw new IllegalArgumentException("IP Address to long, max 50 characters");
            }
        }

    }

}
