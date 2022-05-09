package com.dam.gestionalmacendam.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class Supplier {
    private String SIC = UUID.randomUUID().toString();
    private StringProperty nameSupplier;
    private StringProperty direction;
    private StringProperty telephoneNumber;
    private StringProperty email;

    public Supplier(){
        this(null, null, null, null);
    }

    public Supplier(String nameSupplier, String direction, String telephonNumber, String email){
        this.SIC = SIC;
        this.nameSupplier = new SimpleStringProperty(nameSupplier);
        this.direction = new SimpleStringProperty(direction);
        this.telephoneNumber = new SimpleStringProperty(telephonNumber);
        this.email = new SimpleStringProperty(email);
    }

    //En este constructor se agrega el SIC
    public Supplier(String SIC, String nameSupplier, String direction, String telephonNumber, String email){
        this.SIC = SIC;
        this.nameSupplier = new SimpleStringProperty(nameSupplier);
        this.direction = new SimpleStringProperty(direction);
        this.telephoneNumber = new SimpleStringProperty(telephonNumber);
        this.email = new SimpleStringProperty(email);
    }

    public String getSIC() {
        return SIC;
    }

    public String getNameSupplier() {
        return nameSupplier.get();
    }

    public StringProperty nameSupplierProperty() {
        return nameSupplier;
    }

    public void setNameSupplier(String nameSupplier) {
        this.nameSupplier.set(nameSupplier);
    }

    public String getDirection() {
        return direction.get();
    }

    public StringProperty directionProperty() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction.set(direction);
    }

    public String getTelephoneNumber() {
        return telephoneNumber.get();
    }

    public StringProperty telephoneNumberProperty() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber.set(telephoneNumber);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "SIC=" + SIC +
                ", nameSupplier='" + nameSupplier + '\'' +
                ", direction='" + direction + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
