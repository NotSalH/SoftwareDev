package medicaldoctor.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.Patient;
import medicaldoctor.entities.PatientPrescription;
import medicaldoctor.entities.PatientVisit;
import medicaldoctor.entities.User;

public class PatientRegistrationController implements Initializable, ParentController{
    
    @FXML
    private TitledPane newPatientTiltedPane, returningPatientTiltedPane;
    
    @FXML
    private TextField textFirstName, textLastName, ageNumber, textSex;
    
    @FXML
    private TextField textMedicalInsurance, textBirthdate, textPrimaryDoctory;
    
    @FXML
    private TextField textStreetMailingAddress, textCityMailingAddress;
    
    @FXML
    private ChoiceBox stateMailingAddressChoiceBox, stateBillingAddressChoiceBox, doctor_dropdown;
    
    @FXML
    private TextField zipCodeMailingAddressNumber, textStreetBillingAddress, textCityBillingAddress, zipCodeBillingAddressNumber, textDoctor, textVisitDate;
    
    @FXML
    private TextArea textAreaChiefComplaint, textAreaPresentIllness;
    
    @FXML
    private Label first_name_id, last_id, age_id, sex_id, med_id, dob_id, prime_doctor_id;
    
    @FXML
    private Label mail_street_id, mail_city_id, mail_state_id, mail_zip_id;
    
    @FXML
    private Label bill_street_id, bill_city_id, bill_state_id, bill_zip_id;
    
    @FXML
    private Label doctor_id, visit_id, cheif_id, cheig_id, pres_id;
    
    @FXML
    private TableView<Patient> table;
    
    @FXML
    private TableColumn<Patient, String> patientId, firstName, lastName;
   
    @FXML
    private TableColumn<Patient, LocalDate> DOB;
    
    @FXML
    private TableColumn<Patient, User> PrimaryDoctor;
    
    private HashMap<TextField, Label> textfeild_hash = new HashMap<>();
    private HashMap<String, User> user_hash = new HashMap<>();
    
    private int mask = 0b11111;
    
    @FXML
    void submitButtonClick(ActionEvent event){
        if(isFieldsEmpty(textfeild_hash)){ mask &=11110;}
        if(checkFields(textAreaChiefComplaint, cheif_id)){mask &=11101;}
        if(checkFields(textAreaPresentIllness, pres_id)){mask &=11011;}
        if(checkFields(stateMailingAddressChoiceBox, mail_state_id)){mask &=10111;}
        if(checkFields(stateBillingAddressChoiceBox, bill_state_id)){mask &=01111;}
        if(checkFields(doctor_dropdown, doctor_id)){mask &=01111;}
        if(mask == 0){
            Patient patient = new Patient();
            PatientVisit visit = new PatientVisit();
            PatientPrescription prescription = new PatientPrescription();
            makePatient(patient,visit,prescription);
        }
        mask = 0b11111;
    }
    
    void makePatient(Patient patient, PatientVisit visit,PatientPrescription prescription){
        patient.setFirstName(textFirstName.getText());
        patient.setLastName(textLastName.getText());
        patient.setAge(Integer.parseInt(isInteger(ageNumber.getText())));
        patient.setSex(textSex.getText());
        patient.setDateOfBirth(LocalDate.of(1997, 10, 1));
        patient.setMedicalInsurance(textMedicalInsurance.getText());
        patient.setAddressStreet(textStreetMailingAddress.getText());
        patient.setAddressCity(textCityMailingAddress.getText());
        patient.setAddressState((String)stateMailingAddressChoiceBox.getValue());
        patient.setAddressZipCode(zipCodeMailingAddressNumber.getText());
        patient.setBillingAddressStreet(textStreetBillingAddress.getText());
        patient.setBillingAddressCity(textCityBillingAddress.getText());
        patient.setBillingAddressState((String)stateBillingAddressChoiceBox.getValue());
        patient.setBillingAddressZipCode(zipCodeBillingAddressNumber.getText());
        patient.setPrimaryDoctor(user_hash.get((String)doctor_dropdown.getValue()));
        patient.setSocialSecurityNumber("123-56-7890");
        prescription.setPrescription("");
        prescription.setInstructions("");
        prescription.getPharmacy().setStreet("");
        prescription.getPharmacy().setCity("");
        prescription.getPharmacy().setState("");
        prescription.getPharmacy().setZipCode("");
        prescription.getPharmacy().setEmail("");
        prescription.getPharmacy().setPhone("");
        prescription.getPharmacy().setFax("");
        visit.addPrescription(prescription);
        visit.setDoctor(user_hash.get((String)doctor_dropdown.getValue()));
        visit.setVisitDate(LocalDate.of(2020, 4, 1));
        visit.setChiefComplaint("");
        visit.setPresentIllness("");
        visit.setSymptoms("");
        visit.setPhysicalExamNotes("");
        visit.setDiagnosis("");
        visit.setImpression("");
        visit.setAdditionalNotes("");
        visit.save();
        patient.addVisit(visit);
        patient.save();
    }
    
