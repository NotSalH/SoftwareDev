package medicaldoctor.tests.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import medicaldoctor.backend.PatientService;
import medicaldoctor.backend.data.PatientRegistrationRequest;
import medicaldoctor.entities.Patient;
import medicaldoctor.entities.PatientVisit;
import medicaldoctor.entities.User;
import medicaldoctor.utils.tests.FakeDatabase;
import medicaldoctor.utils.tests.FakeQueries;
import medicaldoctor.utils.tests.LoginScope;
import medicaldoctor.utils.tests.TestEntities;
import org.junit.Assert;
import org.junit.Test;

public class WhenRegisteringAPatient {

    // just wanna make sure nothing is missed :)
    private static final String FIRST_NAME = "Tester";
    private static final String LAST_NAME = "Jones";
    private static final Integer AGE = 27;
    private static final String SEX = "M";
    private static final String MEDICAL_INSURANCE = "test insurance";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.of(1993, 4, 5);
    private static final String PRIMARY_DOCTOR_USERNAME = "primary doctor";
    private static final String SOCIAL_SECURITY_NUMBER = "666-33-9999";
    private static final String ADDRESS_STREET = "524 iiiii street";
    private static final String ADDRESS_CITY = "i heard today someone put CT as the city";
    private static final String ADDRESS_STATE = "QQ";
    private static final String ADDRESS_ZIPCODE = "42111";
    private static final String BILLING_ADDRESS_STREET = "677 gimme money lane";
    private static final String BILLING_ADDRESS_CITY = "Bankville";
    private static final String BILLING_ADDRESS_STATE = "BA";
    private static final String BILLING_ADDRESS_ZIPCODE = "11124";
    private static final String DOCTOR_USERNAME = "visit doctor";
    private static final LocalDateTime VISIT_DATE_TIME = LocalDateTime.of(2005, 4, 8, 17, 13, 35);
    private static final String CHIEF_COMPLAINT = "too many unit tests";
    private static final String PRESENT_ILLNESS = "tons of properties to fill";

    private static final String UPDATED_ADDRESS = "44 even better street";
    private static final String RETURNING_DOCTOR_USERNAME = "old doctor was bad";
    private static final LocalDateTime RETURNING_VISIT_DATE_TIME = LocalDateTime.of(2008, 1, 3, 23, 34, 11);
    private static final String RETURNING_CHIEF_COMPLAINT = "getting tired of this";
    private static final String RETURNING_PRESENT_ILLNESS = "javafx";

