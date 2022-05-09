package com.dam.gestionalmacendam.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Customer extends User {
    private final String CIC;
    private StringProperty name;
    private StringProperty surname;
    private StringProperty cif;
    private StringProperty direction;
    private StringProperty telephoneNumber;
    private StringProperty email;
    private StringProperty photo;
    private ObjectProperty<LocalDateTime> createdAt;

    public Customer(String CIC, String name, String surname, String cif, String direction, String telephoneNumber, String email, String photo,  LocalDateTime createdAt){
        this.CIC=CIC;
        this.name=new SimpleStringProperty(name);
        this.surname=new SimpleStringProperty(surname);
        this.cif=new SimpleStringProperty(cif);
        this.direction=new SimpleStringProperty(direction);
        this.telephoneNumber=new SimpleStringProperty(telephoneNumber);
        this.email=new SimpleStringProperty(email);
        this.photo=new SimpleStringProperty(photo);
        this.createdAt=new SimpleObjectProperty<LocalDateTime>(createdAt);


    }

    public String getName() {
        return name.get();
    }


    public String getSurname() {
        return surname.get();
    }



    public String getCif() {
        return cif.get();
    }


    public String getDirection() {
        return direction.get();
    }


    public String getTelephoneNumber() {
        return telephoneNumber.get();
    }


    public String getEmail() {
        return email.get();
    }


    public String getPhoto() {
        return photo.get();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt.get();
    }

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
