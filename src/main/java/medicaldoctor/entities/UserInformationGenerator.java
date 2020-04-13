package medicaldoctor.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class UserInformationGenerator {

    public UserInformationGenerator(){
    
    }
    
    public String getUserName(String first_name, String last_name){
        return first_name.substring(0,1) + last_name.substring(1, last_name.length());
    }
    
    public String getEmail(String first_name, String last_name){
        return first_name.substring(0,1) + last_name.substring(1, last_name.length()) + "@MCare.com";
    }
    
    public String getUserID(String first_name, String last_name, String id){
        int value = (first_name.substring(0,1) + last_name.substring(1, last_name.length())).hashCode();
        return ((value & 0xFF) >> 0x01) + "";
    }
    
    public String genPass(){
        Calendar c = Calendar.getInstance();
        long i = c.getTimeInMillis() * 100;
        int r = c.getTimeZone().getDSTSavings();
        int j = c.getWeekYear();
        int a = c.get(Calendar.DATE);
        Random rand = new Random();
        byte[] ran = new byte[7];
        rand.nextBytes(ran);
        return (i * (j * a) * rand.nextInt(10)) + (int)(r * rand.nextGaussian() * i) + ran.toString();
    }
    
    public UserType getType(String value){
        switch (value) {
            case "Doctor":
                return UserType.DOCTOR;
            case "Nurse":
                return UserType.NURSE;
            case "Admin":
                return UserType.ADMIN;
            case "Executive":
                return UserType.EXECUTIVE;
            case "LabWorker":
                return UserType.HEMATOLOGIC_LAB_WORKER;
            default:
                return UserType.STAFF;
        }
    }
}
