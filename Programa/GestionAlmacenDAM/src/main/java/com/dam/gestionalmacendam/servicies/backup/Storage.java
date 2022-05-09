package com.dam.gestionalmacendam.servicies.backup;

import com.dam.gestionalmacendam.servicies.IStorage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.dam.gestionalmacendam.models.Backup;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.dam.gestionalmacendam.utils.Properties;


public class Storage implements IStorage {
    private static Storage instance;

    private final Path currentRelativePath = Paths.get("");
    private final String ruta = currentRelativePath.toAbsolutePath().toString();
    private final String dir = ruta + File.separator + "BACKUP";
    private final String backupFile = dir + File.separator + "backup.json";


    private Storage() {
        makeDirectory();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    private void makeDirectory() {
        if (!Files.exists(Paths.get(Properties.DATOS))) {
            try {
                Files.createDirectory(Paths.get(Properties.DATOS));
                Files.createDirectory(Paths.get(Properties.BACKUP));
                Files.createDirectory(Paths.get(Properties.IMAGES));
                // Imagenes
            } catch (IOException e) {
            }
        }
    }


    @Override
    public boolean save(Backup backup) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        boolean result = false;
        PrintWriter f = null;
        try {
            f = new PrintWriter(new FileWriter(backupFile));
            f.println(gson.toJson(backup));
            result = true;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            result = false;
        } finally {
            if (f != null) {
                f.close();
            }
        }
        return result;
    }


    @Override
    public Backup load() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Backup backup = null;
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(backupFile));
            backup = gson.fromJson(reader, new TypeToken<Backup>() {
            }.getType());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        return backup;
    }

    @Override
    public String getStoragePath() {
        return backupFile;
    }
}
