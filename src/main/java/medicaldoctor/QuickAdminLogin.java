package medicaldoctor;

import com.sun.glass.ui.Screen;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import medicaldoctor.backend.LoginService;
import medicaldoctor.controllers.LookUp;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;

public class QuickAdminLogin extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.ADMIN_DASHBOARD);
        Group root = new Group();
        root.getChildren().addAll(AppSession.CONTROLLER_MANAGER);
        Scene scene = new Scene(root);
        stage.setWidth(Screen.getMainScreen().getWidth());
        stage.setHeight(Screen.getMainScreen().getHeight());
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        try {
            LoginService.checkLogin("admin", "password123");
            launch(args);
        } finally {
            DatabaseScope._shutdown();
        }
    }

}
