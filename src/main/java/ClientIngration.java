import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class ClientIngration {

    Logger log = LoggerFactory.getLogger(ClientIngration.class);

    @Inject
    RestClientIntegration restClientIntegration;


    @CacheResult(cacheName = "test-cache")
    public List<String> get(@CacheKey String cache) {
        log.info("Hello, cache:{}", cache);
        final var objects = new ArrayList<String>();
        int page = 0;
        while (page < 6) {
            final var hello = restClientIntegration.hello();
            objects.add(hello.get("key"));
            page++;
        }
        return objects;
    }
}
