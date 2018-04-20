package com.example.andre.eventmanager.parsers;

import android.util.Log;

import com.example.andre.eventmanager.model.Categoria;
import com.example.andre.eventmanager.model.Evento;
import com.example.andre.eventmanager.model.Local;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EventParser {
    private static DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", new Locale("es", "ES"));

    public static ArrayList<Evento> orderingEvents(String data) {
        Log.d("FNC", "Making data in array list");
        ArrayList<Evento> lstEventos = new ArrayList<>();
        try {
            JSONArray dataJSON = new JSONArray(data);

            for (int i = 0; i < dataJSON.length(); i++) {
                JSONObject evento = dataJSON.getJSONObject(i);
                int id = Integer.parseInt(evento.getString("cod_evento"));
                String nombre = evento.getString("nombre_evento");
                String descripcion = evento.getString("descripcion_evento");
                String direccion = evento.getString("direccion_evento");
                String latitud_evento = evento.getString("latitud_evento");
                String longitud_evento = evento.getString("longitud_evento");
                String auxDate = evento.getString("fecha_inicio");
                auxDate = auxDate.replace("T", " ");
                Date fecha_inicio = formatDate.parse(auxDate);
                auxDate = evento.getString("fecha_fin");
                auxDate = auxDate.replace("T", " ");
                Date fecha_fin = formatDate.parse(auxDate);
                Boolean familiar = Boolean.valueOf(evento.getString("familiar"));
                String img = evento.getString("img");
                char categorias = evento.getString("nombre_categoria").charAt(0);
                JSONObject localAux = new JSONObject(evento.getString("local"));
                int cod_local = Integer.parseInt(localAux.getString("cod_local"));
                String nombre_local = localAux.getString("nombre_local");
                String direccion_local = localAux.getString("direccion_local");
                String latitud_local = localAux.getString("latitud_local");
                String longitud_local = localAux.getString("longitud_local");
//                String nombre_propietario = localAux.getString("nombre_propietario");
                String telefono_local = localAux.getString("telefono_local");
                String celular_local = localAux.getString("celular_local");
                String email_local = localAux.getString("email_local");
                String img_local = localAux.getString("img_local");
                Local local = new Local(cod_local, nombre_local, direccion_local,latitud_local,longitud_local, telefono_local, celular_local, email_local,img_local);
                Evento objEvento = new Evento(id, nombre, descripcion, direccion,latitud_evento,longitud_evento, fecha_inicio, fecha_fin, familiar, img, local, categorias);
                lstEventos.add(objEvento);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("FNC", "Events ready");
        return lstEventos;
    }
}
