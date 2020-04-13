package medicaldoctor.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.Patient;
import medicaldoctor.entities.User;
import medicaldoctor.util.InitializeException;

public class DoctorSearchController implements Initializable{
    
    @FXML
    TextField first_name_tf, last_name_tf, email_tf, dep_tf, office_tf;
    
    @FXML
    TableView<Patient> table;
    
    @FXML
    private TableColumn<Patient, String> c_first_name, c_last_name, c_sex, c_ins;
    
    @FXML
    private TableColumn<Patient, Integer> c_age;
    
    @FXML
    private List<Patient> patients;
    
    @FXML
    private List<Patient> doctors_patients;
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        first_name_tf.setText(AppSession.getDoctorSelection().getFirstName());
        last_name_tf.setText(AppSession.getDoctorSelection().getLastName());
        email_tf.setText(AppSession.getDoctorSelection().getEmail());
        dep_tf.setText(AppSession.getDoctorSelection().getDepartment());
        office_tf.setText(AppSession.getDoctorSelection().getOfficeNum() + "");
   
        doctors_patients = new ArrayList<>();
        try (DatabaseScope scope = new DatabaseScope()) {
            patients = Patient.getAll();
        } catch (Exception ex) {
           throw new InitializeException(ex);
        }
        
        for(int i=0;i<patients.size();i++){
            if(patients.get(i).getPrimaryDoctor().getUserName().equals(AppSession.getDoctorSelection().getUserName())){
                doctors_patients.add(patients.get(i));
            }
        }
        
        c_first_name.setCellValueFactory(new PropertyValueFactory("firstName"));
        c_last_name.setCellValueFactory(new PropertyValueFactory("lastName"));
        c_age.setCellValueFactory(new PropertyValueFactory("age"));
        c_sex.setCellValueFactory(new PropertyValueFactory("sex"));
        c_ins.setCellValueFactory(new PropertyValueFactory("medicalInsurance"));
        ObservableList<Patient> data = FXCollections.<Patient>observableArrayList();
        data.addAll(doctors_patients);
        table.setItems(data);
        table.refresh();
        
        table.setRowFactory(tv -> {
            TableRow<Patient> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Patient rowData = row.getItem();
                    AppSession.setPatientSelection(rowData);
                    AppSession.setPatientFlag(1);
                    try {
                        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.PATIENT_PROFILE);
                    } catch (Exception ex) {
                        Logger.getLogger(DoctorSearchController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });
    }
    
    
}
