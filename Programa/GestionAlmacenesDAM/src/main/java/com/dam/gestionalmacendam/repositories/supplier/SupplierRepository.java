package com.dam.gestionalmacendam.repositories.supplier;

import com.dam.gestionalmacendam.dto.SupplierDTO;
import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.sun.org.apache.xpath.internal.objects.XObjectFactory.create;

public class SupplierRepository implements ICRUDSupplier {
    private static SupplierRepository instance;
    private final ObservableList<Supplier> repository = FXCollections.observableArrayList();
    DataBaseManager bbdd = DataBaseManager.getInstance();

    private SupplierRepository(){
        //Constructor vacío
    }

    public static SupplierRepository getInstance(){
        if (instance == null){
            instance = new SupplierRepository();
        }
        return instance;
    }
    @Override
    public ObservableList<Supplier> findAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";
        bbdd.open();
        ResultSet resultado = bbdd.select(sql).orElseThrow(()-> new SQLException("Se ha producido un error obteniendo" +
                "los datos"));
        repository.clear();
        while (resultado.next()){
            repository.add(
                    new Supplier(
                            resultado.getString("SIC"),
                            resultado.getString("nameSupplier"),
                            resultado.getString("direction"),
                            resultado.getString("telephoneNumber"),
                            resultado.getString("email")
                    )
            );
        }
        bbdd.close();
        if (repository.isEmpty()){
            initData();
        }
        return repository;
    }

    private void initData() {
        if (repository.isEmpty()){
            create(new Supplier(UUID.randomUUID().toString(), "APPLE", "CL Madrid N14",
                    "69871236", "factory.spain@apple.com" ));
            create(new Supplier(UUID.randomUUID().toString(), "SAMSUNG", "CL Leganés N1",
                    "602147852", "factory.spain@samsung.com" ));
            create(new Supplier(UUID.randomUUID().toString(), "NIKE", "CL Zapatilla N12",
                    "602147852", "factory.spain@nike.com" ));
        }
    }

    @Override
    public Optional<Supplier> save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO Supplier (nameSupplier, direction, telephoneNumber, email)";
        bbdd.open();
        bbdd.insert(sql, supplier.getSIC(), supplier.getNameSupplier(), supplier.getDirection(), supplier.getTelephoneNumber(), supplier.getEmail());
        bbdd.close();
        return Optional.of(supplier);
    }

    @Override
    public Optional<Supplier> update(UUID uuid, Supplier supplier) throws SQLException {
        int i = repository.indexOf(supplier);
        String sql = "UPDATE Supplier SET nameSupplier = ?, direction = ?, telephoneNumber = ?, email = ? WHERE SIC = ?";
        bbdd.open();
        bbdd.update(sql, supplier.getNameSupplier(), supplier.getDirection(), supplier.getTelephoneNumber(), supplier.getEmail());
        return Optional.of(supplier);
    }

    //todo sí queremos recargar datos en memoria de programa
    public void reload() throws SQLException, IOException, ClassNotFoundException {
    }

    public Supplier findByUUID(UUID SIC)throws SQLException{
        return repository.stream().filter(supplier -> supplier.getSIC().equals(SIC)).findFirst().orElseThrow(
                ()-> new SQLException("No existe ningún proveedor con ese SIC"));
    }


}
