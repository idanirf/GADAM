package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import com.dam.gestionalmacendam.utils.Patterns;
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
import java.sql.SQLException;
import java.time.LocalDateTime;

public class NewEmployeeController {
    private final boolean aceptarClicked = false;
    private final boolean editMode = false;
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
    private Employee employee;
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isAceptarClicked() {
        return aceptarClicked;
    }


    public void onAceptarAction() {
        if (isDataValid()) {
            saveEmployee();
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Datos no validos.");
            alert.setContentText("Datos erroneos.");
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
        if (nombre.getText() == null || nombre.getText().isBlank() || !Patterns.patternName(nombre.getText())) {
            errorMessage += "Debes introducir el nombre";
            nombre.setText("");
        }
        if (surname.getText() == null || surname.getText().isBlank() || !Patterns.patternSurnames(surname.getText())) {
            errorMessage += "Debes introducir apellidos";
            surname.setText("");
        }
        if (nick.getText() == null || nick.getText().isBlank()) {
            errorMessage += "Debes introducir nick de usuario";
            nick.setText("");
        }
        if (password.getText() == null || password.getText().isBlank() || !Patterns.patternPassword(password.getText())) {
            errorMessage += "Debes introducir una contraseña";
            password.setText("");
        }
        if (email.getText() == null || email.getText().isBlank() || !Patterns.patternEmail(email.getText())) {
            errorMessage += "Debes introducir un email con un formato correcto";
            email.setText("");
        }
        if (nif.getText() == null || nif.getText().isBlank() || !Patterns.patternCif(nif.getText())) {
            errorMessage += "Debes introducir NIF valido";
            nif.setText("");
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
