package com.dam.gestionalmacendam.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LineReception {
    private final UUID RLIC = UUID.randomUUID();
    private Article article;
    private int load;
    private Double unitPrice;
    private Double totalPrice;

    @Override
    public String toString() {
        return "LineReception{" +
                "RLIC=" + RLIC +
                ", article=" + article +
                ", load=" + load +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
