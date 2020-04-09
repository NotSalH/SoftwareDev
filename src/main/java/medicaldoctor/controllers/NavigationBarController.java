package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.Permission;

public class NavigationBarController implements Initializable, ParentController{

    @FXML
    private Button buttonMRI;

    @FXML
    private Button buttonXRay;

    @FXML
    private Button buttonWhiteBloodCell;

    @FXML
    private Button buttonMyLabRequests;

    @FXML
    private Button buttonUrinaryTest;

    @FXML
    private Button buttonDoctorLookup;

    @FXML
    private Button buttonLabRequests;

    @FXML
    private Button buttonStoolTest;

    @FXML
    private Button buttonPatientLookup;

    @FXML
    private Button buttonUserLookup;

    @FXML
    private Button buttonPatientRegistration;

    @FXML
    private Button buttonRegisterNewUser;

    @FXML
    private Button buttonCT;

    @FXML
    private Button buttonMyPatients;

    @FXML
    private Button buttonRedBloodCell;

    @FXML
    private Button buttonElectrolyteTest;

    @FXML
    private Button buttonLabPatients;

    @FXML
    private Button buttonLogs;

    @FXML
    private Button buttonRenalFunction;

    @FXML
    private Button buttonLiverFunction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*buttonMustHaveThisPermission(buttonPatientRegistration, Permission.REGISTER_PATIENT);
        buttonMustHaveThisPermission(buttonPatientLookup, Permission.ACCESS_PATIENT_LOOKUP);
        buttonMustHaveThisPermission(buttonDoctorLookup, Permission.ACCESS_DOCTOR_LOOKUP);
        buttonMustHaveThisPermission(buttonUserLookup, Permission.ACCESS_USER_LOOKUP);
        buttonMustHaveThisPermission(buttonMyLabRequests, Permission.ACCESS_MY_LAB_REQUESTS);
        buttonMustHaveThisPermission(buttonRegisterNewUser, Permission.REGISTER_NEW_USER);
        buttonMustHaveThisPermission(buttonLabRequests, Permission.ACCESS_ALL_LAB_REQUESTS);
        buttonMustHaveThisPermission(buttonLogs, Permission.ACCESS_LOGS);
        buttonMustHaveThisPermission(buttonLabPatients, Permission.ACCESS_ALL_LAB_PATIENTS);
        buttonMustHaveThisPermission(buttonRedBloodCell, Permission.VIEW_HEMOTOLOGIC_BUTTONS);
        buttonMustHaveThisPermission(buttonWhiteBloodCell, Permission.VIEW_HEMOTOLOGIC_BUTTONS);
        buttonMustHaveThisPermission(buttonLiverFunction, Permission.VIEW_HEMOTOLOGIC_BUTTONS);
        buttonMustHaveThisPermission(buttonRenalFunction, Permission.VIEW_HEMOTOLOGIC_BUTTONS);
        buttonMustHaveThisPermission(buttonElectrolyteTest, Permission.VIEW_HEMOTOLOGIC_BUTTONS);
        buttonMustHaveThisPermission(buttonUrinaryTest, Permission.VIEW_HEMOTOLOGIC_BUTTONS);
        buttonMustHaveThisPermission(buttonStoolTest, Permission.VIEW_HEMOTOLOGIC_BUTTONS);
        buttonMustHaveThisPermission(buttonXRay, Permission.VIEW_RADIOLOGIC_BUTTONS);
        buttonMustHaveThisPermission(buttonCT, Permission.VIEW_RADIOLOGIC_BUTTONS);
        buttonMustHaveThisPermission(buttonMRI, Permission.VIEW_RADIOLOGIC_BUTTONS);*/
    }

    private void buttonMustHaveThisPermission(Button button, Permission permission) {
        if (AppSession.getActiveUser().hasPermission(permission) == false) {
            button.setManaged(false);
        }
    }
    
    @Override
    public void setScreenParent(ControllerManager page) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
