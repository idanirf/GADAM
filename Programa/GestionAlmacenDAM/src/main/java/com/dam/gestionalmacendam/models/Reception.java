package com.dam.gestionalmacendam.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class Reception {
    private String RIC= UUID.randomUUID().toString();
    private StringProperty supplierSIC;
    private StringProperty carrier;
    private DoubleProperty cost;

    @Override
    public String toString() {
        return "Reception{" +
                "RIC=" + RIC +
                ", supplier=" + supplierSIC +
                ", carrier='" + carrier + '\'' +
                ", cost=" + cost +
                '}';
    }

    public Reception( String ric,String supplierSIC , String carrier, Double cost) {
        this.RIC=ric;
        this.supplierSIC = new SimpleStringProperty(supplierSIC);
        this.carrier = new SimpleStringProperty(carrier);
        this.cost = new SimpleDoubleProperty(cost);
    }
    public Reception(String supplierSIC, String carrier, Double cost) {
        this.supplierSIC = new SimpleStringProperty(supplierSIC);
        this.carrier = new SimpleStringProperty(carrier);
        this.cost = new SimpleDoubleProperty(cost);
    }

    public String getRIC() {
        return RIC;
    }

    public void setRIC(String RIC) {
        this.RIC = RIC;
    }

    public String getSupplierSIC() {
        return supplierSIC.get();
    }

    public StringProperty supplierSICProperty() {
        return supplierSIC;
    }

    public void setSupplierSIC(String supplierSIC) {
        this.supplierSIC.set(supplierSIC);
    }

    public String getCarrier() {
        return carrier.get();
    }

    public StringProperty carrierProperty() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier.set(carrier);
    }

    public double getCost() {
        return cost.get();
    }

    public DoubleProperty costProperty() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }
}
