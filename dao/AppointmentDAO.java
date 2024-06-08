package com.coursework.dao;

import com.coursework.model.Appointment;
import com.coursework.model.Doctor;
import com.coursework.model.Patient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;
import jdk.vm.ci.meta.Local;

public class AppointmentDAO {
    private static List<Appointment> appointments = new ArrayList<>();

    
    static {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String formattedDate = date.format(dateFormatter);
        String formattedTime = time.format(timeFormatter);
        
        List<Patient> allPatients = PatientDAO.getAllPatients();
        // Get the doctor objects from DoctorDAO
        List<Doctor> allDoctors = DoctorDAO.getAllDoctors();

        // Create appointments and add them to the list
        appointments.add(new Appointment(1, formattedDate, formattedTime, allPatients.get(0), allDoctors.get(0)));
        appointments.add(new Appointment(2, formattedDate, formattedTime, allPatients.get(1), allDoctors.get(1)));
        
        // Add more students as needed
    }
     public List<Appointment> getAllAppointments() {
        return appointments;
    }
     
     
      public Appointment getAppointmentById(int appointmentId) {
        
        for (Appointment appointment : appointments) {
            if (appointment.getId() == appointmentId) {
                return appointment;
            }
        }
        return null;
    }
    // Create
    public void addAppointment(Appointment appointment) {
        int newUserId = getNextAppointmentId();
        appointment.setId(newUserId);
        appointments.add(appointment);
    }

   

    // Update
    public void updateAppointment(int appointmentId, Appointment updatedAppointment) throws NotFoundException {
        Appointment appointment = getAppointmentById(appointmentId);
        // Update appointment attributes
        appointment.setDate(updatedAppointment.getDate());
        // You can add more update logic here if needed
    }
    
      public void updateAppointment(Appointment updatedAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getId() == updatedAppointment.getId()) {
                appointments.set(i, updatedAppointment);
                System.out.println("Appointment updated: " + updatedAppointment);
                return;
            }
        }
    }
      
      
    public void deleteAppointment(int appointmentId) throws NotFoundException {
        appointments.removeIf(appoin -> appoin.getId() == appointmentId);
    }
    
    public int getNextAppointmentId() {

        int maxUserId = Integer.MIN_VALUE;

        for (Appointment apointment : appointments) {
            int AppointmentId = apointment.getId();
            if (AppointmentId > maxUserId) {
                maxUserId = AppointmentId;
            }
        }
        return maxUserId + 1;
    }
}
