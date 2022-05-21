package com.dam.gestionalmacendam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

public class pedidoManagerViewController {

    @FXML
    private Button butonVerDetalle;

    @FXML
    private TableColumn<?, ?> tablaCliente;

    @FXML
    private TableColumn<?, ?> tablaMetodoDePago;

    @FXML
    private TableColumn<?, ?> tablaOIC;

    @FXML
    private TableView<?> tablaPedido;

    @FXML
    private TableColumn<?, ?> tablaPrecio;

    @FXML
    private TextField textAreaBuscarPorOic;

    @FXML
    void buscaPorOIC(InputMethodEvent event) {

    }

    @FXML
    void onButonVerDetalle(MouseEvent event) {

    }

}
