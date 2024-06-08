/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.dao;

import com.coursework.model.MedicalRecord;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;
import com.coursework.model.Patient;

public class MedicalRecordDAO {
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();
    static List<Patient> allRecords = PatientDAO.getAllPatients();
    
    static {
    
    medicalRecords.add(new MedicalRecord(allRecords.get(0).getID(), 1, "Cavities", "Filling the Cavity", "None"));
    medicalRecords.add(new MedicalRecord(allRecords.get(1).getID(), 2, "Skin Cancer", "Surgery", "None"));
     
    }
    
      public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecords;
    }
      
      
    
    public MedicalRecord getMedicalRecord(int id) {
        int i =0;
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getPatientId() == id ) {
              
                return medicalRecord;  
            }
            i++;
        }
        return null;
    }
    
      public List<MedicalRecord> getMedicalRecords(int id) { // Return a list to hold all matching records
        List<MedicalRecord> matchingRecords = new ArrayList<>();
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getPatientId() == id) {
                matchingRecords.add(medicalRecord);
            }
        }
        return matchingRecords;
    }
    
   
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
        
    }

    

    // Update
    public void updateMedicalRecord(int id, MedicalRecord updatedMedicalRecord) throws NotFoundException {
        MedicalRecord medicalRecord = getMedicalRecord(id);
       
    }

    // Delete
    public void deleteMedicalRecord(int id) throws NotFoundException {
        MedicalRecord medicalRecord = getMedicalRecord(id);
        medicalRecords.remove(medicalRecord);
    }
}
