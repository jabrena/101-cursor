package info.jab.ms;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusMain
public class MainApplication implements QuarkusApplication {

    private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

    @Override
    public int run(String... args) throws Exception {
        log.info("Greek Gods Service started");
        io.quarkus.runtime.Quarkus.waitForExit();
        return 0;
    }
}
