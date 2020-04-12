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
import javafx.scene.paint.Color;
import medicaldoctor.backend.UserService;
import medicaldoctor.backend.data.NewUserRequest;
import medicaldoctor.core.AppSession;
import medicaldoctor.entities.User;
import medicaldoctor.entities.UserType;

public class NewUserController implements Initializable, ParentController{

    @FXML
    TextField textFirstName, textLastName, textDepartment, textOfficeNumber;
    
    @FXML
    ChoiceBox employeeTypeChoiceBox;
    
    @FXML
    Button submitButton;
    
    @FXML
    Label success,first_name_label,last_name_label,employee_type_label,department_label, office_label;
    
    NewUserRequest new_user_request = new NewUserRequest();
    
    @FXML
    void submitButtonClick(ActionEvent event) throws Exception{
        if(textFirstName.getText().isEmpty()){
            first_name_label.setTextFill(Color.RED);
        }
        if(textLastName.getText().isEmpty()){
            last_name_label.setTextFill(Color.RED);
        }
        if(textDepartment.getText().isEmpty()){
            department_label.setTextFill(Color.RED);
        }
        if(textOfficeNumber.getText().isEmpty()){
            office_label.setTextFill(Color.RED);
        }
        if(employeeTypeChoiceBox.getSelectionModel().isEmpty()){
            employee_type_label.setTextFill(Color.RED);
        }
        
        if(!(textFirstName.getText().isEmpty() && textLastName.getText().isEmpty() && textDepartment.getText().isEmpty() && textOfficeNumber.getText().isEmpty()
               && employeeTypeChoiceBox.getSelectionModel().isEmpty())){
            first_name_label.setTextFill(Color.GREEN);
            last_name_label.setTextFill(Color.GREEN);
            department_label.setTextFill(Color.GREEN);
            office_label.setTextFill(Color.GREEN);
            employee_type_label.setTextFill(Color.GREEN);
            makeNewUser();
        }
    }
    
    private void makeNewUser() throws Exception{
        new_user_request.firstName = textFirstName.getText();
        new_user_request.lastName = textLastName.getText();
        new_user_request.userType = User.byName(((String)employeeTypeChoiceBox.getValue()).toUpperCase());
        new_user_request.officeNum = Integer.parseInt(textOfficeNumber.getText());
        new_user_request.department = department_label.getText();
        UserService.createNewUser(new_user_request);
    }
    
    public void clearFeilds(){
        first_name_label.setTextFill(Color.BLACK);
        last_name_label.setTextFill(Color.BLACK);
        department_label.setTextFill(Color.BLACK);
        office_label.setTextFill(Color.BLACK);
        employee_type_label.setTextFill(Color.BLACK);
        textFirstName.setText("");
        textLastName.setText("");
        textDepartment.setText("");
        textOfficeNumber.setText("");
    }
    
    public UserType getType(String type){
        switch (type) {
            case "Admin":
                return UserType.ADMIN;
            case "Executive":
                return UserType.EXECUTIVE;
            case "Doctor":
                return UserType.DOCTOR;
            case "Nurse":
                return UserType.NURSE;
            case "RadiologicalLab":
                return UserType.HEMATOLOGIC_LAB_WORKER;
            case "HematologicLab":
                return UserType.HEMATOLOGIC_LAB_WORKER;
            default:
                return UserType.STAFF;
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        employeeTypeChoiceBox.getItems().add("Admin");
        employeeTypeChoiceBox.getItems().add("Executive");
        employeeTypeChoiceBox.getItems().add("Doctor");
        employeeTypeChoiceBox.getItems().add("Nurse");
        employeeTypeChoiceBox.getItems().add("RadiologicalLab");
        employeeTypeChoiceBox.getItems().add("HematologicLab");
        employeeTypeChoiceBox.getItems().add("Staff");
    }

    @Override
    public void setScreenParent(ControllerManager page){
        AppSession.CONTROLLER_MANAGER = page;
    }
    
}
