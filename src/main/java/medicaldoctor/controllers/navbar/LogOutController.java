/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicaldoctor.controllers.navbar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import medicaldoctor.controllers.ControllerManager;
import medicaldoctor.controllers.LookUp;
import medicaldoctor.controllers.ParentController;
import medicaldoctor.core.AppSession;

public class LogOutController implements Initializable, ParentController{
    
    @FXML
    void exitButtonClicked(ActionEvent event){
        Platform.exit();
    }
    
    @FXML
    void loginPageButtonClicked(ActionEvent event) throws Exception{
        AppSession.CONTROLLER_MANAGER.showScreen(LookUp.LOGIN_SCREEN);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @Override
    public void setScreenParent(ControllerManager page) {
        AppSession.CONTROLLER_MANAGER = page;
    }
    
}
