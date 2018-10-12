package in.aimlabs.ws2tcp;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Suresh Reddy Guntaka {@literal <sureshreddy.guntaka@gmail.com>}
 */
@Data
@Configuration
public class Ws2TcpConfig {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private Ws2TcpProperties ws2TcpProperties;

    @Bean
    public HandlerMapping registerHandlerMapping () {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put(ws2TcpProperties.getWsEndpoint(), context.getBean(Ws2TcpHandler.class));
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(map);
        mapping.setCorsConfigurations(
                Collections.singletonMap("*", new CorsConfiguration().applyPermitDefaultValues()));
        mapping.setOrder(10);
        return mapping;
    }
}
