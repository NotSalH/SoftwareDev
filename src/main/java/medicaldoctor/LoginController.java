package medicaldoctor;

import com.sun.glass.ui.Screen;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import medicaldoctor.backend.data.LoginResult;

import javafx.stage.Stage;


import medicaldoctor.backend.LoginService;
import medicaldoctor.entities.User;
public class LoginController implements Initializable, ParentInterface {
   
    ControllerManager cm;
    
    @FXML
    private TextField textUsername;

    @FXML
    private PasswordField textPassword;

    @FXML
    private Button buttonSubmit;

    @FXML
    void buttonSubmitClick(ActionEvent event) throws Exception {
        LoginResult result = LoginService.checkLogin(
                textUsername.getText(),
                textPassword.getText());
        if (result == LoginResult.SUCCESS) {
            System.out.println("right username");
            
            //Get the user type some how 
            if(textUsername.getText() == "Admin"){
                cm.showScreen(Program.ScreenSecondLoginName);
            }
            else{
               cm.showScreen(Program.ScreenMainScreenName);
            }
        }
        if (result == LoginResult.WRONG_USERNAME) {
            System.out.println("wrong username");
        }
        if (result == LoginResult.WRONG_PASSWORD) {
            System.out.println("wrong password");
        }
    }
    
    
    public void loggedIn(ActionEvent event){
        try{
                
                /*FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen.fxml"));
                Parent parent_main = (Parent) loader.load();
                Stage stage = new Stage();
                stage.setTitle("Main screen");
                stage.setScene(new Scene(parent_main));
                Stage current = (Stage)((Node)(event.getSource())).getScene().getWindow();
                stage.show();
                current.close();*/
                
                //This will switch the scene to mainscene
                Parent mainloader = FXMLLoader.load(getClass().getClassLoader().getResource("MainScreen.fxml"));
                Scene mainScene = new Scene(mainloader);
                Stage current = (Stage)((Node)(event.getSource())).getScene().getWindow();
                current.setScene(mainScene);   
            }
        
            catch(Exception e){
               System.out.println(e);
            }
    }
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  

    @Override
    public void setScreenParent(ControllerManager page) {
        cm = page;
    }
    
    
}
