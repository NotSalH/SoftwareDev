package medicaldoctor.backend;

import medicaldoctor.backend.data.PatientRegistrationRequest;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.Patient;
import medicaldoctor.entities.PatientVisit;

public class PatientService {

    private PatientService() {
    }

    public static final String MESSAGE_REGISTER_NEW_PATIENT = "Registered new patient '%s'.";
    public static final String MESSAGE_REGISTER_RETURNING_PATIENT = "Registered returning patient '%s'.";
    public static final String MESSAGE_CREATED_NEW_VISIT = "Created new visit '%s' for patient '%s'.";

    /**
     * Create a new patient record in the database and a new visit record.
     *
     * @param request
     * @throws Exception
     */
    public static void registerNewPatient(PatientRegistrationRequest request) throws Exception {
        fillDataAndCommit(new Patient(), request, true);
    }

    /**
     * Update the provided patient record and create a new visit record.
     *
     * @param patientId
     * @param request
     * @throws Exception
     */
    public static void registerReturningPatient(int patientId,
            PatientRegistrationRequest request) throws Exception {
        Patient patient = null;
        try (DatabaseScope t = new DatabaseScope()) {
            patient = Patient.byId(patientId);
        }
        fillDataAndCommit(patient, request, false);
    }

    private static void fillDataAndCommit(Patient patient,
            PatientRegistrationRequest request, boolean isNew) throws Exception {
        // for returning patient, these fields are autofilled from the patient itself, 
        // so we expect most of these to not change.
        patient.setFirstName(request.firstName);
        patient.setLastName(request.lastName);
        patient.setAge(request.age);
        patient.setSex(request.sex);
        patient.setDateOfBirth(request.dateOfBirth);
        patient.setMedicalInsurance(request.medicalInsurance);
        patient.setAddressStreet(request.addressStreet);
        patient.setAddressCity(request.addressCity);
        patient.setAddressState(request.addressState);
        patient.setAddressZipCode(request.addressZipCode);
        patient.setBillingAddressStreet(request.billingAddressStreet);
        patient.setBillingAddressCity(request.billingAddressCity);
        patient.setBillingAddressState(request.billingAddressState);
        patient.setBillingAddressZipCode(request.billingAddressZipCode);
        patient.setPrimaryDoctor(request.primaryDoctor);
        patient.setSocialSecurityNumber(request.socialSecurityNumber);

        PatientVisit visit = new PatientVisit();
        visit.setDoctor(request.doctor);
        visit.setVisitDateTime(request.visitDateTime);
        visit.setChiefComplaint(request.chiefComplaint);
        visit.setPresentIllness(request.presentIllness);
        patient.addVisit(visit);

        try (DatabaseScope t = new DatabaseScope()) {
            t.beginTransaction();
            patient.save();
            if (isNew) {
                AppSession.logEvent(String.format(MESSAGE_REGISTER_NEW_PATIENT,
                        patient.getFullName()));
            } else {
                AppSession.logEvent(String.format(MESSAGE_REGISTER_RETURNING_PATIENT,
                        patient.getFullName()));
            }
            AppSession.logEvent(String.format(MESSAGE_CREATED_NEW_VISIT,
                    visit.getVisitDateTime(),
                    patient.getFullName()));
            t.commit();
        } catch (Exception e) {
            DatabaseScope.rollback();
            throw e;
        }
    }

}
