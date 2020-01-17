package example.count.service;

import io.rsocket.AbstractRSocket;
import io.rsocket.ConnectionSetupPayload;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.SocketAcceptor;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.server.TcpServerTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

/**
 * Service that receives integers via the fire-and-forget interaction model.
 */
public class CountService {
    private static final Logger LOG = LoggerFactory.getLogger(CountService.class);

    public static void main(String... args) throws Exception {
        RSocketFactory.receive()
                .frameDecoder(PayloadDecoder.DEFAULT)
                .acceptor(new SocketAcceptor() {
                    @Override
                    public Mono<RSocket> accept(ConnectionSetupPayload setup, RSocket sendingSocket) {
                        return Mono.just(new AbstractRSocket() {
                            @Override
                            public Mono<Void> fireAndForget(Payload payload) {
                                byte[] bytes = new byte[payload.data().readableBytes()];
                                payload.data().readBytes(bytes);

                                LOG.info("Received: {}", new BigInteger(bytes).intValue());

                                return Mono.empty();
                            }
                        });
                    }
                })
                .transport(TcpServerTransport.create(7000))
                .start()
                .block();

        LOG.info("RSocket server started on port: 7000");

        Thread.currentThread().join();
    }
}
