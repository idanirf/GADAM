package com.dam.gestionalmacendam.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data

@AllArgsConstructor
public class Reception {
    private StringProperty  RIC = new SimpleStringProperty(UUID.randomUUID().toString());
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

    public Reception(StringProperty supplierSIC, StringProperty carrier, DoubleProperty cost) {
        this.supplierSIC = supplierSIC;
        this.carrier = carrier;
        this.cost = cost;
    }
}
