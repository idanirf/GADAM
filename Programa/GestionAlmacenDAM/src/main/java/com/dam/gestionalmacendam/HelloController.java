package com.dam.gestionalmacendam;

import com.dam.gestionalmacendam.managers.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        try{
            SceneManager.get().initOrderView();
        }catch(Exception e){
            System.out.println("no consigue cargar pantalla order manager");
            e.printStackTrace();


        }

    }

}