    @Test
    public void shouldCreateNewPatientAndTwoVisitsIfRegisteringNewPatientThenRegisteringAgainAsReturningPatient() throws Exception {
        FakeDatabase db = new FakeDatabase(FakeQueries.PATIENT_BY_ID, 1);
        PatientRegistrationRequest request = new PatientRegistrationRequest();

        // username as a simple test that the doctor went through correctly
        User primaryDoctor = new User();
        primaryDoctor.setUserName(PRIMARY_DOCTOR_USERNAME);
        User visitDoctor = new User();
        visitDoctor.setUserName(DOCTOR_USERNAME);

        request.firstName = FIRST_NAME;
        request.lastName = LAST_NAME;
        request.age = AGE;
        request.sex = SEX;
        request.medicalInsurance = MEDICAL_INSURANCE;
        request.dateOfBirth = DATE_OF_BIRTH;
        request.primaryDoctor = primaryDoctor;
        request.socialSecurityNumber = SOCIAL_SECURITY_NUMBER;
        request.addressStreet = ADDRESS_STREET;
        request.addressCity = ADDRESS_CITY;
        request.addressState = ADDRESS_STATE;
        request.addressZipCode = ADDRESS_ZIPCODE;
        request.billingAddressStreet = BILLING_ADDRESS_STREET;
        request.billingAddressCity = BILLING_ADDRESS_CITY;
        request.billingAddressState = BILLING_ADDRESS_STATE;
        request.billingAddressZipCode = BILLING_ADDRESS_ZIPCODE;
        request.doctor = visitDoctor;
        request.visitDateTime = VISIT_DATE_TIME;
        request.chiefComplaint = CHIEF_COMPLAINT;
        request.presentIllness = PRESENT_ILLNESS;

        try (LoginScope s = new LoginScope(TestEntities.getStaffUser())) {
            PatientService.registerNewPatient(request);

            Patient savedPatient = (Patient) db.getSavedEntitiesOfType(Patient.class)[0];

            Assert.assertEquals(FIRST_NAME, savedPatient.getFirstName());
            Assert.assertEquals(LAST_NAME, savedPatient.getLastName());
            Assert.assertEquals(AGE, savedPatient.getAge());
            Assert.assertEquals(SEX, savedPatient.getSex());
            Assert.assertEquals(MEDICAL_INSURANCE, savedPatient.getMedicalInsurance());
            Assert.assertEquals(DATE_OF_BIRTH, savedPatient.getDateOfBirth());
            Assert.assertEquals(PRIMARY_DOCTOR_USERNAME, savedPatient.getPrimaryDoctor().getUserName());
            Assert.assertEquals(SOCIAL_SECURITY_NUMBER, savedPatient.getSocialSecurityNumber());
            Assert.assertEquals(ADDRESS_STREET, savedPatient.getAddressStreet());
            Assert.assertEquals(ADDRESS_CITY, savedPatient.getAddressCity());
            Assert.assertEquals(ADDRESS_STATE, savedPatient.getAddressState());
            Assert.assertEquals(ADDRESS_ZIPCODE, savedPatient.getAddressZipCode());
            Assert.assertEquals(BILLING_ADDRESS_STREET, savedPatient.getBillingAddressStreet());
            Assert.assertEquals(BILLING_ADDRESS_CITY, savedPatient.getBillingAddressCity());
            Assert.assertEquals(BILLING_ADDRESS_STATE, savedPatient.getBillingAddressState());
            Assert.assertEquals(BILLING_ADDRESS_ZIPCODE, savedPatient.getBillingAddressZipCode());

            PatientVisit patientVisit = savedPatient.getVisits().get(0);

            Assert.assertEquals(DOCTOR_USERNAME, patientVisit.getDoctor().getUserName());
            Assert.assertEquals(VISIT_DATE_TIME, patientVisit.getVisitDateTime());
            Assert.assertEquals(CHIEF_COMPLAINT, patientVisit.getChiefComplaint());
            Assert.assertEquals(PRESENT_ILLNESS, patientVisit.getPresentIllness());
            Assert.assertNull(patientVisit.getPhysicalExamNotes());

            visitDoctor = new User();
            visitDoctor.setUserName(RETURNING_DOCTOR_USERNAME);

            request.addressStreet = UPDATED_ADDRESS;
            request.doctor = visitDoctor;
            request.visitDateTime = RETURNING_VISIT_DATE_TIME;
            request.chiefComplaint = RETURNING_CHIEF_COMPLAINT;
            request.presentIllness = RETURNING_PRESENT_ILLNESS;

            PatientService.registerReturningPatient(savedPatient, request);

            savedPatient = (Patient) db.getSavedEntitiesOfType(Patient.class)[1];

            //one of each should be enough
            Assert.assertEquals(FIRST_NAME, savedPatient.getFirstName());
            Assert.assertEquals(UPDATED_ADDRESS, savedPatient.getAddressStreet());

            PatientVisit firstVisit = savedPatient.getVisits().get(0);

            Assert.assertEquals(DOCTOR_USERNAME, firstVisit.getDoctor().getUserName());
            Assert.assertEquals(VISIT_DATE_TIME, firstVisit.getVisitDateTime());
            Assert.assertEquals(CHIEF_COMPLAINT, firstVisit.getChiefComplaint());
            Assert.assertEquals(PRESENT_ILLNESS, firstVisit.getPresentIllness());

            PatientVisit secondVisit = savedPatient.getVisits().get(1);

            Assert.assertEquals(RETURNING_DOCTOR_USERNAME, secondVisit.getDoctor().getUserName());
            Assert.assertEquals(RETURNING_VISIT_DATE_TIME, secondVisit.getVisitDateTime());
            Assert.assertEquals(RETURNING_CHIEF_COMPLAINT, secondVisit.getChiefComplaint());
            Assert.assertEquals(RETURNING_PRESENT_ILLNESS, secondVisit.getPresentIllness());
            Assert.assertNull(secondVisit.getPhysicalExamNotes());

            Assert.assertEquals(4, db.getLoggedEventsCount());
        }
    }

}
