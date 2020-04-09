package medicaldoctor.controllers;

import java.io.IOException;
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
    
    public void load(String name, String fxmlFile) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
        Parent parentLoader = (Parent) loader.load(); 
        ParentController pc = ((ParentController) loader.getController());
        pc.setScreenParent(this);
        addScreen(name,parentLoader); 
    }
    
    public void showScreen(final String name) throws Exception{
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
            //File has not been loaded
        }
    }
    
    public void removeScreen(String name) throws Exception{
        if(screen.remove(name) == null){
           //Screen cannot be removed
        }
    }
}