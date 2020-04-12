package medicaldoctor.controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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

public class NewUserController implements Initializable, ParentController{

    @FXML
    TextField textFirstName, textLastName, textDepartment, textOfficeNumber;
    
    @FXML
    ChoiceBox employeeTypeChoiceBox;
    
    @FXML
    Button submitButton;
    
    @FXML
    Label success,first_name_label,last_name_label,employee_type_label,department_label, office_label;
    
    HashMap<TextField, Label> textfeild_hash = new HashMap<>();
    
    NewUserRequest new_user_request = new NewUserRequest();
    
    int mask = 0b11;

    @FXML
    void submitButtonClick(ActionEvent event) throws Exception{
        if(isFieldsEmpty(textfeild_hash)){ mask &= 0b10;}
        if(checkFields(employeeTypeChoiceBox, employee_type_label)){ mask &= 0b01;}
        if(mask == 00){
            makeNewUser();
        }
        else{
            mask = 0b11;
        }
    }
    
    private void makeNewUser() throws Exception{
        new_user_request.firstName = textFirstName.getText();
        new_user_request.lastName = textLastName.getText();
        new_user_request.userType = User.byName(((String)employeeTypeChoiceBox.getValue()).toUpperCase());
        new_user_request.officeNum = Integer.parseInt(isInteger(textOfficeNumber.getText()));
        new_user_request.department = textDepartment.getText();
        UserService.createNewUser(new_user_request);
    }
    
    boolean checkFields(ChoiceBox cb, Label label){
        if(cb.getSelectionModel().isEmpty()){
            label.setTextFill(Color.RED);
            return false;
        }
        else{
            label.setTextFill(Color.GREEN);
            return true;
        }
  
    }
    
    public static String isInteger(String str) {
        if (str == null) {
            return "0";
        }
        int length = str.length();
        if (length == 0) {
            return "0";
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return "0";
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return "0";
            }
        }
        return str;
    }
    
    private boolean isFieldsEmpty(Map<TextField, Label> map){
        int flag = 0;
        for(Map.Entry<TextField,Label> entry : map.entrySet()){
            if(entry.getKey().getText().isEmpty()){
                entry.getValue().setTextFill(Color.RED);
                flag = 1;
            }
            else{
               entry.getValue().setTextFill(Color.GREEN); 
            }
        }
        return flag == 0;
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
        
        textfeild_hash.put(textFirstName, first_name_label);
        textfeild_hash.put(textLastName, last_name_label);
        textfeild_hash.put(textDepartment, department_label);
        textfeild_hash.put(textOfficeNumber, office_label);
    }

    @Override
    public void setScreenParent(ControllerManager page){
        AppSession.CONTROLLER_MANAGER = page;
    }
    
}
