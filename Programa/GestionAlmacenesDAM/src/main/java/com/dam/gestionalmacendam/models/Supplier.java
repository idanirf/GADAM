package com.dam.gestionalmacendam.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Supplier {
    private final UUID SIC = UUID.randomUUID();
    private String nameSupplier;
    private String direction;
    private String telephoneNumber;
    private String email;

    @Override
    public String toString() {
        return "Supplier{" +
                "SIC=" + SIC +
                ", nameSupplier='" + nameSupplier + '\'' +
                ", direction='" + direction + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
