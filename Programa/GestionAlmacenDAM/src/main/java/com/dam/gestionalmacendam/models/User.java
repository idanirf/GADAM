package com.dam.gestionalmacendam.models;


public class User {
    private int idUser;
    private String username;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
