package com.dam.gestionalmacendam.repositories.LineReception;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Optional;

interface SerachByReceptionsBelong <T,ID> {
    ObservableList<T> SerachByReceptionsBelong(ID identifier) throws SQLException;
}
