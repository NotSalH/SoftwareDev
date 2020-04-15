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

public class ProfileController implements Initializable {
    
    @FXML
    TextField username_tf, first_name_tf, last_name_tf, employee_id_t, email_tf, department_tf, office_tf, employee_type_tf;
    
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
        if(AppSession.getAdminFlag() == 1){
            first_name_tf.setText(AppSession.getAdminUserSelection().getFirstName());
            last_name_tf.setText(AppSession.getAdminUserSelection().getLastName());
            username_tf.setText(AppSession.getAdminUserSelection().getUserName());
            employee_id_t.setText(AppSession.getAdminUserSelection().getId() + "");
            email_tf.setText(AppSession.getAdminUserSelection().getEmail());
            department_tf.setText(AppSession.getAdminUserSelection().getDepartment());
            office_tf.setText(AppSession.getAdminUserSelection().getOfficeNum() + "");
            employee_type_tf.setText(AppSession.getAdminUserSelection().getType().getName());
        }
        else{
            first_name_tf.setText(AppSession.getActiveUser().getFirstName());
            last_name_tf.setText(AppSession.getActiveUser().getLastName());
            username_tf.setText(AppSession.getActiveUser().getUserName());
            employee_id_t.setText(AppSession.getActiveUser().getId() + "");
            email_tf.setText(AppSession.getActiveUser().getEmail());
            department_tf.setText(AppSession.getActiveUser().getDepartment());
            office_tf.setText(AppSession.getActiveUser().getOfficeNum() + "");
            employee_type_tf.setText(AppSession.getActiveUser().getType().getName());
        }
        AppSession.setAdminFlag(0);
    }
    
}
