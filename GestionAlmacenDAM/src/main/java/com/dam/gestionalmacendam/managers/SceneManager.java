package com.dam.gestionalmacendam.managers;

import com.dam.gestionalmacendam.HelloApplication;
import com.dam.gestionalmacendam.controllers.*;
import com.dam.gestionalmacendam.controllers.orders.ResumenOrderController;
import com.dam.gestionalmacendam.models.*;
import com.dam.gestionalmacendam.utils.Properties;
import com.dam.gestionalmacendam.utils.Resources;
import com.dam.gestionalmacendam.views.Views;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
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

    public static boolean initNewEmployee(boolean empty, Employee employee) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.NEW_EMPLOYEE.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        NewEmployeeController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Empleados");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.showAndWait();
        return controller.isAceptarClicked();
    }

    public static boolean initNewReception(Reception recepcion, LineReception lineReception) throws IOException {
        System.out.println("Iniciando....");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.NEW_RECEPTION.get()));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        NewRecepcionController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        controller.setReception(recepcion, lineReception);
        stage.setScene(scene);
        stage.showAndWait();
        return controller.isAceptarClicked();
    }

    public void initAPPManager(Stage login, Employee employee) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.MENU_MANAGER.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        MenuManagerController controller = fxmlLoader.getController();
        controller.setEmployee(employee);
        stage.setTitle("GADAM Gestión de Almacenes");
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        login.close();
        if (!login.isShowing()) {
            splash.close();
        }
    }

    public void initAPPEmployee(Stage login, Employee employee) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.MENU_EMPLEADO.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        MenuEmployeeController controller = fxmlLoader.getController();
        controller.setEmployee(employee);
        stage.setTitle("GADAM Gestión de Almacenes");
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        login.close();
        if (!login.isShowing()) {
            splash.close();
        }
    }

    public void initAcercaDe() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.ACERCA_DE.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Acerca de");
        stage.setResizable(false);
        fxmlLoader.<AcercaDeController>getController().setDialogStage(stage);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void initSplash(Stage stage) throws IOException {
       /* Platform.setImplicitExit(false);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.SPLASH.get()));
        //Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        SplashController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();*/

       initOrderView();


    }

    public void initLogin(Stage splash) throws IOException {
        Platform.setImplicitExit(false);
        System.out.println("Iniciando Login");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.LOGIN.get())));
        Scene scene = new Scene(fxmlLoader.load(), 540, 550);
        Stage stage = new Stage();
        LoginController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        this.splash = splash;
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Saliendo...");
            alert.setHeaderText("¿Esta seguro que desea salir?");
            alert.setContentText("Pulse aceptar para salir.");
            var res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                Platform.exit();
            } else {
                event.consume();
            }
        });
    }

    public void initRegister() throws IOException {
        System.out.println("Registrando nuevo usuario.");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.REGISTER.get())));
        Scene scene = new Scene(fxmlLoader.load(), 641, 720);
        Stage stage = new Stage();
        RegisterController controller = fxmlLoader.getController();
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
            var res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                stage.close();
            } else {
                event.consume();
            }
        });
    }

    public void initMainCustomer(Stage login, Customer customer) throws IOException {
        System.out.println("Entrando a la vista del cliente.");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.MAIN_CUSTOMER.get())));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Stage stage = new Stage();
        MainCustomerController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        controller.setCustomer(customer);
        mainStage = stage;
        stage.setTitle("GADAM S.L.");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        login.close();
        if (!login.isShowing()) {
            splash.close();
        }
        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Saliendo...");
            alert.setHeaderText("¿Esta seguro que desea salir?");
            alert.setContentText("Pulse aceptar para salir.");
            var res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                Platform.exit();
            } else {
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

    public void initViewCustomer() throws IOException {
        System.out.println("Entrando a la vista de clientes.");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.VIEW_CUSTOMER.get())));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Consultas Clientes");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void initViewDataCustomer(Customer customer) throws IOException {
        System.out.println("Viendo el perfil del usuario.");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.SHOW_DATA_CUSTOMER.get())));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        Stage stage = new Stage();
        ShowCustomerDataController controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);
        stage.setTitle("Tu perfil");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public void initViewModifyDataCustomer(Customer customer, Stage view) throws IOException {
        System.out.println("Modificando el perfil.");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.MODIFY_DATA_CUSTOMER.get())));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        Stage stage = new Stage();
        ModifyCustomerDataController controller = fxmlLoader.getController();
        controller.setCustomer(customer);
        controller.setStage(stage);
        view.close();
        stage.setTitle("Modificar los datos.");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        if (!stage.isShowing()) {
            view.show();
        }

    }

    public boolean initProducto(boolean editarModo, Article producto) throws IOException {
        System.out.println("Iniciando....");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.NEW_ARTICLE.get()));
        Scene scene = new Scene(fxmlLoader.load(), 561, 507);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setTitle(editarModo ? "Editar Persona" : "Nueva Persona");
        stage.setResizable(false);
        ProductoController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        controller.setEditarModo(editarModo);
        controller.setProducto(producto);
        stage.setScene(scene);
        stage.showAndWait();
        return controller.isAceptarClicked();
    }

    public void initArticleView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.ARTICLE_VIEW.get())));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Stage stage = new Stage();
        stage.setTitle("VISTA PRODUCTOS MANAGER-EMPLEADO");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void initResume(Article producto) throws IOException {
        System.out.println("Viendo el resumen del pedido....");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.RESUME_ARTICLE.get()));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        stage.setScene(scene);
        ResumenController controller = fxmlLoader.getController();
        controller.setProducto(producto);
        stage.showAndWait();

    }

    public void initEmployee() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.TABLA_EMPLYEE.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); //TODO ESTO EVITA Q LA VENTANA DE DETRAS NO SE PUEDA TOCAR
        stage.setTitle("Empleados");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void initModifyEmployee(Employee employee) throws IOException {
        System.out.println(employee);
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.MODIFY_EMPLOYEE.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        EditarEmployeeController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        controller.setEmployee(employee);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setTitle("Editar - Empleados");
        stage.setScene(scene);
        stage.show();
    }

    public void initCarrito() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.VIEW_CARRITO.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        ViewCarritoController controller = fxmlLoader.getController();
        controller.setDialogStage(mainStage);
        controller.setStage(stage);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setTitle("Cesta de Productos");
        stage.setScene(scene);
        stage.show();
    }

    public void initSupplierView() throws IOException {
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.SUPPLIER_VIEW.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        SupplierVistaController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setResizable(false);
        stage.setTitle("Proveedores");
        stage.setScene(scene);
        stage.show();
    }

    public void initNewSuplier() throws IOException {
        System.out.println("Iniciando vista nuevo supplier");
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.NEW_SUPPLIER_VIEW.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Nuevo Proveedor");
        stage.setScene(scene);
        stage.show();

    }

    public void initModificarSuplier(Supplier supplier) throws IOException {
        System.out.println("Iniciando vista modificación supplier");
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.MODIFY_DATA_SUPPLIER.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        EditarSuplierController controller = fxmlLoader.getController();
        controller.setSupplier(supplier);
        stage.setResizable(false);
        stage.setTitle("Modificar Proveedor");
        stage.setScene(scene);
        stage.show();
    }

    public void initOrderView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(HelloApplication.class.getResource(Views.ORDER_VIEW.get())));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("VISTA PRODUCTOS MANAGER-EMPLEADO");
        stage.setResizable(false);
        stage.setTitle("Pedidos");
        stage.setScene(scene);
        stage.show();
    }
    public void initReception() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(HelloApplication.class.getResource(Views.RECEPTION_VIEW.get())));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("VISTA PRODUCTOS MANAGER-EMPLEADO");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void initLineOrderView(Order order) throws IOException, SQLException {
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.LINE_ORDER_VIEW.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        ResumenOrderController controller = fxmlLoader.getController();
        controller.setOrder(order);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void initResumeReception(Reception x) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.RESUME_RECEPTION.get()));
        Scene scene = new Scene(fxmlLoader.load(), 657, 481);
        Stage stage = new Stage();
        ResumenReceptionController controller = fxmlLoader.getController();
        controller.setRecepcion(x);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(scene);

        stage.showAndWait();

    }
}
