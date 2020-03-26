package medicaldoctor.util;

import java.util.Random;

public class RandomPasswordGenerator implements PasswordGenerator {

    private static final String CHARS = "BCDFGHJKLMNPQRSTVWXZ"
            + "bcdfghjklmnpqrstvwxz" + "1234567890";

    private static final Random RANDOM = new Random();

    private final int length;

    public RandomPasswordGenerator(int length) {
        this.length = length;
    }

    /**
     * Generate a password of random consonants or digits.
     *
     * @return the generated password
     */
    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(randomChar());
        }
        return sb.toString();
    }

    private char randomChar() {
        return CHARS.charAt(RANDOM.nextInt(CHARS.length()));
    }

}
