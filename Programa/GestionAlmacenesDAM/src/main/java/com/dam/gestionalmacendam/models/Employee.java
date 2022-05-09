package com.dam.gestionalmacendam.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Employee extends User {
    private final UUID EIC = UUID.randomUUID();
    private String name;
    private String surname;
    private String nif;
    private String email;
    private String photo;
    private boolean isManager;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Employee{" +
                "EIC=" + EIC +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nif='" + nif + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", isManager=" + isManager +
                ", createdAt=" + createdAt +
                '}';
    }
}
