/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class NurseDashboardController implements Initializable, ParentController{
    ControllerManager cm;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @Override
    public void setScreenParent(ControllerManager page) {
        cm = page;
    }
    
}
