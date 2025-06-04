public class Game {
    public String question;

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);

        int strikes = countStrikes(guessNumber);
        int balls = countBalls(guessNumber);

        return new GuessResult(guessSucceeded(strikes, balls), strikes, balls);
    }

    private static boolean guessSucceeded(int strikes, int balls) {
        return strikes == 3 && balls == 0;
    }

    private int countStrikes(String guessNumber) {
        int strikes = 0;
        for (int idx = 0; idx < 3; idx++) {
            if (guessNumber.charAt(idx) == question.charAt(idx)) {
                strikes++;
            }
        }
        return strikes;
    }

    private int countBalls(String guessNumber) {
        int balls = 0;
        for (int idx = 0; idx < 3; idx++) {
            if ((guessNumber.charAt(idx) != question.charAt(idx))
                && (question.indexOf(guessNumber.charAt(idx)) != -1)) {
                System.out.println(guessNumber.charAt(idx));
                balls++;
            }
        }
        return balls;
    }

    private static void assertIllegalArgument(String guessNumber) {
        if (guessNumber ==null) {
            throw new IllegalArgumentException();
        }
        if (guessNumber.length() != 3) {
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
        return guessNumber.charAt(0) == guessNumber.charAt(1)
                || guessNumber.charAt(0) == guessNumber.charAt(2)
                || guessNumber.charAt(1) == guessNumber.charAt(2);
    }
}
