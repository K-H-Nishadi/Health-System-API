package com.coursework.resource;

import com.coursework.dao.AppointmentDAO;
import com.coursework.model.Appointment;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/appointments")
public class AppointmentResource {

    private static final Logger LOGGER = Logger.getLogger(AppointmentResource.class.getName());
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentDAO.getAllAppointments();
            LOGGER.log(Level.INFO, "Retrieved all appointments successfully");
            return Response.ok().entity(appointments).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to fetch appointments", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch appointments. Please try again later.").build();
        }
    }

    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentById(@PathParam("appointmentId") int appointmentId) {
        try {
            Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
            if (appointment != null) {
                LOGGER.log(Level.INFO, "Retrieved appointment with ID: " + appointmentId + " successfully");
                return Response.ok().entity(appointment).build();
            } else {
                LOGGER.log(Level.INFO, "Appointment with ID: " + appointmentId + " not found");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Appointment with ID: " + appointmentId + " not found").build();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to fetch appointment with ID: " + appointmentId, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch appointment with ID: " + appointmentId + ". Please try again later.").build();
        }
    }

    @POST
    public Response addAppointment(Appointment appointment) {
        try {
            appointmentDAO.addAppointment(appointment);
            LOGGER.log(Level.INFO, "Added new appointment successfully");
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to add appointment", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to add appointment. Please try again later.").build();
        }
    }

    @PUT
    @Path("/{appointmentId}")
    public Response updateAppointment(@PathParam("appointmentId") int appointmentId, Appointment updatedAppointment) {
    try {
        if (appointmentId <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid appointment ID: " + appointmentId).build();
        }
        
        if (updatedAppointment == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Updated appointment details are missing").build();
        }
        
        Appointment existingAppointment = appointmentDAO.getAppointmentById(appointmentId);
        if (existingAppointment == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Appointment with ID: " + appointmentId + " not found").build();
        }
        
        updatedAppointment.setId(appointmentId);
        appointmentDAO.updateAppointment(updatedAppointment);
        
        LOGGER.log(Level.INFO, "Updated appointment with ID: " + appointmentId + " successfully");
        
        return Response.ok().build();
    } catch (Exception ex) {
        LOGGER.log(Level.SEVERE, "Failed to update appointment with ID: " + appointmentId, ex);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Failed to update appointment with ID: " + appointmentId + ". Please try again later.").build();
    }
}
    

    @DELETE
    @Path("/{appointmentId}")
    public Response deleteAppointment(@PathParam("appointmentId") int appointmentId) {
        try {
            appointmentDAO.deleteAppointment(appointmentId);
            LOGGER.log(Level.INFO, "Deleted appointment with ID: " + appointmentId + " successfully");
            return Response.ok().entity("Appointment with ID " + appointmentId + " successfully deleted.").build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to delete appointment with ID: " + appointmentId, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete appointment with ID: " + appointmentId + ". Please try again later.").build();
        }
    }
}
