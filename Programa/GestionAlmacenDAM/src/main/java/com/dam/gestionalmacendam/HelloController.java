package com.dam.gestionalmacendam;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import com.dam.gestionalmacendam.repositories.supplier.SupplierRepository;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Optional;

public class HelloController {
    OrderRepository repository = OrderRepository.getInstance(DataBaseManager.getInstance());
    SupplierRepository repositorySuplier = SupplierRepository.getInstance(DataBaseManager.getInstance());
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        try{
                    SceneManager.get().initSuplierView();

        }catch (Exception e){
            System.out.println("no puede cargarse pantalla suplier");
        }


    }
    @FXML
    protected void aonHelloButtonClick() {

        try{
            if( repository.findAll().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Opcion no disponible");
                alert.setHeaderText("Opcion no disponible");
                alert.setContentText("no existe ningun Pedido en este momento, " +
                        "para aceder a esta opcion espere a que se realice un pedido");

                alert.showAndWait();
            }else{

                //si no esta vacia mostramos
                try{
                    SceneManager.get().initOrderView();
                }catch(Exception e){
                    System.out.println("no consigue cargar pantalla order manager");
                    e.printStackTrace();


                }
            }
        }catch(Exception e){
            System.out.println("no consigue cargar pantalla order manager ");
            e.printStackTrace();


        }


    }

}