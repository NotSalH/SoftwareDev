package medicaldoctor.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
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

public class PatientLookupController implements Initializable{
    @FXML
    private TableView<Patient> table;
    
    @FXML
    private TableColumn<Patient, String> c_first_name, c_last_name, c_sex, c_ins;
    
    @FXML
    private ChoiceBox doctor_cb;
    
    @FXML
    private TextField first_name_tf, last_name_tf;
    
    @FXML
    private TableColumn<Patient, Integer> c_age;
    
    @FXML
    private List<Patient> patients;
    
    @FXML
    private List<User> doctors;
    
    void search(TextField tf, int i){
        if(!tf.getText().isEmpty()){
            if(patients.get(i).getFirstName().toLowerCase().equals(tf.getText().toLowerCase()))
                getTableResults(patients.get(i));
            if(patients.get(i).getLastName().toLowerCase().equals(tf.getText().toLowerCase()))
                getTableResults(patients.get(i));
            
        }
    }
    
    void search(String str, int i){
        if(!str.isEmpty()){
            if(doctors.get(i).getFullName().toLowerCase().equals(str.toLowerCase()))
                getTableResults(patients.get(i));
        }
    }
    
    void getTableReuslts(Patient patients){
        table.getItems().clear();
        c_first_name.setCellValueFactory(new PropertyValueFactory("firstName"));
        c_last_name.setCellValueFactory(new PropertyValueFactory("lastName"));
        c_age.setCellValueFactory(new PropertyValueFactory("age"));
        c_sex.setCellValueFactory(new PropertyValueFactory("sex"));
        c_ins.setCellValueFactory(new PropertyValueFactory("medicalInsurance"));
        ObservableList<Patient> data = FXCollections.<Patient>observableArrayList();
        data.addAll(patients);
        table.setItems(data);
        table.refresh();
    }
    
    void getTableResults(Patient patients){
        table.getItems().clear();
        c_first_name.setCellValueFactory(new PropertyValueFactory("firstName"));
        c_last_name.setCellValueFactory(new PropertyValueFactory("lastName"));
        c_age.setCellValueFactory(new PropertyValueFactory("age"));
        c_sex.setCellValueFactory(new PropertyValueFactory("sex"));
        c_ins.setCellValueFactory(new PropertyValueFactory("medicalInsurance"));
        ObservableList<Patient> data = FXCollections.<Patient>observableArrayList();
        data.addAll(patients);
        table.setItems(data);
        table.refresh();
    }
    
    @FXML
    void search_patient(ActionEvent event){
        for(int i =0; i < patients.size(); i++){
            search(first_name_tf,i);
            search(last_name_tf,i);
            search((String) doctor_cb.getValue(), i);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
      
        try (DatabaseScope scope = new DatabaseScope()) {
            patients = Patient.getAll();
        } catch (Exception ex) {
           throw new InitializeException(ex);
        }
        
        try (DatabaseScope scope = new DatabaseScope()) {
            doctors = User.getAll();
        } catch (Exception ex) {
           throw new InitializeException(ex);
        }
        
        for(int i = 0; i<doctors.size(); i++){
            if(doctors.get(i).getType().getDashboardName().equals("DoctorDashboard")){
                doctor_cb.getItems().add(doctors.get(i).getFullName());
            }
        }
        
        c_first_name.setCellValueFactory(new PropertyValueFactory("firstName"));
        c_last_name.setCellValueFactory(new PropertyValueFactory("lastName"));
        c_age.setCellValueFactory(new PropertyValueFactory("age"));
        c_sex.setCellValueFactory(new PropertyValueFactory("sex"));
        c_ins.setCellValueFactory(new PropertyValueFactory("medicalInsurance"));
        ObservableList<Patient> data = FXCollections.<Patient>observableArrayList();
        data.addAll(patients);
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
