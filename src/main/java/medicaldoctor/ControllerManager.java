/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicaldoctor;

import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;



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
           ParentInterface pc = ((ParentInterface) loader.getController());
           pc.setScreenParent(this);
           addScreen(name,parentLoader);
        }
        catch(Exception e){
            System.out.println("FXML file could not be loaded!");
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
