package com.dam.gestionalmacendam.repositories.LineOrder;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Optional;

public interface SearchByOrderBelongs<LineOrder,String>  {
    ObservableList<LineOrder> searchByUuidOrder(String identifier) throws SQLException;
}
