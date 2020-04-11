package medicaldoctor.util;

public class EmailNameGenerator implements EmailGenerator{
    
    @Override
    public String generateEmail(String first_name, String last_name) {
        return first_name.substring(0, 1) + last_name.substring(1, last_name.length()) + "@MCARE.com";
    }
    
}
