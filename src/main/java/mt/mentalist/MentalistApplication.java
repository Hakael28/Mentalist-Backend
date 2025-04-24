package mt.mentalist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "mt.mentalist")
public class MentalistApplication {
    public static void main(String[] args) {
        SpringApplication.run(MentalistApplication.class, args);
    }
}

