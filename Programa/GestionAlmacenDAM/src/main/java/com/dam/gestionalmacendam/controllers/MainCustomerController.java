package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.HelloApplication;
import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import com.dam.gestionalmacendam.utils.Resources;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;


public class MainCustomerController {
    private Stage stage;
    private final ArticleRepository articleRepository= ArticleRepository.getInstance(DataBaseManager.getInstance());

    @FXML
    private TextField txtSearch;
    @FXML
    private GridPane grid;
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize(){
        try {
            initArticles();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnCarritoAction(ActionEvent actionEvent) {
        System.out.println("Yendo al carrito de compra.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Carrito");
        alert.setHeaderText("Estaria entrando al carrito");
        alert.setContentText("Vista del carrito.");
        alert.show();
    }

    @FXML
    private void btnMyUserClick(MouseEvent mouseEvent) {
        System.out.println("Yendo al perfil.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Perfil");
        alert.setHeaderText("Estaria entrando a su perfil");
        alert.setContentText("Vista de su perfil.");
        alert.show();
    }

    private void initArticles() throws SQLException {
        System.out.println("Iniciando los articulos.");

        var li=articleRepository.findAll();
        var k=0;
        for (int i = 0; i < grid.getRowCount() ; i++) {
            for (int j = 0; j < grid.getColumnCount() ; j++) {
                if (k<li.size()){

                    VBox vbox= getItemCell(li,k);
                    grid.add(vbox,j,i);
//                    grid.setGridLinesVisible(true);
                    k++;
                }

            }
        }

    }
    private VBox getItemCell(ObservableList<Article> list, int pos) throws SQLException {
        var article= list.get(pos);
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setStyle("-fx-border-color: transparent gray gray transparent ");
        ImageView iv = new ImageView();
        iv.setFitHeight(130);
        iv.setFitWidth(130);

        if (!article.getPhoto().get().isBlank() && Files.exists(Paths.get(article.getPhoto().get()))) {
            Image image = new Image(new File(article.getPhoto().get()).toURI().toString());
            iv.setImage(image);
        } else {
            iv.setImage(new Image(Resources.get(HelloApplication.class, "images/article_default.png")));
            list.get(pos).setPhoto(new SimpleStringProperty(Resources.getPath(HelloApplication.class, "images/article_default.png").replaceFirst("/","")));
            articleRepository.update(list.get(pos).getPIC(),list.get(pos));
        }

        HBox hbox = new HBox();
        hbox.setSpacing(15);
        hbox.setAlignment(Pos.TOP_CENTER);
        Label nombre = new Label(list.get(pos).getArticle().get());
        nombre.setStyle("-fx-font-weight: bold");
        nombre.setStyle("-fx-font-size: 16");
        Label precio = new Label(list.get(pos).getPrice().get()+" €");
        precio.setStyle("-fx-font-size: 16");
        hbox.getChildren().addAll(nombre,precio);
        vbox.getChildren().addAll(iv,hbox);

        vbox.setOnMouseClicked(event ->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Saliendo...");
                alert.setHeaderText("¿Esta seguro que desea salir?");
                alert.setContentText("Pulse aceptar para salir.");
                alert.showAndWait();
        });

        return vbox;
    }

}
