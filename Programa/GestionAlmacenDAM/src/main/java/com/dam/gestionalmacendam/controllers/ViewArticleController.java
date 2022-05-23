package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.models.Article;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;


public class ViewArticleController {
    private Article article;
    private Stage stage;
    @FXML
    private ImageView viewArticle;
    @FXML
    private Label txtNameArticle;
    @FXML
    private Label txtStock;
    @FXML
    private Label txtPrice;
    @FXML
    private Label txtDescription;
    @FXML
    private Button btnAddArticle;
    @FXML
    public void btnAddArticleAction(ActionEvent actionEvent) {
    }
    public void setArticle(Article article) {
        this.article = article;
        initArticle();
    }
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    public Article getArticle(){
        return this.article;
    }

    private void initArticle() {
        Image image = new Image(new File(article.getPhoto().get()).toURI().toString());
        viewArticle.setImage(image);
        viewArticle.setFitWidth(234.0);
        viewArticle.setFitHeight(281.0);
        txtNameArticle.setText(article.getArticle().get());
        txtStock.setText("Stock: "+article.getStock().get());
        txtPrice.setText("Precio: "+article.getPrice().get() + "€");
        txtDescription.setText(article.getDescription().get());
        if (!article.isActive()){
            btnAddArticle.setDisable(true);
        }
    }
}
