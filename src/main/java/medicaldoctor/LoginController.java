package medicaldoctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import medicaldoctor.backend.data.LoginResult;
import medicaldoctor.backend.LoginService;

public class LoginController {

    @FXML
    private TextField textUsername;

    @FXML
    private PasswordField textPassword;

    @FXML
    private Button buttonSubmit;

    @FXML
    void buttonSubmitClick(ActionEvent event) throws Exception {
        LoginResult result = LoginService.checkLogin(
                textUsername.getText(),
                textPassword.getText());
        if (result == LoginResult.SUCCESS) {
            System.out.println("good!");
        }
        if (result == LoginResult.WRONG_USERNAME) {
            System.out.println("wrong username");
        }
        if (result == LoginResult.WRONG_PASSWORD) {
            System.out.println("wrong password");
        }
    }

}
