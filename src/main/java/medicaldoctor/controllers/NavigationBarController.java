package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import medicaldoctor.backend.LoginService;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.Permission;

public class NavigationBarController implements Initializable {

    @FXML
    private VBox vBox;
    @FXML
    private ToolBar toolBar;

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

    @FXML
    void patientRegistration(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.PATIENT_REGISTRATION);
    }

    @FXML
    void patientLookup(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.PATIENT_LOOKUP);
    }

    @FXML
    void doctorLookup(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.DOCTOR_LOOKUP);
    }

    @FXML
    void userLookup(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.USER_LOOKUP);
    }

    @FXML
    void myPatients(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.MY_PATIENT);
    }

    @FXML
    void myLabRequests(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.MY_LAB_REQUESTS);
    }

    @FXML
    void registerNewUser(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.REGISTER_NEW_USER);
    }

    @FXML
    void labRequests(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.LAB_REQUESTS);
    }

    @FXML
    void log(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.LOG);
    }

    @FXML
    void labPatients(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.LAB_PATIENT);
    }

    @FXML
    void redBloodCell(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.RED_BLOOD_CELLS);
    }

    @FXML
    void whiteBloodCell(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.WHITE_BLOOD_CELLS);
    }

    @FXML
    void liverFunctions(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.LIVER_FUNCTIONS);
    }

    @FXML
    void renalFuctions(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.RENAL_FUNCTIONS);
    }

    @FXML
    void electrolyteTest(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.ELECTROLYTE_TEST);
    }

    @FXML
    void urinaryTest(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.URINARY_TEST);
    }

    @FXML
    void stoolTest(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.STOOL_TEST);
    }

    @FXML
    void xRay(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.X_RAY);
    }

    @FXML
    void ct(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.CT);
    }

    @FXML
    void mri(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.MRI);
    }

    @FXML
    void logout(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.LOG_OUT);
        AppSession.logout();
    }

    @FXML
    void profile(ActionEvent event) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.PROFILE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonMustHaveThisPermission(buttonPatientRegistration, Permission.REGISTER_PATIENT);
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
        buttonMustHaveThisPermission(buttonMRI, Permission.VIEW_RADIOLOGIC_BUTTONS);
    }

    private void buttonMustHaveThisPermission(Button button, Permission permission) {
        if (AppSession.getActiveUser().hasPermission(permission) == false) {
            button.setManaged(false);
        }
    }

}
