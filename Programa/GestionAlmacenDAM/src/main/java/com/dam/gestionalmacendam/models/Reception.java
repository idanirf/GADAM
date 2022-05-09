package com.dam.gestionalmacendam.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Reception {
    private final UUID RIC = UUID.randomUUID();
    private Supplier supplier;
    private String carrier;
    private Double cost;

    @Override
    public String toString() {
        return "Reception{" +
                "RIC=" + RIC +
                ", supplier=" + supplier +
                ", carrier='" + carrier + '\'' +
                ", cost=" + cost +
                '}';
    }
}
