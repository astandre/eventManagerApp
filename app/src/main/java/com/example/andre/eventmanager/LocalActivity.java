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

import com.example.andre.eventmanager.model.Local;
import com.squareup.picasso.Picasso;

public class LocalActivity extends AppCompatActivity {
    Local objLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objLocal = (Local) getIntent().getSerializableExtra("local");
        setContentView(R.layout.activity_local);
        TextView nombre_local = (TextView) findViewById(R.id.nombre_local);
        TextView direccion_local = (TextView) findViewById(R.id.direccion_local);
//        TextView nombre_propietario = (TextView) findViewById(R.id.nombre_propietario);
        TextView telefono_local = (TextView) findViewById(R.id.telefono_local);
        TextView celular_local = (TextView) findViewById(R.id.celular_local);
        TextView email_local = (TextView) findViewById(R.id.email_local);

        ImageView img_local = (ImageView) findViewById(R.id.imagen_local);

        nombre_local.setText(objLocal.getNombre());
        direccion_local.setText(objLocal.getDireccion());
//        nombre_propietario.setText(objLocal.getPropietario());
        telefono_local.setText(objLocal.getTelefono());
        celular_local.setText(objLocal.getCelular());
        email_local.setText(objLocal.getEmail());
        Picasso.get().load(objLocal.getImg_local())
                .resize(400,300)
                .error(R.drawable.errorloading)
                .into(img_local);
        img_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalActivity.this, imageActivity.class);
                intent.putExtra("img", objLocal.getImg_local());
                LocalActivity.this.startActivity(intent);
                Log.d("FNC", "Opening new image View");
            }
        });
        telefono_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("FNC", "Opening dial");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: "+objLocal.getTelefono()));
                startActivity(intent);
            }
        });
        celular_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("FNC", "Opening dial");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: "+objLocal.getCelular()));
                startActivity(intent);
            }
        });
        RelativeLayout info_local = (RelativeLayout)findViewById(R.id.info_local);
        info_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("FNC", "Opening map");
                        Uri gmmIntentUri = Uri.parse("geo:"+objLocal.getLatitud_local()+","+objLocal.getLongitud_local());//latitud , longiutd
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                }, 1000);
            }
        });
//        TODO añadir la funcion de mapa
//        TODO add interaccion on click
//        TODO futuros eventos



    }
}
