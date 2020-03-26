package medicaldoctor.utils.tests;

import medicaldoctor.util.PasswordGenerator;

public class HardcodedPasswordGenerator implements PasswordGenerator {

    public final String password;

    public HardcodedPasswordGenerator(String password) {
        this.password = password;
    }

    @Override
    public String generate() {
        return password;
    }

}
