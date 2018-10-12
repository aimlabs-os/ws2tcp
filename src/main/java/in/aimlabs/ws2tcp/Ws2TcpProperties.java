package in.aimlabs.ws2tcp;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Suresh Reddy Guntaka {@literal <sureshreddy.guntaka@gmail.com>}
 */
@Data
@Component
@ConfigurationProperties(prefix = "ws2tcp")
public class Ws2TcpProperties {

    private String tcpHost;

    private int tcpPort;

    private String wsEndpoint = "/ws2tcp";

}
