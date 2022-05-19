package com.dam.gestionalmacendam.servicies;

import com.dam.gestionalmacendam.models.Backup;

public interface IStorage<T> {

    boolean save(Backup backup);

    T load();

    String getStoragePath();

}
