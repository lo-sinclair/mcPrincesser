package xyz.losi.mcprincesser.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import xyz.losi.mcprincesser.model.dto.GameServer;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "mcserver")
@PropertySource(value = "file:conf/mcserver.yml", factory =  YamlPropertySourceFactory.class)

public class McServerYmlProperties {

    private List<GameServer> servers;

    public List<GameServer> getServers() {
        return servers;
    }

    public void setServers(List<GameServer> servers) {
        this.servers = servers;
    }


}


