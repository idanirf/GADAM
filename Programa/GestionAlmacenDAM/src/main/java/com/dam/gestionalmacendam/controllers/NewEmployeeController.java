package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.HelloApplication;
import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import com.dam.gestionalmacendam.utils.Resources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class NewEmployeeController {
    private final boolean aceptarClicked = false;
    EmployeeRepository repository = EmployeeRepository.getInstance(DataBaseManager.getInstance());
    @FXML
    TextField nombre;
    @FXML
    TextField surname;
    @FXML
    TextField nick;
    @FXML
    PasswordField password;
    @FXML
    TextField nif;
    @FXML
    TextField email;
    @FXML
    CheckBox isManager;
    @FXML
    ImageView imagePerfil;
    @FXML
    CheckBox isActive;
    private boolean editMode = false;
    private Employee employee;
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isAceptarClicked() {
        return aceptarClicked;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        System.out.println("Producto asociado: " + employee);
        if (editMode) {
            setDataInfo();
        }
        nombre.requestFocus();
    }

    public void setEditarModo(boolean editarModo) {
        this.editMode = editarModo;
    }

    private void setDataInfo() {
        nombre.setText(employee.getName());
        surname.setText(employee.getSurname());
        nick.setText(employee.getNickName());
        password.setText(employee.getPassword());
        email.setText(employee.getEmail());
        nif.setText(employee.getNif());
        isManager.setSelected(employee.getIsActive().get());
        isActive.setSelected(employee.getIsActive().get());

        if (!employee.getPhoto().isBlank() && Files.exists(Paths.get(employee.getPhoto().replace("file:/", "")))) {
            System.out.println(("Buscando la imagen: " + employee.getPhoto()));
            Image image = new Image(new File(employee.getPhoto()).toURI().toString());
            System.out.println(("Imagen Encontrada en: " + image.getUrl()));
            imagePerfil.setImage(image);
        } else {
            System.out.println(("No existe la imagen. Usando imagen por defecto"));
            imagePerfil.setImage(new Image(Resources.get(HelloApplication.class, "images/user.png")));
            employee.setPhoto(Resources.getPath(HelloApplication.class, "image/user.png"));
        }

    }

    public void onAceptarAction(ActionEvent event) {
        if (isDataValid()) {
            saveEmployee();
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Datos no validos.");
            alert.setContentText("Seleccione Aceptar los Términos.");
            alert.showAndWait();
        }
    }

    private void saveEmployee() {
        try {
            repository.save(
                    new Employee(
                            nombre.getText(),
                            surname.getText(),
                            nif.getText(),
                            email.getText(),
                            imagePerfil.getImage().getUrl().replaceFirst("file:/", ""),
                            nick.getText(),
                            password.getText(),
                            isManager.isSelected(),
                            LocalDateTime.now(),
                            isActive.isSelected())
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private boolean isDataValid() {
        String errorMessage = "";
        if (nombre.getText() == null || nombre.getText().isBlank()) {
            errorMessage += "Debes introducir el nombre";
        }
        if (surname.getText() == null || surname.getText().isBlank()) {
            errorMessage += "Debes introducir apellidos";
        }
        if (nick.getText() == null || nick.getText().isBlank()) {
            errorMessage += "Debes introducir nick de usuario";
        }
        if (password.getText() == null || password.getText().isBlank()) {
            errorMessage += "Debes introducir una contraseña";
        }
        if (email.getText() == null || email.getText().isBlank()) {
            errorMessage += "Debes introducir apellidos";
        }
        if (nif.getText() == null || nif.getText().isBlank()) {
            errorMessage += "Debes introducir apellidos";
        }
        return errorMessage.length() == 0;

    }

    @FXML
    private void onClickImg() {
        FileChooser buscadorImg = new FileChooser();
        buscadorImg.setTitle("Selecciona la imagen del producto: ");
        buscadorImg.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png"));
        File file = buscadorImg.showOpenDialog(imagePerfil.getScene().getWindow());

        if (file != null) {
            System.out.println(("Seleccion del archivo: " + file.getAbsolutePath()));
            imagePerfil.setImage(new Image(file.toURI().toString()));
            employee.setPhoto(file.getAbsolutePath());

        }
    }
}
