package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import medicaldoctor.backend.LoginService;
import medicaldoctor.backend.data.LoginResult;
import medicaldoctor.core.AppSession;
import medicaldoctor.entities.UserType;

/**
 *
 * @author Salmanhussain
 */
public class AdditionalLoginController implements Initializable, ParentController{
    ControllerManager cm;
    
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField password;
    @FXML
    private Label i_label;
    
    @FXML
    void loginButtonClicked(ActionEvent event) throws Exception{
        LoginResult result = LoginService.checkAdditionalPassword(password.getText());
        if(result == LoginResult.SUCCESS){
            if(AppSession.getActiveUser().getType().equals(UserType.ADMIN)){
                cm.load(LookUp.ADMIN_DASHBOARD_NAME, LookUp.ADMIN_DASHBOARD);
                cm.showScreen(LookUp.ADMIN_DASHBOARD_NAME);
            }
            else if(AppSession.getActiveUser().getType().equals(UserType.EXECUTIVE)){
                cm.load(LookUp.ADMIN_DASHBOARD_NAME, LookUp.ADMIN_DASHBOARD);
                cm.showScreen(LookUp.ADMIN_DASHBOARD_NAME);
            }
            else{
                //do nothing. IDK if this case will ever be reached. This is just in case. 
            }
        }
        else{
            i_label.setVisible(true);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
    }

    @Override
    public void setScreenParent(ControllerManager page) {
        cm = page;
    }
    
}