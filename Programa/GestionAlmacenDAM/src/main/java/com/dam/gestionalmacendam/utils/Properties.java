package com.dam.gestionalmacendam.utils;

import java.io.File;

public class Properties {
    /*
        Ficheros de almacenamiento de datos del programa
         */
    private static final String APP_PATH = System.getProperty("user.dir");
    public static final String DATOS = APP_PATH + File.separator + "datos";

    public static final String BACKUP = DATOS + File.separator + "backup";
    public static final String BACKUP_FILE = BACKUP + File.separator + "copySecurity.json";
    public static final String IMAGES = DATOS + File.separator + "images";

    public static final int APP_HEIGHT = 720;
    public static final int APP_WIDTH = 1280;


}
