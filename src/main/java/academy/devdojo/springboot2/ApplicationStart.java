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

//No pacote controller, há apenas uma classe simples com todos os Endpoints.
//No pacote domínio, há uma classe que representa o que há no banco de dados.
//No pacote repository, há uma classe cujos métodos interagem com o banco de dados.
//No pacote service, há uma classe com a lógica de negócio.