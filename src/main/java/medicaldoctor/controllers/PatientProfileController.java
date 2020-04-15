package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import medicaldoctor.core.AppSession;

public class PatientProfileController implements Initializable{

    @FXML
    private TextField textFirstName, textLastName, ageNumber, textSex;

    @FXML
    private TextField textMedicalInsurance, textBirthdate, textPrimaryDoctory;

    @FXML
    private TextField textStreetMailingAddress, textCityMailingAddress,state_mailing,state_bill;


    @FXML
    private TextField zipCodeMailingAddressNumber, textStreetBillingAddress, textCityBillingAddress, zipCodeBillingAddressNumber, textDoctor, textVisitDate;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        if(AppSession.getPatientFlag() == 1){
            textFirstName.setText(AppSession.getPatientSelection().getFirstName());
            textLastName.setText(AppSession.getPatientSelection().getLastName());
            ageNumber.setText(AppSession.getPatientSelection().getAge() + "");
            textSex.setText(AppSession.getPatientSelection().getSex());
            textMedicalInsurance.setText(AppSession.getPatientSelection().getMedicalInsurance());
            textBirthdate.setText(AppSession.getPatientSelection().getDateOfBirth().toString());
            textPrimaryDoctory.setText(AppSession.getPatientSelection().getPrimaryDoctor().getFullName());
            textStreetMailingAddress.setText(AppSession.getPatientSelection().getAddressStreet());
            textCityMailingAddress.setText(AppSession.getPatientSelection().getAddressCity());
            zipCodeMailingAddressNumber.setText(AppSession.getPatientSelection().getAddressZipCode());
            textStreetBillingAddress.setText(AppSession.getPatientSelection().getBillingAddressStreet());
            textCityBillingAddress.setText(AppSession.getPatientSelection().getBillingAddressCity());
            zipCodeBillingAddressNumber.setText(AppSession.getPatientSelection().getBillingAddressZipCode());
            state_mailing.setText(AppSession.getPatientSelection().getAddressState());
            state_bill.setText(AppSession.getPatientSelection().getBillingAddressState());
        }
        AppSession.setPatientFlag(0);
    }
    
}
