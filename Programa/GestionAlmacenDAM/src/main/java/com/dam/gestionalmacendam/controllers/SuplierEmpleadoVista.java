package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Supplier;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import com.dam.gestionalmacendam.repositories.supplier.SupplierRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class SuplierEmpleadoVista {
    SupplierRepository repository = SupplierRepository.getInstance(DataBaseManager.getInstance());

    @FXML
    private Button ButonCrear;

    @FXML
    private Button butonModificar;

    @FXML
    private TableColumn<Supplier, String> colDireci;

    @FXML
    private TableColumn<Supplier, String> colEmail;

    @FXML
    private TableColumn<Supplier, String> colName;

    @FXML
    private TableColumn<Supplier, String> colSIC;

    @FXML
    private TableColumn<Supplier, String> colTelefo;

    @FXML
    private TableView<Supplier> suplierTable;

    @FXML
    private TextField textAreaSIC;

    @FXML
    void findByUUID(ActionEvent event) throws SQLException {

        String name = textAreaSIC.getText();
        if(name.isEmpty()){
            loadData();
        }else{
            suplierTable.setItems(repository.findAll().filtered(x -> x.getNameSupplier()
                    .toLowerCase().contains(name)|| x.getSIC().toUpperCase().contains(name)));
        }
        suplierTable.refresh();
    }

    @FXML
    void onModificarAction(ActionEvent event) {


        Supplier s = suplierTable.getFocusModel().getFocusedItem();
        System.out.println(s);
        try {
            SceneManager.get().initModificarSuplier(s);
        } catch (Exception e) {
            System.out.println("No se ha seleccionado el empleado");
            e.printStackTrace();
        }

    }

    @FXML
    void onNewSuplier(ActionEvent event) {
        System.out.println("intentando cargar nuevo suplier ");
        try {
            SceneManager.get().initNewSuplier();
        }catch (Exception e) {
            System.out.println("no se ha pordido cargar ");
        }

    }

    private void mensajeProceso() {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("aun no esta operativa está selecion");
            alert.setContentText("aun no esta operativa está selecion");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            } else {
                alert.close();
            }

    }

    @FXML
    void onVerTodos(ActionEvent event) {

    }

    @FXML
    private void initialize() {
        try {
            loadData();
        } catch (SQLException e) {
            System.out.println("error al cargar datos");
        }
        colDireci.setCellValueFactory(cellData -> cellData.getValue().directionProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colName.setCellValueFactory(cellData -> cellData.getValue().nameSupplierProperty());
        colSIC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSIC()));
        colTelefo.setCellValueFactory(cellData -> cellData.getValue().telephoneNumberProperty());


    }

    @FXML
    private void loadData() throws SQLException {
        suplierTable.setItems(repository.findAll());
    }

    @FXML
    void errorDeBudqueda(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Suplier selecionado no existe o incorrecto");
        alert.setContentText("No ha selecionado ningun suplier o suplier selecionado no existe o incorrecto");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        } else {
            alert.close();
        }
    }

    //seleciona y pone en el tex area
    public void selecionarAcion(MouseEvent mouseEvent) {
        Supplier o = suplierTable.getSelectionModel().getSelectedItem();
        textAreaSIC.setText(o.getSIC());
    }

    public void findByUUID() throws SQLException {

        String name = textAreaSIC.getText();
        if(name.isEmpty()){
            errorDeBudqueda();
        }else{
            suplierTable.setItems(repository.findAll().filtered(x -> x.getSIC().contains(name)));
        }
        suplierTable.refresh();
    }
}
