import java.util.IllformedLocaleException;

public class Game {
    public static final int NUM_LENGTH = 3;
    private String question;

    public void setQuestion(String question) {
        assertIllegalArgument(question);
        this.question = question;
    }

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);

        int strikes = countStrikes(guessNumber);
        int balls = countBalls(guessNumber);

        return new GuessResult(guessSucceeded(strikes, balls), strikes, balls);
    }

    private  void assertIllegalArgument(String guessNumber) {
        if (isEmptyString(guessNumber)) {
            throw new IllegalArgumentException();
        };

        if (!isGuessNumberLengthFit(guessNumber)) {
            throw new IllegalArgumentException();
        }

        if (isGuessNumberIncludeNotNumber(guessNumber)){
            throw new IllegalArgumentException();
        }

        if (isDuplicatedNumber(guessNumber)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isGuessNumberIncludeNotNumber(String guessNumber) {
        for (char number: guessNumber.toCharArray()) {
            if (number < '0' || number > '9') {
                return true;
            }
        }
        return false;
    }
    private boolean isGuessNumberLengthFit(String guessNumber) {
        return guessNumber.length() == NUM_LENGTH;
    }

    private boolean isEmptyString(String guessNumber) {
        return guessNumber == null;
    }

    private static boolean isDuplicatedNumber(String guessNumber) {
        for (int idx =0; idx < NUM_LENGTH; idx++) {
            for (int checkIdx = idx+1; checkIdx < NUM_LENGTH; checkIdx++) {
                if (guessNumber.charAt(idx) == guessNumber.charAt(checkIdx)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean guessSucceeded(int strikes, int balls) {
        return strikes == NUM_LENGTH && balls == 0;
    }

    private int countStrikes(String guessNumber) {
        int strikes = 0;
        for (int idx = 0; idx < NUM_LENGTH; idx++) {
            if (guessNumber.charAt(idx) == question.charAt(idx)) {
                strikes++;
            }
        }
        return strikes;
    }

    private int countBalls(String guessNumber) {
        int balls = 0;
        for (int idx = 0; idx < NUM_LENGTH; idx++) {
            if ((guessNumber.charAt(idx) != question.charAt(idx))
                && (question.contains(String.valueOf(guessNumber.charAt(idx))))) {
                balls++;
            }
        }
        return balls;
    }
}
