package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import medicaldoctor.core.AppSession;

public class AdminDashboardController implements Initializable, ParentController {

    //@FXML
    //private TableView<DoctorTable> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(ControllerManager page) {
        AppSession.cm = page;
    }

}
