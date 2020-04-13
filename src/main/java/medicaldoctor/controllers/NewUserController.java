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
import medicaldoctor.backend.data.NewUserRequest;
import medicaldoctor.core.AppSession;

public class NewUserController implements Initializable {

    @FXML
    TextField textFirstName, textLastName, textDepartment, textOfficeNumber;

    @FXML
    ChoiceBox employeeTypeChoiceBox;

    @FXML
    Button submitButton;

    @FXML
    Label success, first_name_label, last_name_label, employee_type_label, department_label, office_label;

    HashMap<TextField, Label> textfeild_hash = new HashMap<>();

    NewUserRequest new_user_request = new NewUserRequest();

    int mask = 0b11;

    @FXML
    void submitButtonClick(ActionEvent event) throws Exception {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
