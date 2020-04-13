package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import medicaldoctor.core.AppSession;

public class LogOutController implements Initializable {

    @FXML
    void exitButtonClicked(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void loginPageButtonClicked(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.LOGIN_SCREEN);
        AppSession.setActiveUser(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
