package com.dam.gestionalmacendam.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data

@AllArgsConstructor
public class LineReception {    // todo hecho
    private StringProperty RLIC = new SimpleStringProperty(UUID.randomUUID().toString());
    private StringProperty articlePIC;
    private IntegerProperty load;
    private DoubleProperty unitPrice;
    private DoubleProperty totalPrice;
    private StringProperty belongsRecepcion;

    public LineReception(StringProperty articlePIC, IntegerProperty load, DoubleProperty unitPrice,
                         DoubleProperty totalPrice, StringProperty belongsRecepcion) {
        this.articlePIC = articlePIC;
        this.load = load;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.belongsRecepcion = belongsRecepcion;
    }

    @Override
    public String toString() {
        return "LineReception{" +
                "RLIC=" + RLIC +
                ", articlePIC=" + articlePIC +
                ", load=" + load +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", belongsRecepcion=" + belongsRecepcion +
                '}';
    }
}
