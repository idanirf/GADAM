package com.dam.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class Reception {
    private final UUID RIC;
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
