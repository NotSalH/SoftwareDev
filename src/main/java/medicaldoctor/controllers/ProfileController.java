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
import medicaldoctor.core.Permission;

public class ProfileController implements Initializable {

    @FXML
    TextField username_tf, first_name_tf, last_name_tf, employee_id_t, email_tf, phone_tf, office_tf, employee_type_tf, sex_tf;

    @FXML
    Label no_field;

    @FXML
    Button conformation, new_user_id, employee_id_tf;

    @FXML
    void new_user(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.load(LookUp.REGISTER_NEW_USER);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.REGISTER_NEW_USER);
    }

    @FXML
    void change_password(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonMustHaveThisPermission(conformation, Permission.REGISTER_NEW_USER);
        buttonMustHaveThisPermission(new_user_id, Permission.REGISTER_NEW_USER);
        buttonMustHaveThisPermission(employee_id_tf, Permission.REGISTER_NEW_USER);
        first_name_tf.setText(AppSession.getActiveUser().getFirstName());
        last_name_tf.setText(AppSession.getActiveUser().getLastName());
        username_tf.setText(AppSession.getActiveUser().getUserName());
        employee_id_t.setText(AppSession.getActiveUser().getId() + "");
        phone_tf.setText(AppSession.getActiveUser().getOfficeNum() + "");
        office_tf.setText(AppSession.getActiveUser().getDepartment());
        employee_type_tf.setText(AppSession.getActiveUser().getType().getName());
    }

    private void buttonMustHaveThisPermission(Button button, Permission permission) {
        if (AppSession.getActiveUser().hasPermission(permission) == false) {
            button.setManaged(false);
        }
    }

}
