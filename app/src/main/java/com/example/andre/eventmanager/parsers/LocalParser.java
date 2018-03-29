package com.example.andre.eventmanager.parsers;


import android.util.Log;

import com.example.andre.eventmanager.model.Evento;
import com.example.andre.eventmanager.model.Local;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by andre on 28/3/2018.
 */

public class LocalParser {

    public static ArrayList<Local> orderingLocales(String data) {
        Log.d("FNC", "Making data in array list");
        ArrayList<Local> lstLocales = new ArrayList<>();
        try {
            JSONArray dataJSON = new JSONArray(data);

            for (int i = 0; i < dataJSON.length(); i++) {
                JSONObject local = dataJSON.getJSONObject(i);
                int id_local = Integer.parseInt(local.getString("cod_local"));
                String nombre_local = local.getString("nombre_local");
                String direccion_local = local.getString("direccion_local");
                String img_local = local.getString("img_local");
                String latitud_local = local.getString("latitud_local");
                String longitud_local = local.getString("longitud_local");
//                String nombre_propietario = local.getString("nombre_propietario");
                String telefono_local = local.getString("telefono_local");
                String celular_local = local.getString("celular_local");
                String email_local = local.getString("email_local");

                Local objLocal = new Local(id_local, nombre_local,
                        direccion_local,latitud_local,
                        longitud_local,telefono_local,celular_local,email_local,img_local);
                lstLocales.add(objLocal);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("FNC", "Locales ready");
        return lstLocales;
    }
}

