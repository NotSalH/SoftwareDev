package medicaldoctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.User;
import medicaldoctor.util.Encryption;

public class LoginController {
    
    @FXML
    private TextField textUsername;
    
    @FXML
    private PasswordField textPassword;
    
    @FXML
    private Button buttonSubmit;
    
    @FXML
    void buttonSubmitClick(ActionEvent event) throws Exception {
        User user;
        try (DatabaseScope scope = new DatabaseScope()) {
            user = User.byUsername(textUsername.getText());
        }
        if (user == null) {
            System.out.println("wrong username");
        } else {
            Encryption encryption = new Encryption();
            boolean passwordMatches = encryption
                    .checkPassword(textPassword.getText(), user.getPasswordHashAndSalt());
            if (passwordMatches) {
                AppSession.setActiveUser(user);
                System.out.println("good!");
            } else {
                System.out.println("wrong password");
            }
        }
    }
    
}
