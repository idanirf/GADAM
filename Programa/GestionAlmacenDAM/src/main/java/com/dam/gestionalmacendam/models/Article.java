package com.dam.gestionalmacendam.models;

import javafx.beans.property.*;
import lombok.Data;
import java.util.UUID;

@Data
public class Article {
    private String PIC=UUID.randomUUID().toString();
    private StringProperty article;
    private StringProperty description;
    private StringProperty location;
    private DoubleProperty price;
    private IntegerProperty stock;
    private BooleanProperty isActive;
    private StringProperty photo;

    public Article(String article, String description, String location,
                   Double price, Integer  stock, Boolean  isActive,String photo) {
        this.article =new SimpleStringProperty(article);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.price = new SimpleDoubleProperty(price) ;
        this.stock = new SimpleIntegerProperty(stock);
        this.isActive = new SimpleBooleanProperty(isActive);
        this.photo=new SimpleStringProperty(photo);
    }
    public Article(String PIC ,String article, String description, String location,
                   Double price, Integer  stock, Boolean  isActive, String photo) {
        this.PIC = PIC;
        this.article =new SimpleStringProperty(article);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.price = new SimpleDoubleProperty(price) ;
        this.stock = new SimpleIntegerProperty(stock);
        this.isActive = new SimpleBooleanProperty(isActive);
        this.photo=new SimpleStringProperty(photo);
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
                ", photo=" + photo +
                '}';
    }
}
