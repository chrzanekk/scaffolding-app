package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffTraceData;

@Service
public class ScaffTraceService {

    private ScaffTraceJdbcRepository scaffTraceJdbcRepository;
    private ScaffUsersService scaffUsersService;

    public ScaffTraceService(ScaffTraceJdbcRepository scaffTraceJdbcRepository, ScaffUsersService scaffUsersService) {
        this.scaffTraceJdbcRepository = scaffTraceJdbcRepository;
        this.scaffUsersService = scaffUsersService;
    }

    public void trace(ScaffTraceData trace) {
        ScaffTraceData preparedTrace = prepareTrace(trace);
        validateTrace(preparedTrace);
        scaffTraceJdbcRepository.create(preparedTrace);
    }

    private ScaffTraceData prepareTrace(ScaffTraceData trace) {
        return new ScaffTraceData(trace.getWhat(), trace.getValue(), getWho(trace.getWho()), trace.getIpAddress(), trace.getBrowser(), trace.getOperatingSystem());
    }

    private String getWho(String who) {
        if (who != null) {
            return who;
        } else {
            ScaffUserData loggedUser = scaffUsersService.getLoggedUser();
            if (loggedUser == null || loggedUser.getId() == null) {
                return null;
            } else {
                return String.valueOf(scaffUsersService.getLoggedUser().getId());
            }
        }
    }

    private void validateTrace(ScaffTraceData data) {

        String ipAddress = data.getIpAddress();
        if (ipAddress != null) {
            if (ipAddress.length() > 50) {
                throw new IllegalArgumentException("IP Address to long, max 50 characters");
            }
        }

    }

}
