package com.dam.gestionalmacendam.repositories.LineReception;

import java.sql.SQLException;
import java.util.Optional;

interface SerachByReceptionsBelong <T,ID> {
    Optional<T> SerachByReceptionsBelong(ID identifier) throws SQLException;
}
