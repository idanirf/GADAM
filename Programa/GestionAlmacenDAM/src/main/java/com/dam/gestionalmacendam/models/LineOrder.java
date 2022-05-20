package com.dam.gestionalmacendam.models;

import javafx.beans.property.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data

@AllArgsConstructor
public class LineOrder {
    private StringProperty OLIC ;
    private StringProperty article;
    private IntegerProperty load;
    private DoubleProperty unitPrice;
    private DoubleProperty totalPrice;
    private StringProperty belongsOrder;

    public void setLoad(int load) {
        this.load.set(load);

    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice.set(unitPrice);
        this.totalPrice= new SimpleDoubleProperty((this.load.intValue())*(this.unitPrice.doubleValue()));

    }


    public LineOrder(String OLIC, String article, Integer load,
                     Double unitPrice,  String belongsOrder) {
        this.OLIC = new SimpleStringProperty(OLIC);
        this.article = new SimpleStringProperty(article);
        this.load = new SimpleIntegerProperty(load);
        this.unitPrice = new SimpleDoubleProperty(unitPrice);
        this.totalPrice = new SimpleDoubleProperty(unitPrice * load);
        this.belongsOrder = new SimpleStringProperty(belongsOrder);
    }

    public LineOrder( String article, Integer load,
                      Double unitPrice,  String belongsOrder) {
        this.OLIC = new SimpleStringProperty(UUID.randomUUID().toString());
        this.article = new SimpleStringProperty(article);
        this.load = new SimpleIntegerProperty(load);
        this.unitPrice = new SimpleDoubleProperty(unitPrice);
        this.totalPrice = new SimpleDoubleProperty(unitPrice * load);
        this.belongsOrder = new SimpleStringProperty(belongsOrder);
    }

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

    public DoubleProperty totalPriceProperty() {
        return totalPrice;
    }

    public StringProperty articleProperty() {
        return article;
    }

    public IntegerProperty loadProperty() {
        return load;
    }

    public DoubleProperty unitPriceProperty() {
        return unitPrice;
    }

    public StringProperty belongsOrderProperty() {
        return belongsOrder;
    }

    public StringProperty OLICProperty() {
        return OLIC;
    }

}

