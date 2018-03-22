package com.example.andre.eventmanager.model;

/**
 * Created by andre on 23/10/2017.
 */

public class Categoria implements java.io.Serializable{
    private int id_Categoria;
    private String nombre_Categoria;

    public Categoria(int id_Categoria, String nombre_Categoria) {
        this.id_Categoria = id_Categoria;
        this.nombre_Categoria = nombre_Categoria;
    }
    public Categoria(int id_Categoria  ) {
        this.id_Categoria = id_Categoria;
    }
    public Categoria(String nombre_Categoria){
        this.nombre_Categoria = nombre_Categoria;
    }

    public int getId_Categoria() {
        return id_Categoria;
    }

    public void setId_Categoria(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    public String getNombre_Categoria() {
        return nombre_Categoria;
    }

    public void setNombre_Categoria(String nombre_Categoria) {
        this.nombre_Categoria = nombre_Categoria;
    }
}
