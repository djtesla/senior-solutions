package euro;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class GameRepositoryTest {

    GameRepository gameRepository = new GameRepository();


    @Test
    void testLoadGamesFromFile(){
        gameRepository.loadGamesFromFile("results.csv");
        assertThat(gameRepository.getGames()).hasSize(16)
                .extracting(game -> game.getNameOfWinner()).filteredOn(name-> name.contains("a"))
                .contains("Slovakia");

    }

}