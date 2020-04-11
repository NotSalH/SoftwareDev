package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import medicaldoctor.core.AppSession;

//Implement in next PR
public class NewUserController implements Initializable, ParentController{

    @FXML
    TextField username_tf, first_name_tf, last_name_tf, employee_id_t, email_tf, phone_tf, office_tf;
    
    @FXML
    ChoiceBox sex_cb, employee_type_cb;
    
    @FXML
    Label no_field;
    
    @FXML
    Button conformation;
    
    @FXML
    void new_user(ActionEvent event){
    }
    
    @FXML
    void confirm_user(ActionEvent event){
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
    }

    @Override
    public void setScreenParent(ControllerManager page){
        AppSession.CONTROLLER_MANAGER = page;
    }
    
}
