package medicaldoctor.controllers;

//LookUp table for all FXML files
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LookUp {

    public static final String LOGIN_SCREEN = "Login";
    public static final String LOGIN_SECOND_SCREEN = "AdditionalLogin";
    public static final String DOCTOR_DASHBOARD = "DoctorDashboard";
    public static final String NURSE_DASHBOARD = "NurseDashboard";
    public static final String ADMIN_DASHBOARD = "AdminDashboard";
    public static final String STAFF_DASHBOARD = "StaffDashboard";
    public static final String LAB_WORKER = "LabWorkerDashBoard";
    public static final String LOG_OUT = "LogOut";
    public static final String PATIENT_REGISTRATION = "PatientRegistration";
    public static final String REGISTER_NEW_USER = "RegisterNewUser";
    public static final String REGISTER_NEW_USER_RESULT = "UserRegistrationOutput";

    //FXML File does not exist
    public static final String PATIENT_LOOKUP = "PatientLookup";
    public static final String DOCTOR_LOOKUP = "DoctorLookup";
    public static final String USER_LOOKUP = "UserLookup";
    public static final String MY_PATIENT = "MyPatients";
    public static final String MY_LAB_REQUESTS = "MyLabRequests";
    public static final String LAB_REQUESTS = "LabRequests";
    public static final String LOG = "Log";
    public static final String LAB_PATIENT = "LabPatient";
    public static final String WHITE_BLOOD_CELLS = "WhiteBloodCells";
    public static final String RED_BLOOD_CELLS = "RedBloodCells";
    public static final String LIVER_FUNCTIONS = "LiverFunctions";
    public static final String RENAL_FUNCTIONS = "RenalFunctions";
    public static final String ELECTROLYTE_TEST = "ElectrolyteTest";
    public static final String URINARY_TEST = "UrinaryTest";
    public static final String STOOL_TEST = "StoolTest";
    public static final String X_RAY = "XRay";
    public static final String CT = "Ct";
    public static final String MRI = "Mri";
    public static final String PROFILE = "Profile";
    
    public static ObservableList<String> STATES = FXCollections.observableArrayList(
            "AK", "AL", "AR", "AS", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI", "IA",
            "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MP", "MS", "MT",
            "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "RI", "SC",
            "SD", "TN", "TX", "UM", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY"
    );

}
