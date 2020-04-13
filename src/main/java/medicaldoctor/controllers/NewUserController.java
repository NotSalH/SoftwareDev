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
import medicaldoctor.backend.data.NewUserResult;
import medicaldoctor.core.AppSession;
import medicaldoctor.entities.UserType;

public class NewUserController implements Initializable {
    
    @FXML
    TextField textFirstName, textLastName, textDepartment, textOfficeNumber;
    
    @FXML
    ChoiceBox employeeTypeChoiceBox;
    
    @FXML
    Button submitButton;
    
    @FXML
    Label success, first_name_label, last_name_label, employee_type_label, department_label, office_label;
    
    HashMap<TextField, Label> textfield_hash = new HashMap<>();
    
    NewUserRequest new_user_request = new NewUserRequest();
    
    @FXML
    void submitButtonClick(ActionEvent event) throws Exception {
        // these check methods display message to users if they fail.
        if (areTextFieldsFilled()) {
            if (isSelectionFilled(employeeTypeChoiceBox, employee_type_label)) {
                NewUserResult result = makeNewUser();
                AppSession.setNewUserResult(result);
                AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.REGISTER_NEW_USER_RESULT);
            }
        }
    }
    
    private NewUserResult makeNewUser() throws Exception {
        new_user_request.firstName = textFirstName.getText();
        new_user_request.lastName = textLastName.getText();
        new_user_request.userType = (String) employeeTypeChoiceBox.getValue();
        new_user_request.officeNum = Integer.parseInt(isInteger(textOfficeNumber.getText()));
        new_user_request.department = textDepartment.getText();
        return UserService.createNewUser(new_user_request);
    }
    
    boolean isSelectionFilled(ChoiceBox cb, Label label) {
        if (cb.getSelectionModel().isEmpty()) {
            label.setTextFill(Color.RED);
            return false;
        } else {
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
    
    private boolean areTextFieldsFilled() {
        boolean areAllFilled = true;
        for (Map.Entry<TextField, Label> entry : textfield_hash.entrySet()) {
            if (entry.getKey().getText().isEmpty()) {
                entry.getValue().setTextFill(Color.RED);
                areAllFilled = false;
            } else {
                entry.getValue().setTextFill(Color.GREEN);
            }
        }
        return areAllFilled;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (UserType userType : UserType.ALL) {
            employeeTypeChoiceBox.getItems().add(userType.getName());
        }
        textfield_hash.put(textFirstName, first_name_label);
        textfield_hash.put(textLastName, last_name_label);
        textfield_hash.put(textDepartment, department_label);
        textfield_hash.put(textOfficeNumber, office_label);
    }
    
}
