package academy.devdojo.springboot2.controller;

import main.java.academy.devdojo.springboot2.domain.Anime;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Com isso, os retornos dos métodos são apenas strings.
public class AnimeController {
    public List<Anime> list() {
        return List.of(new Anime("Dragon Ball Z"));
    }
}
