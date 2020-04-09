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
import medicaldoctor.entities.UserType;

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
                if(AppSession.getActiveUser().getType().equals(UserType.DOCTOR)){ 
                    cm.showScreen(Program.DOCTOR_DASHBOARD_NAME);
                }
                else if(AppSession.getActiveUser().getType().equals(UserType.NURSE)){
                }
                else if(AppSession.getActiveUser().getType().equals(UserType.HEMATOLOGIC_LAB_WORKER)){  
                }
                else if(AppSession.getActiveUser().getType().equals(UserType.RADIOLOGIC_LAB_WORKER)){
                }
                else if(AppSession.getActiveUser().getType().equals(UserType.STAFF)){  
                }
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
