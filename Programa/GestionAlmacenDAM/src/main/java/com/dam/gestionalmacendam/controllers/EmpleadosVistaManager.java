package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.Optional;

public class EmpleadosVistaManager {
    EmployeeRepository employeeRepository = EmployeeRepository.getInstance(DataBaseManager.getInstance());

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> imagenEmpleado;
    @FXML
    private TableColumn<Employee, String> EIC;
    @FXML
    private TableColumn<Employee, String> nick;
    @FXML
    private TableColumn<Employee, String> name;
    @FXML
    private TableColumn<Employee, String> surname;
    @FXML
    private TableColumn<Employee, String> nif;
    @FXML
    private TableColumn<Employee, String> email;
    @FXML
    private TableColumn<Employee, Boolean> manager;
    @FXML
    private TableColumn<Employee, String> createdAt;

    @FXML
    private void initialize() {
        try {
            loadData();
        } catch (SQLException e) {
        }
        imagenEmpleado.setCellValueFactory(cellData -> cellData.getValue().photoProperty());
        nick.setCellValueFactory(cellData -> cellData.getValue().nicknameProperty());
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        surname.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        nif.setCellValueFactory(cellData -> cellData.getValue().nifProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        manager.setCellValueFactory(cellData -> cellData.getValue().isManagerProperty());
        createdAt.setCellValueFactory(cellData -> cellData.getValue().createdAtProperty());
    }

    @FXML
    public void onSalirAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setContentText("¿Salir de GADAM Gestion Almacenes?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        } else {
            alert.close();
        }
    }

    //LOAD DATA EMPLOYEE
    @FXML
    private void loadData() throws SQLException {
        employeeTable.setItems(employeeRepository.findAll());
    }

}
