public class Game {
    public static final int NUM_LENGTH = 3;
    private String question;

    public void setQuestion(String question) {
        this.question = question;
    }

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);

        int strikes = countStrikes(guessNumber);
        int balls = countBalls(guessNumber);

        return new GuessResult(guessSucceeded(strikes, balls), strikes, balls);
    }

    private static void assertIllegalArgument(String guessNumber) {
        if (guessNumber ==null) {
            throw new IllegalArgumentException();
        }
        if (guessNumber.length() != NUM_LENGTH) {
            throw new IllegalArgumentException();
        }

        for (char number: guessNumber.toCharArray()) {
            if (number < '0' || number > '9') {
                throw new IllegalArgumentException();
            }
        }

        if (isDuplicatedNumber(guessNumber)) {
            throw new IllegalArgumentException();
        }
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
