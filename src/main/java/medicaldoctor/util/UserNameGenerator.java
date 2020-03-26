package medicaldoctor.util;

public interface UserNameGenerator {

    String generate(String firstName, String lastName);

    String generate(String firstName, String lastName, int id);

}
