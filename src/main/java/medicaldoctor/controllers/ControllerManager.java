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
    
    public Node getScreen(String screenName){
        return screen.get(screenName);
    }
    
    public void load(String name) throws IOException{
        String path = name+".fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(path));
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
    
}