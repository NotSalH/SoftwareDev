package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import medicaldoctor.core.AppSession;
import medicaldoctor.entities.User;

public class ProfileController implements Initializable {

    @FXML
    TextField username_tf, first_name_tf, last_name_tf, employee_id_t, email_tf, department_tf, office_tf, employee_type_tf;

    @FXML
    Label no_field;

    @FXML
    Button conformation, new_user_id, employee_id_tf, buttonChangePassword;

    @FXML
    void new_user(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.load(LookUp.REGISTER_NEW_USER);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.REGISTER_NEW_USER);
    }

    @FXML
    void buttonChangePasswordClick(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.CHANGE_PASSWORD);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User userToDisplay = null;
        if (AppSession.getViewingUserSelection() == null) {
            userToDisplay = AppSession.getActiveUser();
        } else {
            userToDisplay = AppSession.getViewingUserSelection();
            buttonChangePassword.setManaged(false);
        }
        first_name_tf.setText(userToDisplay.getFirstName());
        last_name_tf.setText(userToDisplay.getLastName());
        username_tf.setText(userToDisplay.getUserName());
        employee_id_t.setText(userToDisplay.getId() + "");
        email_tf.setText(userToDisplay.getEmail());
        department_tf.setText(userToDisplay.getDepartment());
        office_tf.setText(userToDisplay.getOfficeNum() + "");
        employee_type_tf.setText(userToDisplay.getType().getName());

        AppSession.setViewingUserSelection(null);
    }

}
