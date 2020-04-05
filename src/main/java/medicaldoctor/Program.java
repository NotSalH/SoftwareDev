package medicaldoctor;

import com.sun.glass.ui.Screen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import medicaldoctor.core.DatabaseScope;

public class Program extends Application {
    
    //Use better methold than this
    public static String ScreenLoginName = "Login";
    public static String ScreenLogin = "Login.fxml";
    public static String ScreenMainScreenName = "MainScreen";
    public static String ScreenMainScreen = "MainScreen.fxml";
    
    @Override
    public void start(Stage stage){
        ControllerManager cm = new ControllerManager();
        cm.load(Program.ScreenLoginName, Program.ScreenLogin);
        cm.load(Program.ScreenMainScreenName, Program.ScreenMainScreen);   
        cm.showScreen(Program.ScreenLoginName);
        Group root = new Group();
        root.getChildren().addAll(cm);
        Scene scene = new Scene(root);
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
