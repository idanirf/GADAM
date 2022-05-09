package com.dam.gestionalmacendam.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data

@AllArgsConstructor
public class LineOrder {
    private StringProperty  OLIC = new SimpleStringProperty(UUID.randomUUID().toString());
    private StringProperty article;
    private IntegerProperty load;
    private DoubleProperty unitPrice;
    private DoubleProperty totalPrice;
    private StringProperty belongsOrder;

    @Override
    public String toString() {
        return "LineOrder{" +
                "OLIC=" + OLIC +
                ", article=" + article +
                ", load=" + load +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", belongsOrder=" + belongsOrder +
                '}';
    }
}
