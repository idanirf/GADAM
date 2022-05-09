package com.dam.gestionalmacendam.servicies.backup;

import com.dam.gestionalmacendam.models.Backup;

public interface IStorageBackup<T> {

    boolean save(Backup backup);

    T load();

    String getStoragePath();

}
