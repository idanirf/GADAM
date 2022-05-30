package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.models.CarritoItem;
import com.dam.gestionalmacendam.repositories.carrito.CarritoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ViewCarritoController {
    private CarritoRepository carritoRepository = CarritoRepository.getInstance();
    private final ObservableList<Integer> cantidadList = FXCollections.observableArrayList();
    private Stage main;
    private Stage viewCarrito;
    @FXML
    private ListView<CarritoItem> listProducts;
    @FXML
    private ListView<CarritoItem> listPrice;
    @FXML
    private TextField txtTotal;
    public void setData(){
        System.out.println("Cargando los productos de la cesta.");
        listProducts.setItems(carritoRepository.getItems());
        listPrice.setItems(carritoRepository.getItems());
        calcularTotal();
    }
    public void onPedidoAction(ActionEvent actionEvent) {
        viewCarrito.close();
        main.show();
    }

    public void setDialogStage(Stage stage) {
        this.main=stage;
    }
    public void setStage(Stage stage) {
        cantidadList.addAll(1,2,3,4,5);
        this.viewCarrito=stage;
        main.close();
        initList();
        initPrice();
        setData();
    }

    private void initPrice() {
        listPrice.setCellFactory(param -> new ListCell<>(){
                    @Override
                    public void updateItem(CarritoItem item, boolean empty){
                        super.updateItem(item, empty);
                        if(item!= null){
                            VBox vbox = new VBox();
                            var precio=item.getName()+ "       "+  item.getAmount() + "  x  " + item.getPrice() + "  =  "+ item.getTotal()+" €";
                            Label label = new Label(precio);
                            label.setStyle("-fx-font-weight: bold ;-fx-font-size: 24px");
                            label.setAlignment(Pos.CENTER_RIGHT);
                            label.setContentDisplay(ContentDisplay.RIGHT);
                            vbox.getChildren().add(label);
                            setGraphic(label);
                        }
                    }
        });
    }

    public void initList(){
        listProducts.setCellFactory(param -> new ListCell<>(){
            @Override
            public void updateItem(CarritoItem item, boolean empty) {
                super.updateItem(item, empty);
                if (item!= null){
                    HBox hbox= new HBox();
                    hbox.setSpacing(15);
                    VBox vbox = new VBox();
                    vbox.setSpacing(30);
                    ImageView img= new ImageView();
                    Image image= new Image(item.getPhoto());
                    img.setImage(image);
                    img.setFitHeight(100);
                    img.setFitWidth(150);

                    HBox hbox2= new HBox();
                    hbox2.setSpacing(10);
                    Label label= new Label(item.getName());
                    Label cant= new Label("Cantidad: ");
                    Label price= new Label("Price: " +item.getPrice());
                    cant.setStyle("-fx-font-weight: bold ;-fx-font-size: 16px");
                    label.setStyle("-fx-font-weight: bold ;-fx-font-size: 26px");
                    price.setStyle("-fx-font-weight: bold ;-fx-font-size: 20px");
                    price.setTextFill(Color.valueOf("#f79329"));
                    price.setAlignment(Pos.BOTTOM_LEFT);
                    price.setPadding(new Insets(0,0,0,150));
                    price.setPrefHeight(95);
                    ChoiceBox choice= new ChoiceBox(cantidadList);
                    choice.getSelectionModel().select(cantidadList.indexOf(item.getAmount()));

                    choice.setOnAction(event ->{
                        var cantidad= (int) choice.getSelectionModel().getSelectedItem();
                        item.setAmount(cantidad);
                        calcularTotal();
                        initPrice();
                    });

                    hbox2.getChildren().addAll(cant,choice);
                    vbox.getChildren().addAll(label,hbox2);
                    hbox.getChildren().addAll(img,vbox,price);
                    setGraphic(hbox);
                }
            }
        });
    }
    private void calcularTotal() {
        txtTotal.setEditable(false);
        txtTotal.setText(carritoRepository.getTotal() + " €");
    }
}
