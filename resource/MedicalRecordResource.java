/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.resource;

import com.coursework.dao.MedicalRecordDAO;
import com.coursework.model.MedicalRecord;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import java.util.List;

@Path("/medical-records")
public class MedicalRecordResource {

    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordDAO.getAllMedicalRecords();
    }

    @GET
    @Path("/{recordId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecord(@PathParam("recordId") int id) {
        MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecord(id);
        if (medicalRecord != null) {
            return Response.ok(medicalRecord).build();
        } else {
            throw new WebApplicationException("Medical record with ID " + id + " not found", Response.Status.NOT_FOUND);
        }
    }

    @GET
    @Path("/patient/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicalRecord> getMedicalRecordsByPatientId(@PathParam("patientId") int id) {
        List<MedicalRecord> medicalRecords = medicalRecordDAO.getMedicalRecords(id);
        if (!medicalRecords.isEmpty()) {
            return medicalRecords;
        } else {
            throw new WebApplicationException("No medical records found for patient ID " + id, Response.Status.NOT_FOUND);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordDAO.addMedicalRecord(medicalRecord);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{recordId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateMedicalRecord(@PathParam("recordId") int id, MedicalRecord updatedMedicalRecord) {
        try {
            medicalRecordDAO.updateMedicalRecord(id, updatedMedicalRecord);
        } catch (NotFoundException e) {
            throw new WebApplicationException("Medical record with ID " + id + " not found", Response.Status.NOT_FOUND);
        }
    }

    @DELETE
    @Path("/{recordId}")
    public void deleteMedicalRecord(@PathParam("recordId") int id) {
        try {
            medicalRecordDAO.deleteMedicalRecord(id);
        } catch (NotFoundException e) {
            throw new WebApplicationException("Medical record with ID " + id + " not found", Response.Status.NOT_FOUND);
        }
    }
}
