package academy.devdojo.springboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAutoConfiguration
//@ComponentScan
//@Configuration //Coloca a sua classe na filter chain.
@SpringBootApplication //Atribui todas as outras três notações acima de forma automática.
public class ApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }
}
