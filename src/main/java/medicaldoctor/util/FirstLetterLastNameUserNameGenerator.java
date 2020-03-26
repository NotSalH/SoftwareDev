package medicaldoctor.util;

public class FirstLetterLastNameUserNameGenerator implements UserNameGenerator {

    /**
     * Generate a user name from the first letter of the first name appended
     * with the entire last name. Ex: "First","Last" -> "flast"
     *
     * @param firstName
     * @param lastName
     * @return
     */
    public String generate(String firstName, String lastName) {
        firstName = cleanName(firstName);
        lastName = cleanName(lastName);
        return firstName.charAt(0) + lastName;
    }

    /**
     * Generate a user name from the first letter of the first name appended
     * with the entire last name, and also an id. Ex: "First","Last",1 ->
     * "flast1"
     *
     * @param firstName
     * @param lastName
     * @param id
     * @return
     */
    @Override
    public String generate(String firstName, String lastName, int id) {
        return generate(firstName, lastName) + id;
    }

    private String cleanName(String name) {
        return name.toLowerCase().replaceAll("/[^a-z]/", "");
    }

}
