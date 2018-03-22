package com.example.andre.eventmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class imageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        String img = (String) getIntent().getSerializableExtra("img");
        setContentView(R.layout.activity_image);
        ImageView imagen = (ImageView) findViewById(R.id.image_banner);
        Picasso.get().load(img)
//                .resize(800,1000)
                .fit()
//                .centerCrop()
                .error(R.drawable.errorloading)
                .into(imagen);
//TODO comprobar que se pueda hacer zoom
    }
}
