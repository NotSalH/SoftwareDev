package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import medicaldoctor.backend.data.NewUserResult;
import medicaldoctor.core.AppSession;

public class UserRegistrationOutputController implements Initializable {

    @FXML
    TextField textFirstName, textLastName, textDepartment, employeeIDNumber,
            textEmployeeType, textUsername, textTempPassword, textOfficeNumber;

    @FXML
    void continueButtonClick(ActionEvent event) throws Exception {
        AppSession.loadDashboard();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NewUserResult result = AppSession.getNewUserResult();
        textFirstName.setText(result.user.getFirstName());
        textLastName.setText(result.user.getLastName());
        textDepartment.setText(result.user.getDepartment());
        employeeIDNumber.setText(result.user.getId() + "");
        textEmployeeType.setText(result.user.getType().getName());
        textUsername.setText(result.user.getUserName());
        textTempPassword.setText(result.temporaryPassword);
        textOfficeNumber.setText(result.user.getOfficeNum() + "");
    }

}
