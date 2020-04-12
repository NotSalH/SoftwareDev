package medicaldoctor.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;
import medicaldoctor.core.AppSession;
import medicaldoctor.entities.Patient;

public class PatientRegistrationController implements Initializable, ParentController{
    
    @FXML
    TitledPane newPatientTiltedPane, returningPatientTiltedPane;
    
    @FXML
    TextField textFirstName, textLastName, ageNumber, textSex;
    
    @FXML
    TextField textMedicalInsurance, textBirthdate, textPrimaryDoctory;
    
    @FXML
    TextField textStreetMailingAddress, textCityMailingAddress;
    
    @FXML
    ChoiceBox stateMailingAddressChoiceBox, stateBillingAddressChoiceBox;
    
    @FXML
    TextField zipCodeMailingAddressNumber, textStreetBillingAddress, textCityBillingAddress, zipCodeBillingAddressNumber, textDoctor, textVisitDate;
    
    @FXML
    TextArea textAreaChiefComplaint, textAreaPresentIllness;
    
    @FXML
    Label first_name_id, last_id, age_id, sex_id, med_id, dob_id, prime_doctor_id;
    
    @FXML
    Label mail_street_id, mail_city_id, mail_state_id, mail_zip_id;
    
    @FXML
    Label bill_street_id, bill_city_id, bill_state_id, bill_zip_id;
    
    @FXML
    Label doctor_id, visit_id, cheif_id, cheig_id, pres_id;
    
    HashMap<TextField, Label> textfeild_hash = new HashMap<>();
    
    int mask = 0b11111;
    
    @FXML
    void submitButtonClick(ActionEvent event){
        if(isFieldsEmpty(textfeild_hash)){ mask &=11110;}
        if(checkFields(textAreaChiefComplaint, cheif_id)){mask &=11101;}
        if(checkFields(textAreaPresentIllness, pres_id)){mask &=11011;}
        if(checkFields(stateMailingAddressChoiceBox, mail_state_id)){mask &=10111;}
        if(checkFields(stateBillingAddressChoiceBox, bill_state_id)){mask &=01111;}
        if(mask == 0){
            Patient patient = new Patient();
            makePatient(patient);
        }
        else{
            mask = 0b11111;
        }
    }
    
    void makePatient(Patient patient){
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
        patient.setPrimaryDoctor(null);
        patient.setSocialSecurityNumber("123-56-7890");
        patient.addVisit(null);
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
               entry.getValue().setTextFill(Color.BLACK); 
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
            label.setTextFill(Color.BLACK);
            return true;
        }
  
    }
    
    boolean checkFields(TextArea ta, Label label){
        if(ta.getText().isEmpty()){
            label.setTextFill(Color.RED);
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
        textfeild_hash.put(textDoctor,doctor_id);
        textfeild_hash.put(textVisitDate, visit_id);
    }

    @Override
    public void setScreenParent(ControllerManager page){
        AppSession.CONTROLLER_MANAGER = page;
    }
    
}
