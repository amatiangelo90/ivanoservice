package com.amatiservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Timestamp;

public class RecessedModel implements Serializable {

    @JsonProperty("pkRecessedId")
    int pkRecessedId;
    @JsonProperty("description")
    String description;
    @JsonProperty("amount")
    double amount;
    @JsonProperty("vat")
    int vat;
    @JsonProperty("dateTimeRecessed")
    Timestamp dateTimeRecessed;
    @JsonProperty("dateTimeRecessedInsert")
    Timestamp dateTimeRecessedInsert;
    @JsonProperty("fkBranchId")
    int fkBranchId;

    public RecessedModel() {
    }

    public RecessedModel(int pkRecessedId, String description, double amount, int vat, Timestamp dateTimeRecessed, Timestamp dateTimeRecessedInsert, int fkBranchId) {
        this.pkRecessedId = pkRecessedId;
        this.description = description;
        this.amount = amount;
        this.vat = vat;
        this.dateTimeRecessed = dateTimeRecessed;
        this.dateTimeRecessedInsert = dateTimeRecessedInsert;
        this.fkBranchId = fkBranchId;
    }

    public int getPkRecessedId() {
        return pkRecessedId;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public void setPkRecessedId(int pkRecessedId) {
        this.pkRecessedId = pkRecessedId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getDateTimeRecessed() {
        return dateTimeRecessed;
    }

    public void setDateTimeRecessed(Timestamp dateTimeRecessed) {
        this.dateTimeRecessed = dateTimeRecessed;
    }

    public Timestamp getDateTimeRecessedInsert() {
        return dateTimeRecessedInsert;
    }

    public void setDateTimeRecessedInsert(Timestamp dateTimeRecessedInsert) {
        this.dateTimeRecessedInsert = dateTimeRecessedInsert;
    }

    public int getFkBranchId() {
        return fkBranchId;
    }

    public void setFkBranchId(int fkBranchId) {
        this.fkBranchId = fkBranchId;
    }
}
