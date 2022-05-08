package com.dam.gestionalmacendam.repositories.LinesPay;

import com.dam.gestionalmacendam.repositories.CRUDRepository;

import java.sql.SQLException;
import java.util.Optional;

public interface SearchByPay<T,ID>  {
    Optional<T> searchByUuidPay(ID identifier) throws SQLException;
}
