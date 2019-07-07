package model;

import java.util.Date;

public class Person {

    private String personID;
    private String firstName;
    private String surName;
    private Date dateOfBirth;
    private String phone;
    private String email;
    private String pass;

    public Person(String personID, String firstName, String surName, Date dateOfBirth, String phone, String email, String pass) {
        this.personID = personID;
        this.firstName = firstName;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.pass = pass;
    }

    public String getPersonID() {
        return personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
