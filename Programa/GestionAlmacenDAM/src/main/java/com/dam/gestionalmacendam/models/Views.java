package com.dam.gestionalmacendam.models;

public enum Views {
    OrderManager("pedidoManagerView.fxml"),
    OrderDescription("descriptionPedidoManagerView.fxml");


    private final String view;

    Views(String view) {
        this.view = view;
    }

    public String get() {
        return view;
    }
}
