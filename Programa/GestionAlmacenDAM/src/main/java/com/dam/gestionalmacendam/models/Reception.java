package com.dam.gestionalmacendam.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data

@AllArgsConstructor
public class Reception {
    private StringProperty RIC;
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
        this.RIC = new SimpleStringProperty(ric);
        this.supplierSIC = new SimpleStringProperty(supplierSIC);
        this.carrier = new SimpleStringProperty(carrier);
        this.cost = new SimpleDoubleProperty(cost);
    }
    public Reception(String supplierSIC, String carrier, Double cost) {
        this.RIC = new SimpleStringProperty(UUID.randomUUID().toString());
        this.supplierSIC = new SimpleStringProperty(supplierSIC);
        this.carrier = new SimpleStringProperty(carrier);
        this.cost = new SimpleDoubleProperty(cost);
    }

}
