package medicaldoctor.setup;

import medicaldoctor.entities.LogRecord;
import medicaldoctor.entities.Patient;
import medicaldoctor.entities.PatientLabRecord;
import medicaldoctor.entities.PatientPrescription;
import medicaldoctor.entities.PatientVisit;
import medicaldoctor.entities.PharmacyDetails;
import medicaldoctor.entities.RecentView;
import medicaldoctor.entities.User;
import medicaldoctor.entities.UserType;
import medicaldoctor.entities.UserTypePermission;

public class Entities {

    private Entities() {
    }

    // sort so dependent classes are deleted before depended on classes
    public static Class[] LIST = new Class[]{
        LogRecord.class, RecentView.class, Patient.class,
        PatientLabRecord.class, PatientPrescription.class,
        PatientVisit.class, PharmacyDetails.class,
        User.class, UserTypePermission.class, UserType.class};

    public static void main(String[] args) {
        for (Class c : LIST) {
            System.out.println(c.getSimpleName());
        }
    }

}
