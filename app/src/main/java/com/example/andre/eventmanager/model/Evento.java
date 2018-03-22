package com.example.andre.eventmanager.model;


import java.lang.reflect.Array;
import java.util.Date;

/**
 * Created by andre on 23/10/2017.
 */

public class Evento implements java.io.Serializable {
    private int cod_evento;
    private String nombre;
    private String descripcion;
    private String latitud_evento;
    private String longitud_evento;
    private String direccion;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Boolean familiar;
    private String img;
    private Local local;
    private int[] categorias;

    public Evento(){

    }
    public Evento(int cod_evento, String nombre, String descripcion,String direccion, String latitud_evento,String longitud_evento, Date fecha_inicio, Date fecha_fin  , Boolean familiar, String img, Local local,int[] categorias) {
        super();
        this.cod_evento = cod_evento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.latitud_evento = latitud_evento;
        this.longitud_evento = longitud_evento;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.familiar = familiar;
        this.img = img;
        this.local=local;
        this.categorias = categorias;
    }


    public int getId() {
        return cod_evento;
    }

    public void setId(int id) {
        this.cod_evento = cod_evento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() { return direccion; }

    public String getLatitud_evento() {
        return latitud_evento;
    }

    public String getLongitud_evento() {
        return longitud_evento;
    }

    public int getCod_evento() {
        return cod_evento;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Boolean getFamiliar() {return familiar; }

    public void setFamiliar(Boolean familiar) {this.familiar = familiar; }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public int[] getCategorias() { return categorias; }
}
