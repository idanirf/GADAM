package com.dam.gestionalmacendam.utils;

import java.io.File;
import java.net.URI;

public class Properties {
    /*
        Ficheros de almacenamiento de datos del programa
         */
    private static final String APP_PATH = System.getProperty("user.dir");
    public static final String DATOS = APP_PATH + File.separator + "datos";

    public static final String BACKUP = DATOS + File.separator + "backup";
    public static final String BACKUP_FILE = BACKUP + File.separator + "copySecurity.bak";
    public static final String IMAGES = DATOS + File.separator + "images";


}