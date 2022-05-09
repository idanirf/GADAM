package com.dam.gestionalmacendam.repositories.customer;

import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.repositories.CRUDRepository;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public interface ICustomerRepository extends CRUDRepository<Customer, UUID> {

    Customer findByUUID(UUID uuid) throws SQLException;
}
