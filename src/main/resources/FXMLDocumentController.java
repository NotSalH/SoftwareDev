package guisoftwaredev;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
//import javafx.scene.control.MenuItem;

/**
 *
 * @author Owner
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private MenuButton userOptions;
    
    /*
    @FXML
    private MenuItem profile;
    @FXML
    private MenuItem logOut;
    
    */
    
    private int clickCount = 0;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView userPicture;
    
    @FXML
    public void onProfilePictureClicked(){
        clickCount++;
        if(clickCount % 2 == 1){
            userOptions.setVisible(true);
        }else{
            userOptions.setVisible(false);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
