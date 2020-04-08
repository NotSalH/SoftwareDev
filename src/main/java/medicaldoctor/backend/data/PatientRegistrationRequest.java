package medicaldoctor.backend.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import medicaldoctor.entities.User;

public class PatientRegistrationRequest {

    public String firstName;
    public String lastName;
    public Integer age;
    public String sex;
    public String medicalInsurance;
    public LocalDate dateOfBirth;
    public User primaryDoctor;
    public String socialSecurityNumber;
    public String addressStreet;
    public String addressCity;
    public String addressState;
    public String addressZipCode;
    public String billingAddressStreet;
    public String billingAddressCity;
    public String billingAddressState;
    public String billingAddressZipCode;
    public User doctor;
    public LocalDateTime visitDateTime;
    public String chiefComplaint;
    public String presentIllness;

}
