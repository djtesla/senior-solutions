package euro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    List<Game> games;

    @BeforeEach
    void init() {
        games = List.of(new Game("Turkey", "Italy", 0, 3),
                new Game("Wales", "Switzerland", 1, 0),
                new Game("Denmark", "Finland", 2, 2)
        );
    }

    @Test
    void testGetNameOfWinner() {
        assertAll(
                () -> assertEquals("Italy", games.get(0).getNameOfWinner()),
                () -> assertEquals("Wales", games.get(1).getNameOfWinner()),
                () -> assertEquals("draw", games.get(2).getNameOfWinner())
        );

    }

    @Test
    @RepeatedTest(value = 3, name ="Test for winner and difference {currentRepetition}/{totalRepetitions}")
    void testGetNameOfWinnerAnDifferenceWithRepetition(RepetitionInfo repetitionInfo) {
        Object[][] values = {{"Italy", 3}, {"Wales", 1}, {"draw", 0}};
        assertEquals(values[repetitionInfo.getCurrentRepetition()-1][0],games.get(repetitionInfo.getCurrentRepetition()-1).getNameOfWinner());
    }

}