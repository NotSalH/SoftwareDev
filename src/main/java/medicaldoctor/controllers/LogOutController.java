/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LogOutController implements Initializable, ParentController{
    
    ControllerManager cm;
    
    @FXML
    void exitButtonClicked(ActionEvent event){
        //Secure Logout
    }
    
    @FXML
    void loginPageButtonClicked(ActionEvent event){
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @Override
    public void setScreenParent(ControllerManager page) {
        cm = page;
    }
    
}
