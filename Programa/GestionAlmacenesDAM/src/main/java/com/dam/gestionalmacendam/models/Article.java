package com.dam.gestionalmacendam.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class Article {
    private final UUID PIC = UUID.fromString(UUID.randomUUID().toString());
    private String article;
    private String description;
    private String location;
    private double price;
    private int stock;
    private Boolean isActive;

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
