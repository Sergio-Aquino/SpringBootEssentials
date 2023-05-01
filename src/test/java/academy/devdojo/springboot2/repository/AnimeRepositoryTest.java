package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static academy.devdojo.springboot2.util.AnimeCreator.createAnimeToBeSaved;


@DataJpaTest
@DisplayName("Tests for repository")
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save creates anime when Successful")
    public void save_PersistedAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates anime when Successful")
    void save_UpdatedAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        animeSaved.setName("Overlord");

        Anime animeUpdated = this.animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
    }

    @Test
    @DisplayName("Delete removes anime when Successful")
    void delete_RemovedAnime_WhenSuccessfull() {
        Anime animeToBeSaved = createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        this.animeRepository.delete(animeSaved);

        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("FindByName returns list of animes when Successful")
    void findByName_ReturnsListOfAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        String name = animeSaved.getName();

        List<Anime> animes = this.animeRepository.findByName(name);

        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(animes).contains(animeSaved);
    }

    @Test
    @DisplayName("FindByName returns an empty list of animes when no anime is empty")
    void findByName_ReturnsEmptyListOfAnime_WhenAnimeISNotFound() {
        List<Anime> animes = this.animeRepository.findByName("xaxa");
        Assertions.assertThat(animes).isEmpty();
    }

    @Test
    @DisplayName("Save throws ConstraintViolationException when name is empty")
    void save_ThrowConstraintViolationException_WhenNameIsEmpty() {
        Anime anime = new Anime();

//       Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
//               .isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.animeRepository.save(anime))
                .withMessageContaining("The anime name cannot be empty");
    }
}