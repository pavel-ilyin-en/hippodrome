import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class MainTest {

    @Disabled
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void testTimeOut() throws Exception {
        Main.main(null);
    }

}
