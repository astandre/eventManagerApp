package com.example.andre.eventmanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.andre.eventmanager.model.Local;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;



public class CardLocalesFragment extends Fragment {

    private List<Local> lstAuxLocales;

    //   public CardEventosFragment(List<Evento> lstAuxEventos){
//
//   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        lstAuxLocales = (ArrayList<Local>) getArguments().getSerializable("lstLocales");
        Log.d("Cards",String.valueOf(lstAuxLocales.size()));
        LocalesAdapter adapter = new LocalesAdapter(lstAuxLocales);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    public class LocalesAdapter extends RecyclerView.Adapter<LocalesAdapter.LocalesViewHolder> {
        private List<Local> lstLocales;

        public class LocalesViewHolder extends RecyclerView.ViewHolder {
            // Campos respectivos de una card
            private TextView nombre;

            private ImageView imagen;

            private RelativeLayout local_card_container;

            private LocalesViewHolder(View v) {
                super(v);
                nombre = (TextView) v.findViewById(R.id.nombre_local_item);

                imagen = (ImageView) v.findViewById(R.id.imagen_local_item);

                local_card_container = (RelativeLayout) v.findViewById(R.id.local_card_container);
                local_card_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Intent intent = new Intent(getContext(), LocalActivity.class);
                        intent.putExtra("local", lstLocales.get(getPosition()));
                        getContext().startActivity(intent);
                        Log.d("FNC", "Opening local view");

                    }
                });
            }


        }


        public LocalesAdapter(List<Local> lstLocales) {
            this.lstLocales = lstLocales;
        }

        @Override
        public int getItemCount() {
            return lstLocales.size();
        }

        @Override
        public LocalesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_local, viewGroup, false);
            return new LocalesViewHolder(v);
        }

        @Override
        public void onBindViewHolder(LocalesViewHolder viewHolder, int i) {
            Picasso.get().load(lstLocales.get(i).getImg_local())
                    .resize(400, 210)
                    .error(R.drawable.errorloading)
                    .into(viewHolder.imagen);
            viewHolder.nombre.setText(lstLocales.get(i).getNombre());

        }
    }


}
