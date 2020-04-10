package medicaldoctor.controllers.navbar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import medicaldoctor.controllers.ControllerManager;
import medicaldoctor.controllers.ParentController;
import medicaldoctor.core.AppSession;
import static medicaldoctor.core.AppSession.ENCRYPTION;
import medicaldoctor.entities.User;
import medicaldoctor.entities.UserInformationGenerator;

public class NewUserController implements Initializable, ParentController{

    @FXML
    TextField username_tf, first_name_tf, last_name_tf, employee_id_t, email_tf, phone_tf, office_tf;
    
    @FXML
    ChoiceBox sex_cb, employee_type_cb;
    
    @FXML
    Label no_field;
    
    @FXML
    Button conformation;
    
    UserInformationGenerator uig;
    
    @FXML
    void new_user(ActionEvent event){
        if(!(last_name_tf.getText().isEmpty() && first_name_tf.getText().isEmpty() && phone_tf.getText().isEmpty() && office_tf.getText().isEmpty())){
            email_tf.setText(uig.getEmail(first_name_tf.getText(), last_name_tf.getText()));
            username_tf.setText(uig.getEmail(first_name_tf.getText(), last_name_tf.getText()));
            conformation.setVisible(true);
        }
        else{
            no_field.setVisible(true);
        }
    }
    
    @FXML
    void confirm_user(ActionEvent event){
        User user;
        user = new User();
        user.setFirstName(first_name_tf.getText());
        user.setLastName(last_name_tf.getText());
        user.setUserName(username_tf.getText());
        user.setPasswordHashAndSalt(ENCRYPTION.hashPassword(uig.genPass()));
        user.setType(uig.getType((String)employee_type_cb.getValue()));
        user.setDepartment("IDK");
        user.setOfficeNum(Integer.parseInt(office_tf.getText()));
        user.save();
        //Transistion to another page
    }
    
    @FXML
    void change_password(ActionEvent event){
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        sex_cb.getItems().add("M");
        sex_cb.getItems().add("F");
        employee_type_cb.getItems().add("Doctor");
        employee_type_cb.getItems().add("Admin");
        employee_type_cb.getItems().add("Executive");
        employee_type_cb.getItems().add("Nurse");
        employee_type_cb.getItems().add("Staff");
        employee_type_cb.getItems().add("LabWorker");
        uig = new UserInformationGenerator();
    }

    @Override
    public void setScreenParent(ControllerManager page){
        AppSession.CONTROLLER_MANAGER = page;
    }
    
}
