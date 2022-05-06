package com.dam.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Employee extends User {
    private final int EIC;
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
