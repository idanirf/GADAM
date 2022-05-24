package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private TableColumn<Employee, Employee> active;


    @FXML
    private void initialize() {
        try {
            loadData();
        } catch (SQLException e) {
        }
        photo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoto()));
        nick.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNickName()));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        EIC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEIC()));
        surname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSurname()));
        nif.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNif()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        manager.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isManager()));
        createdAt.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDateTime>(cellData.getValue().getCreatedAt()));
        active.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        setActiveValueFactory();
    }
    private void setActiveValueFactory(){
        active.setCellFactory(param -> new TableCell<>(){
            @Override
            public void updateItem(Employee item, boolean empty){
                if (item!=null){
                    CheckBox check= new CheckBox();
                    check.setDisable(true);
                    if (item.isActive()){
                        check.setSelected(true);
                    }else{
                        check.setSelected(false);
                    }
                    setGraphic(check);
                }

            }
        });
    }

    @FXML
    private void onModificarAction() {
        Employee employee = employeeTable.getFocusModel().getFocusedItem();
        System.out.println(employee);
        try {
            SceneManager.get().initModifyEmployee(employee);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        System.out.println("ESTAS??");
        if (aceptarClicked) {
            try {
                loadData();
            } catch (SQLException e) {
                System.err.println(("Error al cargar los empleados " + e.getMessage()));
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
