package com.dam.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LineOrder {
    private final UUID OLIC;
    private Article articles;
    private int load;
    private Double unitPrice;
    private Double totalPrice;
}
