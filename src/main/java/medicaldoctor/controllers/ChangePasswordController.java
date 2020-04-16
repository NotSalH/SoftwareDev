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
import medicaldoctor.backend.LoginService;
import medicaldoctor.backend.UserService;
import medicaldoctor.backend.data.ChangePasswordResult;
import medicaldoctor.backend.data.LoginResult;
import medicaldoctor.core.AppSession;

public class ChangePasswordController implements Initializable {

    @FXML
    private TextField textOldPassword;

    @FXML
    private PasswordField textNewPassword;

    @FXML
    private PasswordField textConfirmNewPassword;

    @FXML
    private Label labelResult;

    @FXML
    void buttonSubmitClick(ActionEvent event) throws Exception {
        LoginResult loginResult = LoginService.checkLogin(
                AppSession.getActiveUser().getUserName(),
                textOldPassword.getText(),
                false);
        if (loginResult == LoginResult.SUCCESS) {
            ChangePasswordResult result = UserService.changePassword(
                    textNewPassword.getText(),
                    textConfirmNewPassword.getText());
            if (result == ChangePasswordResult.SUCCESS) {
                labelResult.setText("Success! Your password has been updated.");
            } else if (result == ChangePasswordResult.PASSWORDS_NOT_MATCHING) {
                labelResult.setText("Your new passwords do not match.");
            } else if (result == ChangePasswordResult.PASSWORD_NOT_CHANGED) {
                labelResult.setText("Your new password is the same as your old password. Please enter a different password.");
            }
        } else {
            labelResult.setText("Your old password is not correct.");
        }
    }

    @FXML
    void buttonReturnClick(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.PROFILE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelResult.setText("");
    }

}
