package com.dam.gestionalmacendam.repositories.customer;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class CutomerRepository implements ICustomerRepository{

    private static CutomerRepository instance;
    private final ObservableList<Customer> repository = FXCollections.observableArrayList();

    DataBaseManager db = DataBaseManager.getInstance();


    public static CutomerRepository getInstance() {
        if (instance == null) {
            instance = new CutomerRepository();
        }
        return instance;
    }


    @Override
    public ObservableList<Customer> findAll() throws SQLException {
        String sql = "SELECT * FROM Customer";
        ResultSet resultado = db.select(sql).orElseThrow(() -> new SQLException("Error al obtener todos los clientes."));
        repository.clear();
        while(resultado.next()){
            repository.add(
                    new Customer(
                            resultado.getString("CIC"),
                            resultado.getString("name"),
                            resultado.getString("surname"),
                            resultado.getString("cif"),
                            resultado.getString("direction"),
                            resultado.getString("telephoneNumber"),
                            resultado.getString("email"),
                            resultado.getString("photo"),
                            LocalDateTime.parse(resultado.getString("createdAt"))

                    )
            );
        }
        db.close();
        return repository;
    }

    @Override
    public Optional<Customer> save(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer (CIC,name,surname,cif,direction,telephoneNumber,email,photo,createdAt) VALUES (?,?,?,?,?,?,?,?,?)";
        db.open();
        db.insert(sql, customer.getCIC(),customer.getName(),customer.getSurname(),customer.getCif(),customer.getDirection(),customer.getTelephoneNumber(),customer.getEmail(),customer.getPhoto(),customer.getCreatedAt().toString());
        db.close();
        return Optional.of(customer);
    }

    @Override
    public Optional<Customer> update(UUID uuid, Customer customer) throws SQLException {
        int index = repository.indexOf(customer);
        String sql=  "UPDATE Customer SET name = ?, surname = ?, cif = ?, direction = ?, telephoneNumber= ?, email = ?, photo = ?, createdAt = ? WHERE CIC = ?";
        db.open();
        db.update(sql,customer.getName(),customer.getSurname(),customer.getCif(),customer.getDirection(),customer.getTelephoneNumber(),customer.getEmail(),customer.getPhoto(),customer.getCreatedAt(),customer.getCIC());
        db.close();
        return Optional.of(customer);
    }

    @Override
    public Customer findByUUID(UUID uuid) throws SQLException {
        return repository.stream().filter(customer -> customer.getCIC().equals(uuid)).findFirst().orElseThrow(()-> new SQLException("No existe el cliente con el UUID: "+ uuid));
    }
}
