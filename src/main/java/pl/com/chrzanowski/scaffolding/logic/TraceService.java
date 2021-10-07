package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.TraceData;

@Service
public class TraceService {

    private TraceJdbcRepository traceJdbcRepository;
    private UserService userService;

    public TraceService(TraceJdbcRepository traceJdbcRepository, UserService userService) {
        this.traceJdbcRepository = traceJdbcRepository;
        this.userService = userService;
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
            UserData loggedUser = userService.getLoggedUser();
            if (loggedUser == null || loggedUser.getId() == null) {
                return null;
            } else {
                return String.valueOf(userService.getLoggedUser().getId());
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
