/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.resource;

import com.coursework.dao.PersonDAO;
import com.coursework.model.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.NotFoundException;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {
    private final PersonDAO personDAO = new PersonDAO();

    @POST
    public Response addPerson(Person person) {
        personDAO.addPerson(person);
        return Response.status(Response.Status.CREATED).entity("Person added").build();
    }

    @GET
    @Path("/{id}")
    public Response getPerson(@PathParam("id") int id) {
        try {
            Person person = personDAO.getPerson(id);
            return Response.ok(person).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") int id, Person updatedPerson) {
        try {
            personDAO.updatePerson(id, updatedPerson);
            return Response.ok().entity("Person updated").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        try {
            personDAO.deletePerson(id);
            return Response.ok().entity("Person deleted").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}

