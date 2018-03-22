package com.example.andre.eventmanager.model;

/**
 * Created by andre on 23/10/2017.
 */

import java.util.ArrayList;

public class Local implements java.io.Serializable{
    private int id_local;
    private String nombre;
    private String direccion;
    private String latitud_local;
    private String longitud_local;
    private String propietario;
    private String telefono;
    private String celular;
    private String email;
    private String img_local;
//    private ArrayList<Evento> lstEventos;

    public Local(int id_local, String nombre, String direccion,String latitud_local, String longitud_local, String propietario, String telefono, String celular, String email,String img_local) {
        this.id_local = id_local;
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud_local = latitud_local;
        this.longitud_local = longitud_local;
        this.propietario = propietario;
        this.telefono = telefono;
        this.celular = celular;
        this.email = email;
        this.img_local = img_local;
    }

    public Local(String nombre) {
        this.nombre = nombre;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLatitud_local() {
        return latitud_local;
    }

    public String getLongitud_local() {
        return longitud_local;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg_local() {
        return img_local;
    }
//    public ArrayList<Evento> getLstEventos() {
//        return lstEventos;
//    }

//    public void setLstEventos(ArrayList<Evento> lstEventos) {
//        this.lstEventos = lstEventos;
//    }
}
