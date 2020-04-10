package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import medicaldoctor.entities.DoctorTable;

public class DoctorDashboardController implements Initializable, ParentController{
    
    ControllerManager cm;
    
    @FXML
    private TableView<DoctorTable> table;
   
    @FXML
    private TableColumn<DoctorTable, String> name, doctor, date;
    
    private ObservableList<DoctorTable> list = FXCollections.observableArrayList(
        //Ask Thomas about this
        //new DoctorTable();
    );
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.getColumns().addAll(name,doctor,date);
    }  
   
    @Override
    public void setScreenParent(ControllerManager page) {
        cm = page;
    }
    
}
