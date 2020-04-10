/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicaldoctor.controllers.navbar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import medicaldoctor.controllers.ControllerManager;
import medicaldoctor.controllers.ParentController;
import medicaldoctor.core.AppSession;

/**
 *
 * @author Salmanhussain
 */
public class ProfileController implements Initializable, ParentController{

    @Override
    public void initialize(URL location, ResourceBundle resources){
        
    }

    @Override
    public void setScreenParent(ControllerManager page){
        AppSession.cm = page;
    }
    
}
