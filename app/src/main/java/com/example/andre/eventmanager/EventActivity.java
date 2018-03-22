package com.example.andre.eventmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andre.eventmanager.model.Evento;
import com.example.andre.eventmanager.parsers.EventParser;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EventActivity extends AppCompatActivity {
    public String url;
    Evento objEvento ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        objEvento = (Evento) getIntent().getSerializableExtra("Evento");
        Log.d("FNC", "Event Activity");
        setContentView(R.layout.activity_event);
//                        Create objects for the view
        TextView nombre = (TextView) findViewById(R.id.nombre);
        TextView hint_month = (TextView) findViewById(R.id.hint_month);
        TextView hint_day = (TextView) findViewById(R.id.hint_day);
        TextView fecha = (TextView) findViewById(R.id.fecha);
        TextView fecha_hint = (TextView) findViewById(R.id.fecha_hint);
        TextView local = (TextView) findViewById(R.id.local);
        TextView direccion = (TextView) findViewById(R.id.direccion);
        TextView descripcion = (TextView) findViewById(R.id.descripcion);
        ImageView imagen = (ImageView) findViewById(R.id.imagen);
//                        Setting data in the view
        nombre.setText(objEvento.getNombre());

        hint_month.setText(new SimpleDateFormat("MMM",
                new Locale("es", "ES"))
                .format(objEvento.getFecha_inicio())
                .toUpperCase()
                .replace(".", ""));
        hint_day.setText(new SimpleDateFormat("dd",
                new Locale("es", "ES"))
                .format(objEvento.getFecha_inicio()));
        String fechaEvento = new SimpleDateFormat("E, dd MMM kk:mm",
                new Locale("es", "ES"))
                .format(objEvento.getFecha_inicio()) + " - " +
                new SimpleDateFormat("E, dd MMM kk:mm",
                        new Locale("es", "ES"))
                        .format(objEvento.getFecha_fin());
        fechaEvento = fechaEvento.toUpperCase()
                .replace(".", "");
        fecha.setText(fechaEvento);


        fecha_hint.setText("Esta semana");// TODO arreglar el hint de la fecha
        local.setText(objEvento.getLocal().getNombre());
        direccion.setText(objEvento.getDireccion());
        descripcion.setText(objEvento.getDescripcion());

        Picasso.get().load(objEvento.getImg())
                .resize(400, 300)
                .error(R.drawable.errorloading)
                .into(imagen);

        imagen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(EventActivity.this, imageActivity.class);
                intent.putExtra("img", objEvento.getImg());
                EventActivity.this.startActivity(intent);
                Log.d("FNC", "Opening image view");
            }
        });

        RelativeLayout infoLocal = (RelativeLayout) findViewById(R.id.infoLocal);

        infoLocal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EventActivity.this, LocalActivity.class);
                intent.putExtra("local", objEvento.getLocal());
                EventActivity.this.startActivity(intent);
                Log.d("FNC", "Opening local view");
            }
        });

        RelativeLayout infoDireccion = (RelativeLayout)findViewById(R.id.infoDireccion);
        infoDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Uri gmmIntentUri = Uri.parse("geo:"+objEvento.getLatitud_evento()+","+objEvento.getLongitud_evento());//latitud , longiutd 37.7749,-122.4194
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                }, 1000);
            }
        });
//        TODO terminar
    }
}
