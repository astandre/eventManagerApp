package com.example.andre.eventmanager;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by andre on 18/3/2018.
 */

public class Constants {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            BASE_URL,
            ERROR_CONNECTION,
            ERROR_CATEGORIA_NOT_FOUND,

    })
    public @interface ServiceName {
    }

    public static final String BASE_URL = "192.168.3.118:8000";
    public static final String ERROR_CONNECTION = "Error de conexion";
    public static final String ERROR_CATEGORIA_NOT_FOUND = "No existen eventos dentro de la categoria";
}
