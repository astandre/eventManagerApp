package com.example.andre.eventmanager.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by andre on 23/10/2017.
 */

public class Evento implements Serializable/*,Parcelable*/{
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
    private char categoria;

    public Evento(){

    }
    public Evento(int cod_evento, String nombre ){
        this.cod_evento = cod_evento;
        this.nombre = nombre;
    }
    public Evento (Parcel in){
        readFromParcel(in);
    }
//    public Evento(Parcel source){
//        cod_evento=  source.readInt();
//                this.id = data[0];
//        nombre = source.readString();
//        descripcion = source.readString();
//        direccion = source.readString();
//        latitud_evento = source.readString();
//        longitud_evento = source.readString();
//        img = source.readString();

//        this.longitud_evento = data[5];
//        this.fecha_inicio = new SimpleDateFormat(data[6],
//                        new Locale("es", "ES"))
//                        .format(lstEventos.get(position).getFecha_inicio())
//                        .toUpperCase()
//                        .replace(".", ""));
//        this.fecha_fin = data[7];
//        this.familiar = data[8];
//        this.local=data[10];
//        this.categorias = data[11];
//            }
//
//    @Override
//       public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(cod_evento);
//        dest.writeString(nombre);
////        dest.writeString(descripcion);
////        dest.writeString(latitud_evento);
////        dest.writeString(longitud_evento);
//////       dest.writeBooleanArray(familiar);
////       dest.writeString(img);
////       dest.writeIntArray(categorias);
//
//           }
    private void readFromParcel(Parcel in) {
        cod_evento = in.readInt();
        nombre = in.readString();

    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//    public static final Creator<Evento> CREATOR = new Creator<Evento>() {
//        @Override
//        public Evento[] newArray(int size) {
//            return new Evento[size];
//        }
//
//        @Override
//        public Evento createFromParcel(Parcel source) {
//            return new Evento(source);
//        }
//    };

    public Evento(int cod_evento, String nombre, String descripcion,String direccion, String latitud_evento,String longitud_evento, Date fecha_inicio, Date fecha_fin  , Boolean familiar, String img, Local local,char categoria) {
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
        this.categoria = categoria;
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

    public char getCategorias() { return categoria; }
}
