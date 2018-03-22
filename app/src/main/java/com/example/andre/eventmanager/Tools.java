package com.example.andre.eventmanager;

import android.widget.ImageView;

import com.example.andre.eventmanager.R;

/**
 * Created by andre on 20/3/2018.
 */

public class Tools {
    public static void determinarCategoria(int cat, ImageView view){
        switch (cat){
            case 1:
                view.setImageResource(R.drawable.musica);
                break;
            case 2:
                view.setImageResource(R.drawable.art);
                break;
            case 3:
                view.setImageResource(R.drawable.gaming);
                break;
            case 4:
                view.setImageResource(R.drawable.politica);
                break;
            case 5:
                view.setImageResource(R.drawable.sports);
                break;

        }

    }
}
