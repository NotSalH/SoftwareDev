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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import medicaldoctor.backend.LoginService;
import medicaldoctor.backend.data.LoginResult;
import medicaldoctor.core.AppSession;
import medicaldoctor.entities.UserType;

/**
 *
 * @author Salmanhussain
 */
public class AdditionalLoginController implements Initializable, ParentController{
    ControllerManager cm;
    
    @FXML
    Button login;
    @FXML
    Label password, incorrect_id;
    
    @FXML
    void login_second(ActionEvent event) throws Exception{
        LoginResult result = LoginService.checkAdditionalPassword(password.getText());
        if(result == LoginResult.SUCCESS){
            if(AppSession.getActiveUser().getType().equals(UserType.ADMIN)){
            }
            else if(AppSession.getActiveUser().getType().equals(UserType.EXECUTIVE)){
                
            }
            else{
                //do nothing. IDK if this case will ever be reached. This is just in case. 
            }
        }
        else{
            incorrect_id.setVisible(true);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
    }

    @Override
    public void setScreenParent(ControllerManager page) {
        cm = page;
    }
    
}
