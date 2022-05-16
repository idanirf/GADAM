package com.dam.gestionalmacendam;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.models.Supplier;
import com.dam.gestionalmacendam.repositories.customer.CutomerRepository;
import com.dam.gestionalmacendam.repositories.supplier.SupplierRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        CutomerRepository repo = CutomerRepository.getInstance();
        Customer custo = new Customer(UUID.randomUUID().toString(), "pepe","ramos", "1","avaux","3332211",
                "ramos@gmail.com", "x", LocalDateTime.now());

       try {
           repo.save(custo);
       }catch(SQLException e){
           e.printStackTrace();
       }

        SupplierRepository supplierRepository = SupplierRepository.getInstance(DataBaseManager.getInstance());
        var supplier1 = new Supplier(UUID.randomUUID().toString(), "NOVATABLA S.L,","Calle MDZ N12", "678908765",
                "novatabla.mad@novatabla.es");
        supplierRepository.save(supplier1);
        System.out.println("Viejo: " + supplier1);
        supplier1.setDirection("LA NUEVA CALLE");
        supplier1.setTelephoneNumber("657908654");
        supplier1.setEmail("lanuevacalle@mesatabla.com");
        supplierRepository.update(supplier1.getSIC(),supplier1);
        System.out.println("Nuevo: " + supplierRepository.findByUUID(supplier1.getSIC()));
        //launch();
        checkServer();
    }

    private static void checkServer() {
        System.out.println("Comprobamos la conexión al Servidor BD");
        DataBaseManager controller = DataBaseManager.getInstance();
        try {
            controller.open();
            Optional<ResultSet> rs = controller.select("SELECT 'Hello world'");
            if (rs.get().next()) {
                controller.close();
                System.out.println("Conexión con la Base de Datos realizada con éxito");
                System.exit(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }
    }
}