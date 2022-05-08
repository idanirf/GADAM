package com.dam.gestionalmacendam.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LineOrder {
    private final UUID OLIC = UUID.randomUUID();
    private Article article;
    private int load;
    private Double unitPrice;
    private Double totalPrice;

    @Override
    public String toString() {
        return "LineOrder{" +
                "OLIC=" + OLIC +
                ", article=" + article +
                ", load=" + load +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
