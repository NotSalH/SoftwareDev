package medicaldoctor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import medicaldoctor.backend.LoginService;
import medicaldoctor.core.DatabaseScope;

/**
 * Temporary runnable to show how javafx includes work for the navbar, as we do
 * not have the scene transitions after login yet.
 */
public class TempNavBarExampleProgram extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AdminDashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            // skip logging in
            LoginService.checkLogin("fdesk", "sitaround");
            launch(args);
        } finally {
            DatabaseScope._shutdown();
        }
    }

}
