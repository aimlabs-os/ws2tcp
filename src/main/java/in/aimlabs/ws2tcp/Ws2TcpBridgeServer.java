package in.aimlabs.ws2tcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

/**
 * @author Suresh Reddy Guntaka {@literal <sureshreddy.guntaka@gmail.com>}
 */
@SpringBootApplication
public class Ws2TcpBridgeServer {

    public static void main (String args[]) {
        SpringApplication.run(Ws2TcpBridgeServer.class, args);
    }

    @Bean
    WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}
