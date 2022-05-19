package com.dam.gestionalmacendam.repositories.Order;

import com.dam.gestionalmacendam.repositories.SearchByUuid;
import com.dam.gestionalmacendam.repositories.CRUDRepository;

public interface OrderInterface<Order, String> extends CRUDRepository, SearchByUuid {
}
