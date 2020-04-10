package medicaldoctor.controllers.navbar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import medicaldoctor.controllers.ControllerManager;
import medicaldoctor.controllers.LookUp;
import medicaldoctor.controllers.ParentController;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.Permission;

public class NavigationBarController implements Initializable, ParentController{
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
    void patientRegistration(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.PATIENT_REGISTRATION);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.PATIENT_REGISTRATION);
    }
    
    @FXML
    void patientLookup(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.PATIENT_LOOKUP);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.PATIENT_REGISTRATION); 
    }
    
    @FXML
    void doctorLookup(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.DOCTOR_LOOKUP);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.DOCTOR_LOOKUP); 
    }
    
    @FXML
    void userLookup(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.USER_LOOKUP);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.USER_LOOKUP);
    }
    
    @FXML
    void myPatients(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.MY_PATIENT);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.MY_PATIENT);
    }
    
    @FXML
    void myLabRequests(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.MY_LAB_REQUESTS);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.MY_LAB_REQUESTS); 
    }
    
    @FXML
    void registerNewUser(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.REGISTER_NEW_USER);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.REGISTER_NEW_USER);
    }
    
    @FXML
    void labRequests(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.LAB_REQUESTS);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.LAB_REQUESTS);
    }
    
    @FXML
    void log(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.LOG);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.LOG);
    }
    
    @FXML
    void labPatients(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.LAB_PATIENT);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.LAB_PATIENT);
    }
    
    @FXML
    void redBloodCell(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.RED_BLOOD_CELLS);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.RED_BLOOD_CELLS);
    }
    
    @FXML
    void whiteBloodCell(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.WHITE_BLOOD_CELLS);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.WHITE_BLOOD_CELLS);
    }
    
    @FXML
    void liverFunctions(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.LIVER_FUNCTIONS);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.LIVER_FUNCTIONS);
    }
    
    @FXML
    void renalFuctions(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.RENAL_FUNCTIONS);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.RENAL_FUNCTIONS);
    }
    
    @FXML
    void electrolyteTest(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.ELECTROLYTE_TEST);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.ELECTROLYTE_TEST);
    }
    
    @FXML
    void urinaryTest(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.URINARY_TEST);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.URINARY_TEST);
    }
    
    @FXML
    void stoolTest(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.STOOL_TEST);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.STOOL_TEST);
    }
    
    @FXML
    void xRay(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.X_RAY);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.X_RAY);
    }
    
    @FXML
    void ct(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.CT);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.CT); 
    }
    
    @FXML
    void mri(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.MRI);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.MRI);
    }
    
    @FXML
    void logout(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.LOG_OUT);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.LOG_OUT);
    }
    
    @FXML
    void profile(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.load(LookUp.PROFILE);
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.PROFILE); 
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
    
    @Override
    public void setScreenParent(ControllerManager page) {
        AppSession.CONTROLLER_MANAGER = page;
    }
    
}