    @FXML
    void selectButtonClick(ActionEvent event){
        
    }
    
    private boolean isFieldsEmpty(Map<TextField, Label> map){
        int flag = 0;
        for(Map.Entry<TextField,Label> entry : map.entrySet()){
            if(entry.getKey().getText().isEmpty()){
                entry.getValue().setTextFill(Color.RED);
                flag = 1;
            }
            else{
               entry.getValue().setTextFill(Color.GREEN); 
            }
        }
        return flag == 0;
    }
    
    public static String isInteger(String str) {
        if (str == null) {
            return "0";
        }
        int length = str.length();
        if (length == 0) {
            return "0";
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return "0";
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return "0";
            }
        }
        return str;
    }
    
    boolean checkFields(ChoiceBox cb, Label label){
        if(cb.getSelectionModel().isEmpty()){
            label.setTextFill(Color.RED);
            return false;
        }
        else{
            label.setTextFill(Color.GREEN);
            return true;
        }
  
    }
    
    boolean checkFields(TextArea ta, Label label){
        if(ta.getText().isEmpty()){
            label.setTextFill(Color.GREEN);
            return true;
        }
        else{
            label.setTextFill(Color.BLACK);
            return false;
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        for(String i : LookUp.STATES){
            stateMailingAddressChoiceBox.getItems().add(i);
            stateBillingAddressChoiceBox.getItems().add(i);
        }
        
        List<User> user;
        try (DatabaseScope scope = new DatabaseScope()) {
            user = User.getAll();
        } catch (Exception ex) {
           user = new ArrayList<>();
        }
        
        List<Patient> patients;
        try (DatabaseScope scope = new DatabaseScope()) {
            patients = Patient.getAll();
        } catch (Exception ex) {
           patients = new ArrayList<>();
        }
        
        patientId.setCellValueFactory(new PropertyValueFactory("id"));
        firstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        DOB.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));
        PrimaryDoctor.setCellValueFactory(new PropertyValueFactory("primaryDoctor"));
        ObservableList<Patient> data = FXCollections.<Patient>observableArrayList();
        data.addAll(patients);
        table.setItems(data);
        table.refresh();
        
        for(int i =0; i < user.size(); i++){
            if(user.get(i).getType().getDashboardName().equals("DoctorDashboard")){
                doctor_dropdown.getItems().add(user.get(i).getFullName());
                user_hash.put(user.get(i).getFullName(), user.get(i));
            }
        }
        
        textfeild_hash.put(textFirstName,first_name_id);
        textfeild_hash.put(textLastName, last_id);
        textfeild_hash.put(ageNumber, age_id);
        textfeild_hash.put(textSex, sex_id);
        textfeild_hash.put(textMedicalInsurance, med_id);
        textfeild_hash.put(textBirthdate, dob_id);
        textfeild_hash.put(textPrimaryDoctory, prime_doctor_id);
        textfeild_hash.put(textStreetMailingAddress, mail_street_id);
        textfeild_hash.put(textCityMailingAddress, mail_city_id);
        textfeild_hash.put(zipCodeMailingAddressNumber, mail_zip_id);
        textfeild_hash.put(textStreetBillingAddress, bill_street_id);
        textfeild_hash.put(textCityBillingAddress, bill_city_id);
        textfeild_hash.put(zipCodeBillingAddressNumber, bill_zip_id);
        textfeild_hash.put(textVisitDate, visit_id);
    }

    @Override
    public void setScreenParent(ControllerManager page){
        AppSession.CONTROLLER_MANAGER = page;
    }
    
}
