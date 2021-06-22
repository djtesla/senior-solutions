package euro;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(SoftAssertionsExtension.class)
class GameServiceTest {

    GameService gameService;


    @BeforeEach
    void init() {

        GameRepository gameRepository = new GameRepository();
        gameRepository.loadGamesFromFile("results.csv");
        gameService = new GameService(gameRepository);
    }

    @Test
    void getCountyWithMostScoredGoals() {
        Game game = gameService.getGameWithBiggestDifference();
        assertThat(game.getNameOfWinner().equals("England"));
    }

    @Test
    void testGetCountyWithMostScoredGoals () {
        assertThat(gameService.getCountyWithMostScoredGoals().equals("England"));

    }

    @Test
    @DisplayName("Softy assert test for scores")
    void testGetNumberOfScoredGoalsOfCountry (SoftAssertions softly) {
        softly.assertThat(gameService.getNumberOfScoredGoalsOfCountry("Belgium")).isEqualTo(3);
        softly.assertThat(gameService.getNumberOfScoredGoalsOfCountry("Croatia")).isEqualTo(1);
        softly.assertThat(gameService.getNumberOfScoredGoalsOfCountry("French")).isEqualTo(1);


    }


}