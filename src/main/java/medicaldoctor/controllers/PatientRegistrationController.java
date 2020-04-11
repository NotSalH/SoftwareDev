/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import medicaldoctor.controllers.ControllerManager;
import medicaldoctor.controllers.LookUp;
import medicaldoctor.controllers.ParentController;
import medicaldoctor.core.AppSession;

public class PatientRegistrationController implements Initializable, ParentController{
    
    @FXML
    TitledPane newPatientTiltedPane, returningPatientTiltedPane;
    
    @FXML
    TextField textFirstName, textLastName, ageNumber, textSex;
    
    @FXML
    TextField textMedicalInsurance, textBirthdate, textPrimaryDoctory;
    
    /*@FXML
    TextField textStreetMailingAddress, textCityMailingAddress;
    
    @FXML
    ChoiceBox stateMailingAddressChoiceBox, stateBillingAddressChoiceBox;
    
    @FXML
    TextField zipCodeMailingAddressNumber, textStreetBillingAddress, textCityBillingAddress, zipCodeBillingAddressNumber;
    
    @FXML
    TextField textDoctor, textVisitDate, textAreaChiefComplaint, textAreaPresentIllness;*/
    
    @FXML
    void submitButtonClick(ActionEvent event){
        
    }
    
    @FXML
    void selectButtonClick(ActionEvent event){
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        /*for(String i : LookUp.STATES){
            stateMailingAddressChoiceBox.getItems().add(i);
            stateBillingAddressChoiceBox.getItems().add(i);
            System.out.println(i);
        }*/
    }

    @Override
    public void setScreenParent(ControllerManager page){
        AppSession.CONTROLLER_MANAGER = page;
    }
    
}
