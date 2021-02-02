package basilisk.hooksCheckingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class HooksCheckingServiceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HooksCheckingServiceApplication.class, args);

    }

}
