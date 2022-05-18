package com.dam.gestionalmacendam.models;

import javafx.beans.property.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Employee {
    private String EIC = UUID.randomUUID().toString();
    private StringProperty name;
    private StringProperty surname;
    private StringProperty nif;
    private StringProperty email;
    private StringProperty photo;

    private StringProperty nickName;
    private StringProperty password;
    private BooleanProperty isManager;
    private ObjectProperty<LocalDateTime> createdAt;

    public Employee(String name, String surname, String nif, String email, String photo, String nickName, String password, boolean isManager, LocalDateTime createdAt) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.nif = new SimpleStringProperty(nif);
        this.email = new SimpleStringProperty(email);
        this.nickName = new SimpleStringProperty(nickName);
        this.password = new SimpleStringProperty(password);
        this.photo = new SimpleStringProperty(photo);
        this.isManager = new SimpleBooleanProperty(isManager);
        this.createdAt = new SimpleObjectProperty<LocalDateTime>(createdAt);
    }

    public Employee(String eic, String name, String surname, String nif, String email, String photo, String nickName, String password, boolean isManager, LocalDateTime createdAt) {
        this.EIC = eic;
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.nif = new SimpleStringProperty(nif);
        this.email = new SimpleStringProperty(email);
        this.photo = new SimpleStringProperty(photo);
        this.nickName = new SimpleStringProperty(nickName);
        this.password = new SimpleStringProperty(password);
        this.isManager = new SimpleBooleanProperty(isManager);
        this.createdAt = new SimpleObjectProperty<LocalDateTime>(createdAt);
    }

    public String getEIC() {
        return EIC;
    }

    public String getName() {
        return name.get();
    }

    public String getSurname() {
        return surname.get();
    }

    public String getNif() {
        return nif.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPhoto() {
        return photo.get();
    }

    public String getNickName() {
        return nickName.get();
    }

    public String getPassword() {
        return password.get();
    }

    public boolean isManager() {
        return isManager.get();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt.get();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EIC='" + EIC + '\'' +
                ", name=" + name +
                ", surname=" + surname +
                ", nif=" + nif +
                ", email=" + email +
                ", photo=" + photo +
                ", nickName=" + nickName +
                ", password=" + password +
                ", isManager=" + isManager +
                ", createdAt=" + createdAt +
                '}';
    }
}
