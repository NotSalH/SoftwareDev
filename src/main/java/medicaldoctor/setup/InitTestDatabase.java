package medicaldoctor.setup;

import java.time.LocalDate;
import java.time.LocalDateTime;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.core.LabTestStatus;
import medicaldoctor.core.LabTestType;
import medicaldoctor.core.Permission;
import medicaldoctor.entities.Patient;
import medicaldoctor.entities.PatientLabRecord;
import medicaldoctor.entities.PatientPrescription;
import medicaldoctor.entities.PatientVisit;
import medicaldoctor.entities.User;
import medicaldoctor.entities.UserType;

/**
 * Setup all tables in the database, and start data from scratch with test
 * records.
 */
public final class InitTestDatabase {

    private InitTestDatabase() {
    }

    private static DatabaseScope s;

    public static void main(String[] args) throws Exception {
        try (DatabaseScope s_ = new DatabaseScope()) {
            s = s_;
            s.beginTransaction();
            deletePreviousData();
            updatePermissions();
            insertUserTypes();
            insertTestRecords();
            s.commit();
        } catch (Exception e) {
            DatabaseScope.rollback();
            throw e;
        } finally {
            DatabaseScope._shutdown();
        }
    }

    private static void deletePreviousData() {
        for (Class c : Entities.LIST) {
            s.runUpdate("DELETE FROM " + c.getSimpleName());
        }
    }

    private static void updatePermissions() {
        update(UserType.ADMIN, Permission.values());
        update(UserType.EXECUTIVE, Permission.values());
        update(UserType.STAFF,
                Permission.REGISTER_PATIENT,
                Permission.ACCESS_PATIENT_LOOKUP,
                Permission.ACCESS_DOCTOR_LOOKUP);
        update(UserType.NURSE,
                Permission.REGISTER_PATIENT,
                Permission.ACCESS_PATIENT_LOOKUP,
                Permission.ACCESS_DOCTOR_LOOKUP);
        update(UserType.DOCTOR,
                Permission.ACCESS_PATIENT_LOOKUP,
                Permission.ACCESS_DOCTOR_LOOKUP,
                Permission.ACCESS_MY_PATIENTS,
                Permission.ACCESS_MY_LAB_REQUESTS);
        update(UserType.HEMATOLOGIC_LAB_WORKER,
                Permission.VIEW_HEMOTOLOGIC_BUTTONS);
        update(UserType.RADIOLOGIC_LAB_WORKER,
                Permission.VIEW_RADIOLOGIC_BUTTONS);
    }

    private static void update(UserType userType, Permission... permissions) {
        for (Permission permission : permissions) {
            userType.addPermission(permission);
        }
    }

    private static void insertUserTypes() {
        UserType.ADMIN.save();
        UserType.EXECUTIVE.save();
        UserType.STAFF.save();
        UserType.NURSE.save();
        UserType.DOCTOR.save();
        UserType.RADIOLOGIC_LAB_WORKER.save();
        UserType.HEMATOLOGIC_LAB_WORKER.save();
    }

