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

import java.util.Optional;

public class EmpleadosVistaManagerController {
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
    public void onSalirAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setContentText("¿Está seguro que desea salir?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        } else {
            alert.close();
        }
    }
}
