package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

import java.time.LocalDateTime;

public class ScaffTraceData {
    private String what;
    private String value;
    private String who;
    private LocalDateTime localDateTime;
    private String ipAddress;
    private String browser;
    private String operatingSystem;


    public ScaffTraceData(String what, String value, String who, String ipAddress, String browser, String operatingSystem) {
        this.what = what;
        this.value = value;
        this.who = who;
        this.browser = browser;
        this.operatingSystem = operatingSystem;
        this.localDateTime = LocalDateTime.now();
        this.ipAddress = ipAddress;
    }

    public String getWhat() {
        return what;
    }

    public String getValue() {
        return value;
    }

    public String getWho() {
        return who;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getBrowser() {
        return browser;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    @Override
    public String toString() {
        return "TraceData{" +
                "what='" + what + '\'' +
                ", value='" + value + '\'' +
                ", who='" + who + '\'' +
                ", localDateTime=" + localDateTime +
                ", ipAddress='" + ipAddress + '\'' +
                ", browser='" + browser + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }
}


