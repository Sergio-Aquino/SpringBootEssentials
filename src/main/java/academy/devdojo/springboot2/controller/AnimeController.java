package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Com isso, os retornos dos métodos são apenas strings.
//@RequestMapping("anime")
@RequestMapping(value = "/anime")
public class AnimeController {
    //@GetMapping(path = "list")
    @GetMapping("/list")
    public List<Anime> list() {
        return List.of(new Anime("Dragon Ball Z"), new Anime("Naruto"));
    }
}
