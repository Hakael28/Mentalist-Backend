package mt.mentalist.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = "mt.mentalist")
@EnableJpaRepositories(basePackages = "mt.mentalist.repositorio")
@EntityScan(basePackages = "mt.mentalist.modelo")
public class MentalistApplication {
    public static void main(String[] args) {
        SpringApplication.run(MentalistApplication.class, args);
    }
}