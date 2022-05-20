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

    public String getPIC() {
        return PIC;
    }

    public void setPIC(String PIC) {
        this.PIC = PIC;
    }

    public String getArticle() {
        return article.get();
    }

    public StringProperty articleProperty() {
        return article;
    }

    public void setArticle(String article) {
        this.article.set(article);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getStock() {
        return stock.get();
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public boolean isIsActive() {
        return isActive.get();
    }

    public BooleanProperty isActiveProperty() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive.set(isActive);
    }

    public String getPhoto() {
        return photo.get();
    }

    public StringProperty photoProperty() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo.set(photo);
    }
}
