package com.dam.gestionalmacendam.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data

@AllArgsConstructor
public class Order {
    private StringProperty OIC = new SimpleStringProperty(UUID.randomUUID().toString()) ;
    private StringProperty customerCIC;
    private DoubleProperty price;
    private ObjectProperty<Pay> methodPay;


    public Order(StringProperty customerCIC, DoubleProperty price, ObjectProperty<Pay> methodPay) {
        this.customerCIC = customerCIC;
        this.price = price;
        this.methodPay = methodPay;
    }

    @Override
    public String toString() {
        return "Order{" +
                "OIC=" + OIC +
                ", customer=" + customerCIC +
                ", price=" + price +
                ", methodPay=" + methodPay +
                '}';
    }
}
