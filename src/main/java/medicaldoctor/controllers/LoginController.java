package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private Label i_label;
    
    @FXML
    void buttonSubmitClick(ActionEvent event) throws Exception {
        LoginResult result = LoginService.checkLogin(
                textUsername.getText(),
                textPassword.getText());
        
        if (result == LoginResult.SUCCESS) {
            if(AppSession.getActiveUser().hasAdditionalPassword()){
                cm.load(LookUp.LOGIN_SECOND_SCREEN);
                cm.showScreen(LookUp.LOGIN_SECOND_SCREEN);
            }
            else{
                if(AppSession.getActiveUser().getType().equals(UserType.DOCTOR)){
                    cm.load(LookUp.DOCTOR_DASHBOARD);
                    cm.showScreen(LookUp.DOCTOR_DASHBOARD);
                }
                else if(AppSession.getActiveUser().getType().equals(UserType.NURSE)){
                    cm.load(LookUp.NURSE_DASHBOARD);
                    cm.showScreen(LookUp.NURSE_DASHBOARD);
                }
                else if(AppSession.getActiveUser().getType().equals(UserType.HEMATOLOGIC_LAB_WORKER)){
                    cm.load(LookUp.LAB_WORKER);
                    cm.showScreen(LookUp.LAB_WORKER);
                }
                else if(AppSession.getActiveUser().getType().equals(UserType.RADIOLOGIC_LAB_WORKER)){
                    cm.load(LookUp.LAB_WORKER);
                    cm.showScreen(LookUp.LAB_WORKER);
                }
                else if(AppSession.getActiveUser().getType().equals(UserType.STAFF)){  
                    cm.load(LookUp.STAFF_DASHBOARD);
                    cm.showScreen(LookUp.STAFF_DASHBOARD);
                }
            }
        }
        
        if (result == LoginResult.WRONG_USERNAME) {
            i_label.setText("Wrong Username!");
            i_label.setVisible(true);
        }
        else if(result == LoginResult.WRONG_PASSWORD){
            i_label.setText("Wrong Password!");
            i_label.setVisible(true); 
        }
        else{
            i_label.setText("Wrong Password and Username!");
            i_label.setVisible(true);  
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
