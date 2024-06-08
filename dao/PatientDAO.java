package com.coursework.dao;

import com.coursework.model.Patient;
import com.coursework.model.Person;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class PatientDAO extends PersonDAO {

    private static List<Patient> patients = new ArrayList<>();
    
     static {
        patients.add(new Patient(1, "Hashi Jayasekara", "0760883400", "Galle", "Cavities", "Stable"));
        patients.add(new Patient(2, "Dasuni De Silva", "070524890", "Maradana", "Skin Cancer", "No major threat"));
        // Add more patients as needed
    }
     
    public static List<Patient> getAllPatients() {
        return patients;
    }
    
     public Patient getPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getID() == id) {
                return patient;
            }
        }
        return null;
    }

    // Create
    public void addPatient(Patient patient) {
        int newpatientId = getNextPatientId();
        patient.setID(newpatientId);
        addPerson(patient);
        patients.add(patient);
    }

   
    
    public void updatePatient(Patient updatedPatient) {
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            if (patient.getID() == updatedPatient.getID()) {
                patients.set(i, updatedPatient);
                System.out.println("Patient updated: " + updatedPatient);
                return;
            }
        }
    }

    // Delete
    public void deletePatient(int id) throws NotFoundException {
        patients.removeIf(patientt -> patientt.getID() == id);
    }
    
     public int getNextPatientId() {
         
        int maxpatientId = Integer.MIN_VALUE;

        for (Patient patient : patients) {
            int userId = patient.getID();
            if (userId > maxpatientId) {
                maxpatientId = userId;
            }
        }

        return maxpatientId + 1;
    }
}
