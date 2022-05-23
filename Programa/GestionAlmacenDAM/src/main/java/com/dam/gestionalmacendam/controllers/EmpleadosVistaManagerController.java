package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class EmpleadosVistaManagerController {
    @FXML
    public TextField buscar;
    EmployeeRepository employeeRepository = EmployeeRepository.getInstance(DataBaseManager.getInstance());
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> photo;
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
    private TableColumn<Employee, LocalDateTime> createdAt;
    @FXML
    private TableColumn<Employee, Boolean> active;


    @FXML
    private void initialize() {
        try {
            loadData();
        } catch (SQLException e) {
        }
        photo.setCellValueFactory(cellData -> cellData.getValue().photoProperty());
        nick.setCellValueFactory(cellData -> cellData.getValue().nicknameProperty());
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        surname.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        nif.setCellValueFactory(cellData -> cellData.getValue().nifProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        manager.setCellValueFactory(cellData -> cellData.getValue().isManagerProperty());
        createdAt.setCellValueFactory(cellData -> cellData.getValue().createdAtProperty());
        active.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
    }

    @FXML
    private void onModificarAction() throws SQLException {
        Employee employee = employeeTable.getFocusModel().getFocusedItem();
        System.out.println(employee);
        try {
            SceneManager.get().initModifyEmployee(employee);
        } catch (Exception e) {
            System.out.println("No se ha seleccionado el empleado");
        }
    }

    @FXML
    private void loadData() throws SQLException {
        employeeTable.setItems(employeeRepository.findAll());
    }

    public void onNewEmployee() throws IOException {
        System.out.println("Insertando Empleado");
        Employee employee = new Employee();
        boolean aceptarClicked = SceneManager.initNewEmployee(false, employee);
        if (aceptarClicked) {
            try {
                employeeRepository.save(employee);
                loadData();
            } catch (SQLException e) {
                System.err.println(("Error al crear persona: " + e.getMessage()));
            }
        }
    }

    public void findByUUID() throws SQLException {
        String uuid = buscar.getText();
        if (uuid.isEmpty()) {
            loadData();
        } else {
            employeeTable.setItems(employeeRepository.findAll().filtered(x -> x.getNickName()
                    .contains(uuid) || x.getEIC().contains(uuid)));
        }
        employeeTable.refresh();
    }
}
