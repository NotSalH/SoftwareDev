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

public class LoginController implements Initializable, ParentController {

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
            if (AppSession.getActiveUser().hasAdditionalPassword()) {
                textUsername.setText("");
                textPassword.setText("");
                AppSession.CONTROLLER_MANAGER.load(LookUp.LOGIN_SECOND_SCREEN);
                AppSession.CONTROLLER_MANAGER.showScreen(LookUp.LOGIN_SECOND_SCREEN);
            } else {
                textUsername.setText("");
                textPassword.setText("");
                AppSession.CONTROLLER_MANAGER.load(AppSession.getActiveUser().getType().getDashboardName());
                AppSession.CONTROLLER_MANAGER.showScreen(AppSession.getActiveUser().getType().getDashboardName());
            }
        }

        if (result == LoginResult.WRONG_USERNAME) {
            i_label.setText("Wrong Username!");
            i_label.setVisible(true);
        } else if (result == LoginResult.WRONG_PASSWORD) {
            i_label.setText("Wrong Password!");
            i_label.setVisible(true);
        } else {
            i_label.setText("Wrong Password and Username!");
            i_label.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void setScreenParent(ControllerManager page) {
        AppSession.CONTROLLER_MANAGER = page;
    }

}
