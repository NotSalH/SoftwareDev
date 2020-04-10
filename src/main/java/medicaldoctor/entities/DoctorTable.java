/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicaldoctor.entities;

/**
 *
 * @author Salmanhussain
 */
public class DoctorTable {
    private Patient patient;
    private User user;
    
    public DoctorTable(Patient patient, User user){
        this.patient = patient;
        this.user = user;
    }
    
    public String getPatientName(){
        return patient.getName();
    }
    
    public String getDoctorName(){
        return user.getFirstName() + " " + user.getLastName();
    }
    
    
}
