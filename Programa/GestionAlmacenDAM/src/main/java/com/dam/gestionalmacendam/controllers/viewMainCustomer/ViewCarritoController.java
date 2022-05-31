package com.dam.gestionalmacendam.controllers.viewMainCustomer;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.*;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import com.dam.gestionalmacendam.repositories.carrito.CarritoRepository;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ViewCarritoController {
    private final ObservableList<Integer> cantidadList = FXCollections.observableArrayList();
    private final CarritoRepository carritoRepository = CarritoRepository.getInstance();
    private final OrderRepository orderRepository = OrderRepository.getInstance(DataBaseManager.getInstance());
    private final LineOrderRepository lineOrderRepository = LineOrderRepository.getInstance(DataBaseManager.getInstance());
    private final ArticleRepository articleRepository = ArticleRepository.getInstance(DataBaseManager.getInstance());
    private Customer customer;
    private Stage main;
    private Stage viewCarrito;
    @FXML
    private ListView<CarritoItem> listProducts;
    @FXML
    private ListView<CarritoItem> listPrice;
    @FXML
    private TextField txtTotal;

    public void setData() {
        System.out.println("Cargando los productos de la cesta.");
        listProducts.setItems(carritoRepository.getItems());
        listPrice.setItems(carritoRepository.getItems());
        calcularTotal();
    }

    public void onPedidoAction(ActionEvent actionEvent) {
        Order order = new Order(customer.getName(), Pay.CARD);
        var list = carritoRepository.getItems();
        List<LineOrder> lista = new ArrayList<>();
        int cost = 0;
        boolean error = false;

        try {
            for (int i = 0; i < list.size(); i++) {
                var aux = articleRepository.findByName(list.get(i).getName());
                if (aux.getStock().get() - list.get(i).getAmount() >= 0) {
                    lista.add(
                            new LineOrder(
                                    list.get(i).getName(),
                                    list.get(i).getAmount(),
                                    list.get(i).getPrice(),
                                    order.getOIC()
                            )
                    );
                    cost += list.get(i).getTotal();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("No hay suficiente stock del producto " + aux.getArticle().get());
                    alert.showAndWait();
                    error = true;
                }
                System.out.println(list.get(i));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (!error) {
            order.setPrice(new SimpleDoubleProperty(cost));
            try {
                orderRepository.save(order);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            lista.forEach(item -> {
                try {
                    var aux = articleRepository.findByName(item.getArticle().get());
                    aux.setStock(new SimpleIntegerProperty(aux.getStock().get() - item.getLoad().get()));
                    articleRepository.update(aux.getPIC(), aux);
                    lineOrderRepository.save(item);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
            viewCarrito.close();
            main.show();
        }
        
    }

    public void setDialogStage(Stage stage) {
        this.main = stage;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStage(Stage stage) {
        cantidadList.addAll(1, 2, 3, 4, 5);
        this.viewCarrito = stage;
        main.close();
        initList();
        initPrice();
        setData();
    }

    private void initPrice() {
        listPrice.setCellFactory(param -> new ListCell<>() {
            @Override
            public void updateItem(CarritoItem item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    VBox vbox = new VBox();
                    var precio = item.getName() + "       " + item.getAmount() + "  x  " + item.getPrice() + "  =  " + item.getTotal() + " €";
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

    public void initList() {
        listProducts.setCellFactory(param -> new ListCell<>() {
            @Override
            public void updateItem(CarritoItem item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    HBox hbox = new HBox();
                    hbox.setSpacing(15);
                    VBox vbox = new VBox();
                    vbox.setSpacing(30);
                    ImageView img = new ImageView();
                    Image image = new Image(item.getPhoto());
                    img.setImage(image);
                    img.setFitHeight(100);
                    img.setFitWidth(150);

                    HBox hbox2 = new HBox();
                    hbox2.setSpacing(10);
                    Label label = new Label(item.getName());
                    Label cant = new Label("Cantidad: ");
                    Label price = new Label("Price: " + item.getPrice());
                    cant.setStyle("-fx-font-weight: bold ;-fx-font-size: 16px");
                    label.setStyle("-fx-font-weight: bold ;-fx-font-size: 26px");
                    price.setStyle("-fx-font-weight: bold ;-fx-font-size: 20px");
                    price.setTextFill(Color.valueOf("#f79329"));
                    price.setAlignment(Pos.BOTTOM_LEFT);
                    price.setPadding(new Insets(0, 0, 0, 150));
                    price.setPrefHeight(95);
                    ChoiceBox choice = new ChoiceBox(cantidadList);
                    choice.getSelectionModel().select(cantidadList.indexOf(item.getAmount()));

                    choice.setOnAction(event -> {
                        var cantidad = (int) choice.getSelectionModel().getSelectedItem();
                        item.setAmount(cantidad);
                        calcularTotal();
                        initPrice();
                    });

                    hbox2.getChildren().addAll(cant, choice);
                    vbox.getChildren().addAll(label, hbox2);
                    hbox.getChildren().addAll(img, vbox, price);
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
