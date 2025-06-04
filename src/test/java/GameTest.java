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
        // 1-1
        assertMatchedNumber(game.guess("192"), false, 1, 1);
        // 1-0
        assertMatchedNumber(game.guess("176"), false, 1, 0);
    }

    @Test
    void returnSolvedResult2() {
        generateQuestion("789");
        // 3-0
        assertMatchedNumber(game.guess("789"), true, 3, 0);
        // 0-0
        assertMatchedNumber(game.guess("123"), false, 0, 0);
        // 2-0
        assertMatchedNumber(game.guess("689"), false, 2, 0);
        // 1-2
        assertMatchedNumber(game.guess("987"), false, 1, 2);
        // 1-1
        assertMatchedNumber(game.guess("179"), false, 1, 1);
        // 1-0
        assertMatchedNumber(game.guess("180"), false, 1, 0);
    }

    private void generateQuestion(String questionNumber) {
        game.setQuestion(questionNumber);
    }

    private static void assertMatchedNumber(GuessResult result, boolean solved, int strikes, int balls) {
        assertNotNull(result);
        assertEquals(solved, result.isSolved());
        assertEquals(strikes, result.getStrikes());
        assertEquals(balls, result.getBalls());
    }
}