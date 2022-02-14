package basilisk.jobsManagingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
public class JobsManagingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobsManagingServiceApplication.class, args);
    }

}
