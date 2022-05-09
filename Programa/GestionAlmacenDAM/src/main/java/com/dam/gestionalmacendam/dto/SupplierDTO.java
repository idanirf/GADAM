package com.dam.gestionalmacendam.dto;

import com.dam.gestionalmacendam.models.Supplier;

import java.io.Serializable;
import java.util.UUID;

public class SupplierDTO implements Serializable {
    private String SIC;
    private final String nameSupplier;
    private final String direction;
    private final String telephoneNumber;
    private final String email;

    public SupplierDTO(Supplier supplier){
        this.SIC = supplier.getSIC();
        this.nameSupplier = supplier.getNameSupplier();
        this.direction = supplier.getDirection();
        this.telephoneNumber = supplier.getTelephoneNumber();
        this.email = supplier.getEmail();
    }

    public Supplier fromDTO(){
        return new Supplier(SIC, nameSupplier, direction, telephoneNumber, email);
    }
}
