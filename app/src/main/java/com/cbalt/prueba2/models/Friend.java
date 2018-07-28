package com.cbalt.prueba2.models;

import com.orm.SugarRecord;

public class Friend extends SugarRecord {

    public String name, description, type, address;
    public int phoneNumber;

    public Friend() {
    }

    public Friend(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Friend(String name, String description, String type, String address, int phoneNumber) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
