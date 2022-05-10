package com.dam.gestionalmacendam.models;

import javafx.beans.property.*;
import lombok.Data;
import java.util.UUID;

@Data
public class Article {
    private StringProperty PIC ;
    private StringProperty article;
    private StringProperty description;
    private StringProperty location;
    private DoubleProperty price;
    private IntegerProperty stock;
    private BooleanProperty isActive;

    public Article(StringProperty article, StringProperty description, StringProperty location,
                   DoubleProperty price, IntegerProperty stock, BooleanProperty isActive) {
        this.PIC = new SimpleStringProperty(UUID.randomUUID().toString());
        this.article = article;
        this.description = description;
        this.location = location;
        this.price = price;
        this.stock = stock;
        this.isActive = isActive;
    }
    public Article(StringProperty PIC , StringProperty article, StringProperty description, StringProperty location,
                   DoubleProperty price, IntegerProperty stock, BooleanProperty isActive) {
        this.PIC = PIC;
        this.article = article;
        this.description = description;
        this.location = location;
        this.price = price;
        this.stock = stock;
        this.isActive = isActive;
    }
    @Override
    public String toString() {
        return "Products{" +
                "PIC=" + PIC +
                ", article='" + article + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", isActive=" + isActive +
                '}';
    }
}
