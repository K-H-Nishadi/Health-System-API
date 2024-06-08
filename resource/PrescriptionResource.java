/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.resource;

import com.coursework.dao.PrescriptionDAO;
import com.coursework.model.Prescription;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/prescriptions")

public class PrescriptionResource {

    private static final Logger loggers = Logger.getLogger(PrescriptionResource.class.getName());
    
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prescription> getAllPrescriptions() {
        return prescriptionDAO.getAllPrescriptions();
    }
    
    
    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescription(@PathParam("patientId") int id) {
        try {
            Prescription prescription = prescriptionDAO.getPrescription(id);
            if (prescription != null) {
                loggers.log(Level.INFO, "Retrieved prescription successfully");
                return Response.ok().entity(prescription).build();
            } else {
                loggers.log(Level.INFO, "No prescription found for patient ID: ");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No prescription found for patient ID: " + id).build();
            }
        } catch (Exception ex) {
            loggers.log(Level.SEVERE, "Failed to fetch prescription for patient ID: " + id, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch prescription for patient ID: " + id + ". Please try again later.").build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescription) {
        try {
            prescriptionDAO.addPrescription(prescription);
            loggers.log(Level.INFO, "Added new prescription successfully");
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception ex) {
            loggers.log(Level.SEVERE, "Failed to add prescription", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to add prescription. Please try again later.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePrescription(@PathParam("id") int id) {
        try {
            Prescription prescription = prescriptionDAO.getPrescription(id);
            if (prescription != null) {
                prescriptionDAO.deletePrescription(id);
                loggers.log(Level.INFO, "Deleted prescription with ID: " + id + " successfully");
                return Response.ok().entity("Prescription with ID " + id + " deleted successfully.").build();
            } else {
                loggers.log(Level.INFO, "Prescription with ID " + id + " not found");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Prescription with ID " + id + " not found.").build();
            }
        } catch (Exception ex) {
            loggers.log(Level.SEVERE, "Failed to delete prescription with ID: " + id, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete prescription with ID: " + id + ". Please try again later.").build();
        }
    }
}

