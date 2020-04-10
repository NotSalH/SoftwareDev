package medicaldoctor;

import medicaldoctor.controllers.LookUp;
import medicaldoctor.controllers.ControllerManager;
import com.sun.glass.ui.Screen;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import medicaldoctor.core.DatabaseScope;

public class Program extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        ControllerManager cm = new ControllerManager();
        cm.load(LookUp.LOGIN_SCREEN);
        cm.showScreen(LookUp.LOGIN_SCREEN);
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
