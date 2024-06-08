package com.coursework.dao;

import com.coursework.model.Doctor;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO extends PersonDAO {

    private static List<Doctor> doctors = new ArrayList<>();

    static {
        doctors.add(new Doctor(1, "Devi Sherma", "0912345670", "Nugegoda", "Dentist"));
        doctors.add(new Doctor(2, "Julia Roberts", "0743546700", "Dehiwala", "Dermatologist"));
        // Add more doctors as needed
    }

    public static List<Doctor> getAllDoctors() {
        return doctors;
    }

    // Read
    public Doctor getDoctorById(int id) throws NotFoundException {
        for (Doctor doctor : doctors) {
            if (doctor.getID() == id) {
                return doctor;
            }
        }
        throw new NotFoundException("Doctor not found with ID: " + id);
    }

        // Create
    public void addDoctor(Doctor doctor) {
        int newDoctorId = getNextDoctorId();
        doctor.setID(newDoctorId);
        addPerson(doctor);
        doctors.add(doctor);
    }
    
    
    public void updateDoctor(Doctor updatedDoctor) {
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            if (doctor.getID() == updatedDoctor.getID()) {
                doctors.set(i, updatedDoctor);
                System.out.println("Doctor updated: " + updatedDoctor);
                return;
            }
        }
    }


    
    public void deleteDoctor(int id) throws NotFoundException {
        doctors.removeIf(doctor1 -> doctor1.getID() == id);
    }
    
     public int getNextDoctorId() {

        int maxDoctorId = Integer.MIN_VALUE;

        for (Doctor doctor : doctors) {
            int userId = doctor.getID();
            if (userId > maxDoctorId) {
                maxDoctorId = userId;
            }
        }
        return maxDoctorId + 1;
    }
     
}
