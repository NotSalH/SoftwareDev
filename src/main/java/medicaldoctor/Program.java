package medicaldoctor;

import medicaldoctor.controllers.ControllerManager;
import com.sun.glass.ui.Screen;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import medicaldoctor.core.DatabaseScope;

public class Program extends Application {
    public static final String LOGIN_SCREEN_NAME = "Login";
    private static final String LOGIN_SCREEN = "Login.fxml";
    public static final String LOGIN_SECOND_SCREEN_NAME = "AdditionalLogin";
    private static final String LOGIN_SECOND_SCREEN = "AdditionalLogin.fxml";
    public static final String DOCTOR_DASHBOARD_NAME = "DoctorDashboard";
    private static final String DOCTOR_DASHBOARD = "DoctorDashboard.fxml";
    public static final String NURSE_DASHBOARD_NAME = "NurseDashboard";
    private static final String NURSE_DASHBOARD = "NurseDashboard.fxml";
    
    @Override
    public void start(Stage stage) throws Exception {
        ControllerManager cm = new ControllerManager();
        cm.load(LOGIN_SCREEN_NAME, LOGIN_SCREEN);
        cm.load(LOGIN_SECOND_SCREEN_NAME, LOGIN_SECOND_SCREEN);
        cm.load(DOCTOR_DASHBOARD_NAME, DOCTOR_DASHBOARD);  
        cm.showScreen(Program.LOGIN_SCREEN_NAME);
        Group root = new Group();
        root.getChildren().addAll(cm);
        Scene scene = new Scene(root);
        stage.setWidth(Screen.getMainScreen().getWidth());
        stage.setHeight(Screen.getMainScreen().getHeight());
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            launch(args);
        } finally {
            DatabaseScope._shutdown();
        }
    }

}
