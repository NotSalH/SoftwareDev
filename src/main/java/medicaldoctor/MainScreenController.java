package medicaldoctor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import medicaldoctor.backend.LoginResult;
import medicaldoctor.backend.LoginService;

public class MainScreenController implements Initializable, ParentInterface{
    
    ControllerManager cm;
    @FXML
    private Label label;
    @FXML
    private Button button;
    
    @FXML
    private ColorPicker colorPicker;
    
    @FXML
    private TextField tx;
    
    @FXML
    private AnchorPane anchor_pane;
    
    
    //This is the onclickID defined in the scenebuilder.
    //One button
    @FXML
    private void button_1(ActionEvent event) {
        label.setText("Boo!");
    }
    
    //Changes the color of the button text
    @FXML
    private void pick_color(ActionEvent event){
        Color color = colorPicker.getValue();
        label.setTextFill(colorPicker.getValue());
        button.setTextFill(color);
    }
    
    @FXML
    private void tx_onClick(ActionEvent event){
       button.setText(tx.getText());
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @Override
    public void setScreenParent(ControllerManager page) {
        cm = page;
    }

    
}
