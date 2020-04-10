package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class AdminDashboardController implements Initializable, ParentController{
    
    ControllerManager cm;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setScreenParent(ControllerManager page) {
        cm = page;
    }
    
}
