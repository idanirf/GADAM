package com.dam.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LineReception {
    private final UUID RLIC;
    private Article article;
    private int load;
    private Double unitPrice;
    private Double totalPrice;
}
