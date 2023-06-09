package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.requests.AnimePostRequestBody;
import academy.devdojo.springboot2.requests.AnimePutRequestBody;
import academy.devdojo.springboot2.service.AnimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Com isso, os retornos dos métodos são apenas strings.
//@RequestMapping("anime")
@RequestMapping(value = "/animes")
@Log4j2
//@AllArgsConstructor //Cria um construtor com todos os atributos existentes na classe como parâmetros
@RequiredArgsConstructor //Constrói um construtor que recebe todos os atributos finais como parâmetro.
public class AnimeController {
    //@Autowired //Usa-se para injeção de dependência.
    private final AnimeService animeService;

    /*public AnimeController(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }*/

    //@GetMapping(path = "list")


    @GetMapping
    public ResponseEntity<Page<Anime>> list(Pageable pageable) {
        return new ResponseEntity<>(animeService.listAll(pageable), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Anime>> listAll() {
        return new ResponseEntity<>(animeService.listAllNonPageable(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        return new ResponseEntity(animeService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody) {
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /*@GetMapping("/list2")
    public List<Anime> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return List.of(new Anime("Dragon Ball GT"), new Anime("Naruto"), new Anime("Boruto"));
    }*/
}
//Adiocando o Hot Swap, não é necessário reiniciar a aplicação sempre que um método é adicionado ou alterado