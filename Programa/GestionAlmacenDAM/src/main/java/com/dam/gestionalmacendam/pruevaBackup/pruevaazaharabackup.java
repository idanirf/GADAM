package com.dam.gestionalmacendam.pruevaBackup;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class pruevaazaharabackup {
     private final Path rutaRelativa = Paths.get("");
     private final String rutaAbsuluta = rutaRelativa.toAbsolutePath().toString();
     private final String directorio = rutaAbsuluta + File.separator + "backup";
     private final String fileG = directorio + File.separator + "copiaDeSeguridadGson" + LocalDate.now();
    private final String fileB = directorio + File.separator + "copiaDeSeguridadBIN" + LocalDate.now();
    private final String fileA = directorio + File.separator + "copiaDeSeguridadAle" + LocalDate.now();

    File fichero = null;
    File ficheroB = null;
    File ficheroA = null;

    PrintWriter f = null;
    ObjectOutputStream fB = null;
    RandomAccessFile fA = null;

    public pruevaazaharabackup() {
        crearDir();
        crearFileGson();
        //no me va
       // crearFileBin();
        crearFileAleatorio();
    }

    public void crearDir() {
        Path path = Paths.get(directorio);
        if(!Files.exists(path)){
            try {
                Files.createDirectory(path) ;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void crearFileGson() {
        OrderRepository o = OrderRepository.getInstance(DataBaseManager.getInstance());
        fichero = new File(fileG);
        try {
            List<Order> order = o.findAll();
            f = new PrintWriter(new FileWriter(fichero, true));
            for (Order or : order) {
                f.println(or.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(f!=null){
                f.close();
            }
        }


    }
    public void crearFileBin() {
        OrderRepository o = OrderRepository.getInstance(DataBaseManager.getInstance());
        ficheroB = new File(fileB);
        try {
            List<Order> order = o.findAll();
             fB = new ObjectOutputStream( new FileOutputStream(fileB));
            fB.writeObject(order.get(0));

            for (Order or : order) {
                fB.writeObject(or);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(f!=null){
                try{
                    fB.close();
                }catch (Exception e){}

            }
        }


    }
    public void crearFileAleatorio() {
        OrderRepository o = OrderRepository.getInstance(DataBaseManager.getInstance());
        ficheroA = new File(fileA);
        try {
            List<Order> order = o.findAll();
            fA = new RandomAccessFile(ficheroA,"rw");
            for (Order or : order) {
                fA.writeBytes(or.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(f!=null){
                f.close();
            }
        }


    }

}
