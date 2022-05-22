package com.dam.gestionalmacendam.managers;

import com.dam.gestionalmacendam.HelloApplication;

import com.dam.gestionalmacendam.controllers.*;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.views.Views;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

import java.util.Objects;


public class SceneManager {
    private static SceneManager instance;
    private final Class<?> appClass;

    private Stage mainStage;
    private Stage splash;

    private SceneManager(Class<?> appClass) {
        this.appClass = appClass;
        //System.out.println("SceneManager created");
    }

    public static SceneManager getInstance(Class<?> appClass) {
        if (instance == null) {
            instance = new SceneManager(appClass);
        }
        return instance;
    }

    public static SceneManager get() {
        return instance;
    }


    public void initSplash(Stage stage) throws IOException{
        Platform.setImplicitExit(false);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.SPLASH.get()));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        SplashController controller= fxmlLoader.getController();
        controller.setDialogStage(stage);
//        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, null)));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }
    public void initLogin(Stage splash) throws IOException {
        Platform.setImplicitExit(false);
        System.out.println("Iniciando Login");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.LOGIN.get())));
        Scene scene = new Scene(fxmlLoader.load(), 540, 550);
        Stage stage = new Stage();
        LoginController controller= fxmlLoader.getController();
        controller.setDialogStage(stage);
        this.splash=splash;
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Saliendo...");
            alert.setHeaderText("¿Esta seguro que desea salir?");
            alert.setContentText("Pulse aceptar para salir.");
            var res= alert.showAndWait();
            if(res.get()== ButtonType.OK){
                Platform.exit();
            }else{
                event.consume();
            }
        });
    }
    public void initRegister() throws IOException {
        System.out.println("Registrando nuevo usuario.");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.REGISTER.get())));
        Scene scene = new Scene(fxmlLoader.load(), 641, 720);
        Stage stage = new Stage();
        RegisterController controller= fxmlLoader.getController();
        controller.setDialogStage(stage);
        stage.setTitle("Registrar Usuario Nuevo");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Saliendo...");
            alert.setHeaderText("¿Esta seguro que desea salir?");
            alert.setContentText("Pulse aceptar para salir.");
            var res= alert.showAndWait();
            if(res.get()== ButtonType.OK){
                stage.close();
            }else{
                event.consume();
            }
        });
    }
    public void initMainCustomer(Stage login) throws IOException {
        System.out.println("Entrando a la vista del cliente.");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.MAIN_CUSTOMER.get())));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Stage stage = new Stage();
        MainCustomerController controller= fxmlLoader.getController();
        controller.setDialogStage(stage);
        mainStage=stage;
        stage.setTitle("GADAM S.L.");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        login.close();
        if (!login.isShowing()){
            splash.close();
        }
        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Saliendo...");
            alert.setHeaderText("¿Esta seguro que desea salir?");
            alert.setContentText("Pulse aceptar para salir.");
            var res= alert.showAndWait();
            if(res.get()== ButtonType.OK){
                Platform.exit();
            }else{
                event.consume();
            }
        });

    }
    public void initViewArticle(Article article) throws IOException {
        System.out.println("Viendo resumen del articulo " + article.getArticle().get());
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.VIEW_ARTICLE.get())));
        Scene scene = new Scene(fxmlLoader.load(), 560, 520);
        Stage stage = new Stage();
        ViewArticleController controller = fxmlLoader.getController();
        controller.setArticle(article);
        controller.setDialogStage(stage);
        stage.setTitle("Producto " + article.getArticle().get());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

}
