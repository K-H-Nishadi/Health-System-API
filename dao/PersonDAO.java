/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.dao;

import com.coursework.model.Person;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class PersonDAO {
    private final List<Person> persons = new ArrayList<>();

    // Create
    public void addPerson(Person person) {
        persons.add(person);
    }
    

    // Read
    public Person getPerson(int id) throws NotFoundException {
        for (Person person : persons) {
            if (person.getID()== id) {
                return person;
            }
        }
        throw new NotFoundException("Person with Id " + id + " not found");
    }

    // Update
    public void updatePerson(int id, Person updatedPerson) throws NotFoundException {
        Person person = getPerson(id);
        // Update person attributes
        person.setName(updatedPerson.getName());
        person.setContactInformation(updatedPerson.getContactInformation());
        person.setAddress(updatedPerson.getAddress());
    }

    // Delete
    public void deletePerson(int id) throws NotFoundException {
        Person person = getPerson(id);
        persons.remove(person);
    }
}

