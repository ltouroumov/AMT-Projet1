package ch.ltouroumov.heig.amt.project1.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

/**
 * Configures the REST API.
 * Prefixes it under '/api'
 */
@ApplicationPath("/api")
public class RestApiConfig extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.disableMoxyJson", true);
        return properties;
    }
}



