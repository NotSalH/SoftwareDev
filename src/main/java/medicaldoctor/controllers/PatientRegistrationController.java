package medicaldoctor.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import medicaldoctor.backend.PatientService;
import medicaldoctor.backend.data.PatientRegistrationRequest;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.Patient;
import medicaldoctor.entities.PatientPrescription;
import medicaldoctor.entities.PatientVisit;
import medicaldoctor.entities.User;

public class PatientRegistrationController implements Initializable {

    @FXML
    private TitledPane newPatientTiltedPane, returningPatientTiltedPane;

    @FXML
    private TextField textFirstName, textLastName, ageNumber, textSex, textSocial;

    @FXML
    private TextField textMedicalInsurance, textBirthdate, textPrimaryDoctory;

    @FXML
    private TextField textStreetMailingAddress, textCityMailingAddress;

    @FXML
    private ChoiceBox stateMailingAddressChoiceBox, stateBillingAddressChoiceBox, doctor_dropdown, prime_doctor_dropdown;

    @FXML
    private TextField zipCodeMailingAddressNumber, textStreetBillingAddress, textCityBillingAddress, zipCodeBillingAddressNumber, textDoctor, textVisitDate;

    @FXML
    private TextArea textAreaChiefComplaint, textAreaPresentIllness;

    @FXML
    private Label first_name_id, last_id, age_id, sex_id, med_id, dob_id, prime_doctor_id, social_label;

    @FXML
    private Label mail_street_id, mail_city_id, mail_state_id, mail_zip_id;

    @FXML
    private Label bill_street_id, bill_city_id, bill_state_id, bill_zip_id;

    @FXML
    private Label doctor_id, visit_id, cheif_id, cheig_id, pres_id, success_label;

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
    
    //PatientVisit visit = new PatientVisit();
    //PatientPrescription prescription = new PatientPrescription();
           
    private boolean isFieldsEmpty(Map<TextField, Label> map){
        int flag = 0;
        for(Map.Entry<TextField, Label> entry : map.entrySet()){
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
    @FXML
    void submitButtonClick(ActionEvent event) throws Exception{
        if(isFieldsEmpty(textfeild_hash) &
           checkFields(textAreaChiefComplaint, cheif_id) &
           checkFields(textAreaPresentIllness, pres_id) &
           checkFields(stateMailingAddressChoiceBox, mail_state_id) &
           checkFields(stateBillingAddressChoiceBox, bill_state_id) &
           checkFields(doctor_dropdown, doctor_id) &
           checkFields(prime_doctor_dropdown, prime_doctor_id)){
           makePatient();
        }
    }
    
   private void makePatient() throws Exception{
        PatientRegistrationRequest request = new PatientRegistrationRequest();
        request.firstName = textFirstName.getText();
        request.lastName = textLastName.getText() ;
        request.age = Integer.parseInt(isInteger(ageNumber.getText()));
        request.sex =textSex.getText();
        try{
            request.dateOfBirth = LocalDate.parse(textBirthdate.getText());
            if (request.dateOfBirth == null){
                throw new Exception();
            }
        } catch (Exception e){
            success_label.setText("Birth Date is not in a correct format.");
            return;
        }
        request.medicalInsurance = (textMedicalInsurance.getText());
        request.addressStreet =(textStreetMailingAddress.getText());
        request.addressCity = (textCityMailingAddress.getText());
        request.addressState =((String) stateMailingAddressChoiceBox.getValue());
        request.addressZipCode = (zipCodeMailingAddressNumber.getText());
        request.billingAddressStreet = (textStreetBillingAddress.getText());
        request.billingAddressCity = (textCityBillingAddress.getText());
        request.billingAddressState = ((String) stateBillingAddressChoiceBox.getValue());
        request.billingAddressZipCode = (zipCodeBillingAddressNumber.getText());
        request.primaryDoctor = (user_hash.get((String) prime_doctor_dropdown.getValue()));
        request.socialSecurityNumber = textSocial.getText();
        request.doctor = (user_hash.get((String) doctor_dropdown.getValue()));
        request.visitDateTime = LocalDateTime.now();
        request.chiefComplaint = textAreaChiefComplaint.getText();
        request.presentIllness = textAreaPresentIllness.getText();
        PatientService.registerNewPatient(request);
        success_label.setText("Success!");
    }
   
    @FXML
    void selectButtonClick(ActionEvent event) {
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

    boolean checkFields(ChoiceBox cb, Label label) {
        if (cb.getSelectionModel().isEmpty()) {
            label.setTextFill(Color.RED);
            return false;
        } else {
            label.setTextFill(Color.GREEN);
            return true;
        }

    }

    boolean checkFields(TextArea ta, Label label) {
        if (ta.getText().isEmpty()) {
            label.setTextFill(Color.GREEN);
            return false;
        } else {
            label.setTextFill(Color.BLACK);
            return true;
        }
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        success_label.setText("");
        
        stateMailingAddressChoiceBox.getItems().addAll(LookUp.STATES);
        stateBillingAddressChoiceBox.getItems().addAll(LookUp.STATES);
      
        List<User> user;
        try (DatabaseScope scope = new DatabaseScope()) {
            user = User.getAll();
        } catch (Exception ex) {
            user = new ArrayList<>();
        }

        for (int i = 0; i < user.size(); i++) {
            if (user.get(i).getType().getDashboardName().equals("DoctorDashboard")) {
                doctor_dropdown.getItems().add(user.get(i).getFullName());
                user_hash.put(user.get(i).getFullName(), user.get(i));
                prime_doctor_dropdown.getItems().add(user.get(i).getFullName());
            }
        }

        textfeild_hash.put(textFirstName, first_name_id);
        textfeild_hash.put(textLastName, last_id);
        textfeild_hash.put(ageNumber, age_id);
        textfeild_hash.put(textSex, sex_id);
        textfeild_hash.put(textMedicalInsurance, med_id);
        textfeild_hash.put(textBirthdate, dob_id);
        textfeild_hash.put(textSocial, social_label);
        textfeild_hash.put(textStreetMailingAddress, mail_street_id);
        textfeild_hash.put(textCityMailingAddress, mail_city_id);
        textfeild_hash.put(zipCodeMailingAddressNumber, mail_zip_id);
        textfeild_hash.put(textStreetBillingAddress, bill_street_id);
        textfeild_hash.put(textCityBillingAddress, bill_city_id);
        textfeild_hash.put(zipCodeBillingAddressNumber, bill_zip_id);
    }
    
}
