package medicaldoctor.core;

import java.time.LocalDateTime;
import static medicaldoctor.backend.LoginService.MESSAGE_USER_LOGGED_OUT;
import medicaldoctor.backend.data.NewUserResult;
import medicaldoctor.controllers.ControllerManager;
import medicaldoctor.entities.LogRecord;
import medicaldoctor.entities.Patient;
import medicaldoctor.entities.PatientVisit;
import medicaldoctor.entities.User;
import medicaldoctor.util.EmailGenerator;
import medicaldoctor.util.EmailNameGenerator;
import medicaldoctor.util.Encryption;
import medicaldoctor.util.FirstLetterLastNameUserNameGenerator;
import medicaldoctor.util.PasswordGenerator;
import medicaldoctor.util.RandomPasswordGenerator;
import medicaldoctor.util.UserNameGenerator;

/**
 * Static global state of the program session.
 */
public final class AppSession {

    public static PasswordGenerator TEMPORARY_PASSWORD_GENERATOR
            = new RandomPasswordGenerator(18);
    public static UserNameGenerator USER_NAME_GENERATOR
            = new FirstLetterLastNameUserNameGenerator();
    public static Encryption ENCRYPTION = new Encryption();
    public static ControllerManager CONTROLLER_MANAGER = new ControllerManager();
    public static EmailGenerator EMAIL_GENERATOR
            = new EmailNameGenerator();

    private static User activeUser;
    private static Patient currentPatient;
    private static PatientVisit currentVisit;
    private static NewUserResult newUserResult;
    private static User adminUserSelection;
    private static Integer adminFlag;
    private static User doctorSelection;
    private static Integer doctorFlag;
    
    public static User getActiveUser() {
        if (activeUser == null) {
            throw new IllegalStateException("User is not logged on");
        }
        return activeUser;
    }

    public static void setActiveUser(User user) {
        activeUser = user;
    }
    
    public static void setAdminUserSelection(User user){
        adminUserSelection = user;
    }
    
    public static void setDoctorSelction(User user){
        doctorSelection = user;
    }
    
    public static void setDoctorFlag(int flag){
        doctorFlag = flag;
    }
    
    public static void setAdminFlag(int flag){
        adminFlag = flag;
    }
    
    public static User getAdminUserSelection(){
        return adminUserSelection;
    }
    
    public static User getDoctorSelection(){
        return doctorSelection;
    }
    
    public static Integer getAdminFlag(){
        return adminFlag;
    }
    
    public static Integer getDoctorFlag(){
        return doctorFlag;
    }
    
    public static boolean isLoggedIn() {
        return activeUser != null;
    }

    public static Patient getCurrentPatient() {
        return currentPatient;
    }

    public static void setCurrentPatient(Patient currentPatient) {
        AppSession.currentPatient = currentPatient;
    }

    public static PatientVisit getCurrentVisit() {
        return currentVisit;
    }

    public static void setCurrentVisit(PatientVisit currentVisit) {
        AppSession.currentVisit = currentVisit;
    }

    public static NewUserResult getNewUserResult() {
        return newUserResult;
    }

    public static void setNewUserResult(NewUserResult newUserResult) {
        AppSession.newUserResult = newUserResult;
    }

    /**
     * Load the dashboard, depending on the UserType.
     *
     * @throws Exception
     */
    public static void loadDashboard() throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(
                AppSession.getActiveUser().getType().getDashboardName());
    }

    /**
     * Logout backend by logging the action and clearing global state variables.
     *
     * @throws Exception
     */
    public static void logout() throws Exception {
        AppSession.logEventInNewScope(MESSAGE_USER_LOGGED_OUT);
        AppSession.setActiveUser(null);
        AppSession.setCurrentPatient(null);
        AppSession.setCurrentVisit(null);
    }

    /**
     * Log a new event to the database, assumes we are in a DatabaseScope.
     *
     * @param description
     */
    public static void logEvent(String description) {
        logEvent0(description);
    }

    /**
     * Log a new event to the database, assumes we are NOT in a DatabaseScope,
     * and thus will briefly create a new session.
     *
     * @param description
     * @throws Exception
     */
    public static void logEventInNewScope(String description) throws Exception {
        try (DatabaseScope scope = new DatabaseScope()) {
            logEvent0(description);
        }
    }

    private static void logEvent0(String description) {
        LogRecord record = new LogRecord();
        record.setDateTime(LocalDateTime.now());
        record.setUser(getActiveUser());
        record.setDescription(description);
        record.save();
    }

}
