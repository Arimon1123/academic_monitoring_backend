package com.example.backend_academic_monitoring.Utilities;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class EnvUtil {
    @Autowired
    Environment environment;

    private String host;
    private String port;

    public String getPort() {
        if (port == null) {
            port = environment.getProperty("server.port");
        }
        return port;
    }
    public String getHost() {
        if (host == null) {
            host = environment.getProperty("server.host");
        }
        return host;
    }

    public String getBaseUrl() {
        return "http://" + getHost() + ":" + getPort();
    }
}
