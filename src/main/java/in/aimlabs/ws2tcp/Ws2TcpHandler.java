package in.aimlabs.ws2tcp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.tcp.TcpClient;


/**
 * @author Suresh Reddy Guntaka {@literal <sureshreddy.guntaka@gmail.com>}
 */
@Data
@Slf4j
@Component
public class Ws2TcpHandler implements WebSocketHandler {

    @Autowired
    private Ws2TcpProperties ws2TcpProperties;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<String> outFlux = Flux.create(sink -> new Thread(() ->
                TcpClient.newConnection()
                        .host(ws2TcpProperties.getTcpHost())
                        .port(ws2TcpProperties.getTcpPort())
                        .handle((in, out) -> {
                            in.receive()
                                    .asString()
                                    .subscribe(sink::next);
                            return session.receive()
                                    .map(WebSocketMessage::getPayloadAsText)
                                    .flatMap(data -> out.sendString(Mono.just(data)));

                        })
                        .connect().block())
                .start());
        return session.send(outFlux.map(session::textMessage));
    }
}
