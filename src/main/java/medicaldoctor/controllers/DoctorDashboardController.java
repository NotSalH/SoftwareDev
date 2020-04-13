package medicaldoctor.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.Patient;
import medicaldoctor.util.InitializeException;

public class DoctorDashboardController implements Initializable {

    @FXML
    private TableView<Patient> table;

    @FXML
    private TableColumn<Patient, String> columnId, columnName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Patient> patients;
        try (DatabaseScope scope = new DatabaseScope()) {
            patients = Patient.getAll();
        } catch (Exception ex) {
            throw new InitializeException(ex);
        }

        columnId.setCellValueFactory(new PropertyValueFactory("id"));
        columnName.setCellValueFactory(new PropertyValueFactory("fullName"));

        ObservableList<Patient> data = FXCollections.<Patient>observableArrayList();
        data.addAll(patients);
        table.setItems(data);
        table.refresh();
    }  
   
    @Override
    public void setScreenParent(ControllerManager page) {
        AppSession.CONTROLLER_MANAGER = page;
    }

}
