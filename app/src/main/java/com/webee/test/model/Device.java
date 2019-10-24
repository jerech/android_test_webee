package com.webee.test.model;

import java.io.Serializable;
import java.util.Date;

public class Device implements Serializable {

    private int id;

    private String macAddress;

    private String name;

    private Date admissionDate;

    public Device(int id, String macAddress, String name, Date admissionDate) {
        this.id = id;
        this.macAddress = macAddress;
        this.name = name;
        this.admissionDate = admissionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }
}
