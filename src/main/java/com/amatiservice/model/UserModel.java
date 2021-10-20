package com.amatiservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserModel implements Serializable {

    @JsonProperty("id")
    long id;
    @JsonProperty("name")
    String name;
    @JsonProperty("lastName")
    String lastName;
    @JsonProperty("phone")
    String phone;
    @JsonProperty("mail")
    String mail;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }



    public UserModel() {
    }

    public UserModel(int id, String name, String lastName, String phone, String mail) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.mail = mail;
    }
}