    private static void insertTestRecords() {
        User user;
        PatientVisit visit;
        PatientLabRecord labRecord;
        PatientPrescription prescription;

        user = new User();
        user.setFirstName("Network");
        user.setLastName("Admin");
        user.setUserName("admin");
        user.setPasswordHashAndSalt(AppSession.ENCRYPTION.hashPassword("password123"));
        user.setAdditionalPasswordHashAndSalt(AppSession.ENCRYPTION.hashPassword("secure@123"));
        user.setType(UserType.ADMIN);
        user.setDepartment("Administration");
        user.setOfficeNum(145);
        user.setEmail(AppSession.EMAIL_GENERATOR.generateEmail(user.getUserName()));
        user.save();
        
        user = new User();
        user.setFirstName("CEO");
        user.setLastName("Jung");
        user.setUserName("exec");
        user.setPasswordHashAndSalt(AppSession.ENCRYPTION.hashPassword("ceo"));
        user.setAdditionalPasswordHashAndSalt(AppSession.ENCRYPTION.hashPassword("money"));
        user.setType(UserType.EXECUTIVE);
        user.setDepartment("Administration");
        user.setOfficeNum(337);
        user.setEmail(AppSession.EMAIL_GENERATOR.generateEmail(user.getUserName()));
        user.save();
        
        user = new User();
        user.setFirstName("Corona");
        user.setLastName("Virus");
        user.setUserName("cvirus");
        user.setPasswordHashAndSalt(AppSession.ENCRYPTION.hashPassword("rip"));
        user.setType(UserType.NURSE);
        user.setDepartment("Nurses");
        user.setOfficeNum(154);
        user.setEmail(AppSession.EMAIL_GENERATOR.generateEmail(user.getUserName()));
        user.save();

        user = new User();
        user.setFirstName("Front");
        user.setLastName("Desk");
        user.setUserName("fdesk");
        user.setPasswordHashAndSalt(AppSession.ENCRYPTION.hashPassword("sitaround"));
        user.setType(UserType.STAFF);
        user.setDepartment("Lobby");
        user.setOfficeNum(1);
        user.setEmail(AppSession.EMAIL_GENERATOR.generateEmail(user.getUserName()));
        user.save();

        user = new User();
        user.setFirstName("Medicalus");
        user.setLastName("Doctorus");
        user.setUserName("mdoctorus");
        user.setPasswordHashAndSalt(AppSession.ENCRYPTION.hashPassword("ihaveMD"));
        user.setType(UserType.DOCTOR);
        user.setDepartment("Heart Department");
        user.setOfficeNum(545);
        user.setEmail(AppSession.EMAIL_GENERATOR.generateEmail(user.getUserName()));
        user.save();

        User doctor = user;
        user = new User();
        user.setFirstName("Vampire");
        user.setLastName("Bat");
        user.setUserName("vbat");
        user.setPasswordHashAndSalt(AppSession.ENCRYPTION.hashPassword("where_is_blood"));
        user.setType(UserType.HEMATOLOGIC_LAB_WORKER);
        user.setDepartment("Hemo Lab");
        user.setOfficeNum(222);
        user.setEmail(AppSession.EMAIL_GENERATOR.generateEmail(user.getUserName()));
        user.save();

        User hemoLabWorker = user;
        user = new User();
        user.setFirstName("Space");
        user.setLastName("Machine");
        user.setUserName("smachine");
        user.setPasswordHashAndSalt(AppSession.ENCRYPTION.hashPassword("radiowaves"));
        user.setType(UserType.RADIOLOGIC_LAB_WORKER);
        user.setDepartment("Radio Lab");
        user.setOfficeNum(234);
        user.setEmail(AppSession.EMAIL_GENERATOR.generateEmail(user.getUserName()));
        user.save();
        User radioLabWorker = user;

        visit = new PatientVisit();

        labRecord = new PatientLabRecord();
        labRecord.setLabWorker(radioLabWorker);
        labRecord.setRequester(doctor);
        labRecord.setLabTestType(LabTestType.CT);
        labRecord.setLabTestStatus(LabTestStatus.COMPLETE);
        labRecord.setResult("see tumor");
        labRecord.setRequestDateTime(LocalDateTime.of(2020, 4, 1, 16, 52, 22));
        labRecord.setResultDateTime(LocalDateTime.of(2020, 4, 2, 15, 12, 3));
        visit.addLabRecord(labRecord);

        labRecord = new PatientLabRecord();
        labRecord.setLabWorker(hemoLabWorker);
        labRecord.setRequester(doctor);
        labRecord.setLabTestType(LabTestType.RED_BLOOD_CELL);
        labRecord.setLabTestStatus(LabTestStatus.PENDING);
        labRecord.setRequestDateTime(LocalDateTime.of(2020, 4, 1, 16, 55, 43));
        visit.addLabRecord(labRecord);

        prescription = new PatientPrescription();
        prescription.setPrescription("10mg some kind of opiod for pain");
        prescription.setInstructions("take 1 pill a day for the rest of your life");
        prescription.getPharmacy().setStreet("123 Medicine Road");
        prescription.getPharmacy().setCity("HelpTown");
        prescription.getPharmacy().setState("CT");
        prescription.getPharmacy().setZipCode("02573");
        prescription.getPharmacy().setEmail("stuff@pharmacy.com");
        prescription.getPharmacy().setPhone("111-222-3333");
        prescription.getPharmacy().setFax("444-555-6666");
        visit.addPrescription(prescription);

        visit.setDoctor(doctor);
        visit.setVisitDateTime(LocalDateTime.of(2020, 4, 1, 10, 3, 22));
        visit.setChiefComplaint("heart problems");
        visit.setPresentIllness("nothing present that we are aware of.");
        visit.setSymptoms("irregular heartbeat, high blood pressure");
        visit.setPhysicalExamNotes("blood pressure is like 9001, heartbeat is like 90bpm then 50bpm then 256 bpm");
        visit.setDiagnosis("aorta cancer");
        visit.setImpression("looks really bad: cancer?");
        visit.setAdditionalNotes("need to do lab tests");
        visit.save();

        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setAge(22);
        patient.setSex("M");
        patient.setDateOfBirth(LocalDate.of(1997, 10, 1));
        patient.setMedicalInsurance("Really good insurance");
        patient.setAddressStreet("999 Patient Lane");
        patient.setAddressCity("West Sickford");
        patient.setAddressState("MA");
        patient.setAddressZipCode("57428");
        patient.setBillingAddressStreet("999 Billing Lane");
        patient.setBillingAddressCity("West Billingsford");
        patient.setBillingAddressState("MA");
        patient.setBillingAddressZipCode("57777");
        patient.setPrimaryDoctor(doctor);
        patient.setSocialSecurityNumber("123-56-7890");
        patient.addVisit(visit);
        patient.save();
    }

}
