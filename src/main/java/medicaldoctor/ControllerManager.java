/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicaldoctor;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import medicaldoctor.backend.LoginResult;
import medicaldoctor.backend.LoginService;


public class ControllerManager extends StackPane{
    private HashMap<String, Node> screen = new HashMap<>();
  
    public ControllerManager(){
        super();
    }
    
    public void addScreen(String screenName, Node node){
        screen.put(screenName,node);
    }
    
    public Node getScrene(String screenName){
        return screen.get(screenName);
    }
    
    public void load(String name, String fxmlFile){
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
           Parent parentLoader = (Parent) loader.load(); 
           ParentController pc = ((ParentController) loader.getController());
           pc.setScreenParent(this);
           addScreen(name,parentLoader);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void showScreen(final String name){
        if(screen.get(name) != null){
            if(!getChildren().isEmpty()){
               getChildren().remove(0);
               getChildren().add(0,screen.get(name));
            }
            else{
                getChildren().add(screen.get(name));
            }
        }
        else{
            System.out.println("FXML file has not been loaded");
        }
    }
    
    
    public void removeScreen(String name){
        if(screen.remove(name) == null){
            System.out.println("Could not remove screen");
        }
    }
}
