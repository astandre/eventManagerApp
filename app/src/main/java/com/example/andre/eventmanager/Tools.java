package com.example.andre.eventmanager;

import android.widget.ImageView;

import com.example.andre.eventmanager.R;

/**
 * Created by andre on 20/3/2018.
 */

public class Tools {
    public static void determinarCategoria(char cat, ImageView view) {
        switch (cat) {
            case 'M':
                view.setImageResource(R.drawable.musica);
                break;
            case 'T':
                view.setImageResource(R.drawable.art);
                break;
            case 'G':
                view.setImageResource(R.drawable.gaming);
                break;
            case 'P':
                view.setImageResource(R.drawable.politica);
                break;
            case 'D':
                view.setImageResource(R.drawable.sports);
                break;
            case 'F':
                view.setImageResource(R.drawable.party);
                break;

        }

    }

    public static char determinarCategoriaChar(int cat) {
        switch (cat) {
            case 0:
                return 'M';

            case 1:
                return 'T';

            case 2:
                return 'G';

            case 3:
                return 'P';

            case 4:
                return 'D';
            case 5:
                return 'F';

        }
        return 'Z';
    }
}
