/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_ex1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

/**
 *
 * @author Salmanhussain
 */
public class FXMLDocumentController implements Initializable {
    //PLEASE NOTE: ID and onclickID are NOT the same. 
    
    //This is redeclaring the ID from the sceneBuilder
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
        button.setTextFill(color);
    }
    
    
    @FXML
    private void tx_onClick(ActionEvent event){
       button.setText(tx.getText());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
