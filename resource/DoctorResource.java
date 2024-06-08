/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.resource;

import com.coursework.dao.DoctorDAO;
import com.coursework.model.Doctor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/doctors")

public class DoctorResource {
    
    private static final Logger LOGGER = Logger.getLogger(DoctorResource.class.getName());
    private DoctorDAO doctorDAO = new DoctorDAO();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors() {
          try {
            List<Doctor> doctors = DoctorDAO.getAllDoctors();
            LOGGER.log(Level.INFO, "Retrieved all patients successfully");
            return Response.ok().entity(doctors).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to fetch patients", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch patients. Please try again later.").build();
        }
    }

    @GET
    @Path("/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("Id") int id) {
        try {
            Doctor doctor = doctorDAO.getDoctorById(id);
            if (doctor != null) {
                return Response.ok(doctor).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found").build();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error getting doctor by ID", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error getting doctor").build();
        }
    }

    @POST
    public Response addDoctor(Doctor doctor) {
        try {
            doctorDAO.addDoctor(doctor);
            return Response.status(Response.Status.CREATED).entity("Doctor added successfully.").build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error adding doctor", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding doctor").build();
        }
    }

    @PUT
    @Path("/{doctorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("doctorId") int doctorId, Doctor updatedDoctor) {
        try {
            Doctor existingDoctor = doctorDAO.getDoctorById(doctorId);
            if (existingDoctor != null) {
                updatedDoctor.setID(doctorId);
                doctorDAO.updateDoctor(updatedDoctor);
                return Response.ok("Doctor updated successfully").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found").build();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating doctor", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating doctor").build();
        }
    }

    @DELETE
    @Path("/{Id}")
    public Response deleteDoctor(@PathParam("Id") int id) {
        try {
            Doctor existingDoctor = doctorDAO.getDoctorById(id);
            if (existingDoctor != null) {
                doctorDAO.deleteDoctor(id);
                LOGGER.log(Level.INFO, "Deleted doctor with ID {0} successfully", id);
                return Response.ok().entity("Doctor with ID " + id + " successfully deleted.").build();
            } else {
                LOGGER.log(Level.WARNING, "Doctor with ID {0} not found", id);
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Doctor with ID " + id + " not found.").build();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting doctor", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting doctor").build();
        }
    }
}

