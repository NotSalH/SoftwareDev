package medicaldoctor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import medicaldoctor.backend.LoginService;
import medicaldoctor.core.DatabaseScope;

/**
 * For directly testing the table fill in.
 */
public class TempTableProgram extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("DoctorDashboard.fxml"));
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
            LoginService.checkLogin("mdoctorus", "ihaveMD");
            launch(args);
        } finally {
            DatabaseScope._shutdown();
        }
    }

}
