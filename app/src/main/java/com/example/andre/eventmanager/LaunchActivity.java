package com.example.andre.eventmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import android.util.Log;

import com.example.andre.eventmanager.model.Evento;
import com.example.andre.eventmanager.model.Local;
import com.example.andre.eventmanager.parsers.EventParser;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LaunchActivity extends AppCompatActivity {

    //    ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
    ArrayList<Evento> lstEventos = new ArrayList<>() ;
    public String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
// for Demo Only
//        for (int i =0; i < 8;i++){
//            Local objLocal = new Local(i,"Local "+i,"Direccion "+i,"000","000","Dueño "+i, "2300064", "0967561342", "email@test.com","img.jpg");
//            int [] categorias = {1,2,3};
//            Evento objEvento = new Evento(i,"Evento "+i,"Descripcion del evento","ubicacion del evento","0000","000", new Date(), new Date(), true, "img.jpg", objLocal, categorias);
//            lstEventos.add(objEvento);
//        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        url = "http://" + Constants.BASE_URL + "/api/eventos/";
//        Intent intent = new Intent(LaunchActivity.this, MainAcivity.class);
//        intent.putExtra("lstEventos", lstEventos); //Optional parameters
//        LaunchActivity.this.startActivity(intent);
//        finish();
        try {

            run();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void run() throws IOException {
        Log.d("FNC","Attempting to get data");
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                LaunchActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("FNC","Succes getting data");
                        lstEventos = EventParser.orderingEvents(myResponse);
                        Intent intent = new Intent(LaunchActivity.this, MainAcivity.class);
                        intent.putExtra("lstEventos", lstEventos); //Optional parameters
                        LaunchActivity.this.startActivity(intent);
                        finish();
                    }
                });

            }
        });
    }

////TODO arreglar API para recuperar todo
////TODO recuperar imagenes de link
////TODO termianr vistas dinamicas
////TODO hacer la barra de cargando info al inicio
////    TODO recuparar todo los datos de Eventos
//
//    }
}