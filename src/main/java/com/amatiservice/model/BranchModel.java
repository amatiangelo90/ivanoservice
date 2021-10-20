package com.amatiservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BranchModel implements Serializable {

    @JsonProperty("pkBranchId")
    long pkBranchId;
    @JsonProperty("name")
    String name;
    @JsonProperty("email")
    String eMail;
    @JsonProperty("address")
    String address;
    @JsonProperty("phone")
    String phone;
    @JsonProperty("vatNumber")
    String vatNumber;
    @JsonProperty("provider")
    ProviderFatture provider;
    @JsonProperty("idKeyUser")
    String idKeyUser;
    @JsonProperty("idUidPassword")
    String idUidPassword;
    @JsonProperty("fkUserId")
    long fkUserId;

    public BranchModel() {
    }

    public BranchModel(long pkBranchId, String name, String eMail, String vatNumber, String address, String phone, ProviderFatture provider, String idKeyUser, String idUidPassword, long fkUserId) {
        this.pkBranchId = pkBranchId;
        this.name = name;
        this.eMail = eMail;
        this.vatNumber = vatNumber;
        this.address = address;
        this.phone = phone;
        this.provider = provider;
        this.idKeyUser = idKeyUser;
        this.idUidPassword = idUidPassword;
        this.fkUserId = fkUserId;
    }

    public long getPkBranchId() {
        return pkBranchId;
    }

    public void setPkBranchId(long pkBranchId) {
        this.pkBranchId = pkBranchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ProviderFatture getProvider() {
        return provider;
    }

    public void setProvider(ProviderFatture provider) {
        this.provider = provider;
    }

    public String getIdKeyUser() {
        return idKeyUser;
    }

    public void setIdKeyUser(String idKeyUser) {
        this.idKeyUser = idKeyUser;
    }

    public String getIdUidPassword() {
        return idUidPassword;
    }

    public void setIdUidPassword(String idUidPassword) {
        this.idUidPassword = idUidPassword;
    }

    public long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(long fkUserId) {
        this.fkUserId = fkUserId;
    }
}

