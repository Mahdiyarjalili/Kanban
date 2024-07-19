package com.kanban.kanban.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ContactFormEntity {
    public ContactFormEntity(String firstname, String lastname, String email, String number, String country, String description) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.number = number;
        this.country = country;
        this.description = description;
    }

    @Id
    @GeneratedValue
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String number;
    private String country;
    private String description;
}
