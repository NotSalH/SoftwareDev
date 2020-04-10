package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import medicaldoctor.core.AppSession;

public class AdminDashboardController implements Initializable, ParentController{
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @Override
    public void setScreenParent(ControllerManager page) {
        AppSession.cm = page;
    }
    
}
