package com.dam.gestionalmacendam.views;

public enum Views {
    SPLASH("views/splash.fxml"),
    LOGIN("views/login.fxml"),
    REGISTER("views/register.fxml"),
    MAIN_CUSTOMER("views/main-customer.fxml");

    private final String view;

    Views(String view) {
        this.view = view;
    }

    public String get() {
        return view;
    }
}
