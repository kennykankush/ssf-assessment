package vttp.batch5.ssf.noticeboard.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VariableConfig {

    @Value("${chuk.server.url}")
    private String server_url;

    public String getServer_url() {
        return server_url;
    }
}
