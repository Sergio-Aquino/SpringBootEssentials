package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.service.AnimeService;
import academy.devdojo.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController //Com isso, os retornos dos métodos são apenas strings.
//@RequestMapping("anime")
@RequestMapping(value = "/animes")
@Log4j2
//@AllArgsConstructor //Cria um construtor com todos os atributos existentes na classe como parâmetros
@RequiredArgsConstructor //Constrói um construtor que recebe todos os atributos finais como parâmetro.
public class AnimeController {
    //@Autowired //Usa-se para injeção de dependência.
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    /*public AnimeController(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }*/

    //@GetMapping(path = "list")
    @GetMapping
    public ResponseEntity<List<Anime>> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity(animeService.findById(id), HttpStatus.OK);
    }

    /*@GetMapping("/list2")
    public List<Anime> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return List.of(new Anime("Dragon Ball GT"), new Anime("Naruto"), new Anime("Boruto"));
    }*/
}
//Adiocando o Hot Swap, não é necessário reiniciar a aplicação sempre que um método é adicionado ou alterado