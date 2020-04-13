package medicaldoctor.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
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
import medicaldoctor.entities.Patient;
import medicaldoctor.entities.User;

public class PatientRegistrationController implements Initializable {

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
    void submitButtonClick(ActionEvent event) {
    }

    @FXML
    void selectButtonClick(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
