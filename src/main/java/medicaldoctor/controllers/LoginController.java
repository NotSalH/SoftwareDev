package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import medicaldoctor.Program;
import medicaldoctor.backend.data.LoginResult;
import medicaldoctor.backend.LoginService;
import medicaldoctor.core.AppSession;

public class LoginController implements Initializable, ParentController{
    ControllerManager cm;
    
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
            if(AppSession.getActiveUser().hasAdditionalPassword()){
                cm.showScreen(Program.LOGIN_SECOND_SCREEN_NAME);
            }
            else{
                cm.showScreen(Program.LOGIN_SECOND_SCREEN_NAME);
            }
        }
        if (result == LoginResult.WRONG_USERNAME || result == LoginResult.WRONG_PASSWORD) {
           //WRONG USERNAME or password
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  

    @Override
    public void setScreenParent(ControllerManager page) {
        cm = page;
    }

    

}
