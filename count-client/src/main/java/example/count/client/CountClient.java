package example.count.client;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * Client that sends integers to the hello-service using the fire-and-forget interaction model.
 */
public class CountClient {
    private static final Logger LOG = LoggerFactory.getLogger(CountClient.class);

    public static void main(String... args) throws Exception {
        RSocket rSocket = RSocketFactory.connect()
                .transport(TcpClientTransport.create(7000))
                .start()
                .block();

        for (int i = 1; i <= 1_000; i++) {
            LOG.info("Sending: {}", i);
            rSocket.fireAndForget(DefaultPayload.create(BigInteger.valueOf(i).toByteArray()))
                    .subscribe();
        }
    }
}
