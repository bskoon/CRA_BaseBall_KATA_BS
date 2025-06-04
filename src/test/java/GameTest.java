import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void createGame() {
        assertNotNull(game);
    }

    @Test
    void throwIllegalArgumentExceptionInvalidInput() {
        assertIllegalArgument(null);
        assertIllegalArgument("12");
        assertIllegalArgument("1234");
        assertIllegalArgument("12s");
        assertIllegalArgument("121");
    }

    private void assertIllegalArgument(String guessNumber) {
        try {
            game.guess(guessNumber);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    void returnSolvedResult() {
        generateQuestion("123");
        // 3-0
        assertMatchedNumber(game.guess("123"), true, 3, 0);
        // 0-0
        assertMatchedNumber(game.guess("456"), false, 0, 0);
        // 2-0
        assertMatchedNumber(game.guess("124"), false, 2, 0);
        // 1-2
        assertMatchedNumber(game.guess("132"), false, 1, 2);
    }

    private void generateQuestion(String questionNumber) {
        game.question = questionNumber;
    }

    private static void assertMatchedNumber(GuessResult result, boolean solved, int strikes, int balls) {
        assertNotNull(result);
        assertEquals(solved, result.isSolved());
        assertEquals(strikes, result.getStrikes());
        assertEquals(balls, result.getBalls());
    }
}