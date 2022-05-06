package com.dam.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Customer extends User {
    private final int CIC;
    private String name;
    private String surname;
    private String cif;
    private String direction;
    private String telephoneNumber;
    private String email;

    private String photo;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Customer{" +
                "CIC=" + CIC +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", cif='" + cif + '\'' +
                ", direction='" + direction + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
