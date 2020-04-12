package medicaldoctor.util;

public class EmailNameGenerator implements EmailGenerator{
    
    @Override
    public String generateEmail(String username) {
        return username + "@MCARE.com";
    }
    
}
