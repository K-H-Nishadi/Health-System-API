/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.model;


public class MedicalRecord {
    
   private int id;
   private int patient;
   private String diagnoses;
   private String treatments;
   private String otherData;
   
   public MedicalRecord(){
   }

    public MedicalRecord(int patient, int id,  String diagnoses, String treatment, String otherData) {
        this.id = id;
        this.patient = patient;
        this.diagnoses = diagnoses;
        this.treatments = treatment;
        this.otherData = otherData;
      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }
    
    public int getPatientId() {
        return getPatient(); // Simply return the patient ID using the existing getPatient() method.
    }
    
    public String getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }
    
    public String getTreatments() {
        return treatments;
    }

    public void settreatments(String treatments) {
        this.treatments = treatments;
    }
    
    public String getOtherData() {
        return otherData;
    }

    public void setOthetData(String otherData) {
        this.otherData = otherData;
    }
    
}
