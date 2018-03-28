package com.example.andre.eventmanager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
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

import com.example.andre.eventmanager.model.Evento;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CardEventosFragment extends Fragment {

    private List<Evento> lstAuxEventos;

    //   public CardEventosFragment(List<Evento> lstAuxEventos){
//
//   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        lstAuxEventos = (ArrayList<Evento>) getArguments().getSerializable("lstEventos");
//        getActivity().getApplicationContext();
//        Log.d("test", String.valueOf(lstAuxEventos.size()));
//        Log.d("pos",lstAuxEventos.get(0).getNombre());
//        ContentAdapter adapter = new ContentAdapter(lstAuxEventos);
//        ContentAdapter adapter = new ContentAdapter(lstAuxEventos);
        EventsAdapter adapter = new EventsAdapter(lstAuxEventos);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
        private List<Evento> lstEventos;

        public class EventsViewHolder extends RecyclerView.ViewHolder {
            // Campos respectivos de una card
            private TextView nombre;
            private TextView fecha;
            private TextView local;
            private ImageView imagen;
            private ImageView familiar;
            private ImageView category1;
            private ImageView category2;
            private ImageView category3;
            private RelativeLayout container_event;

            private EventsViewHolder(View v) {
                super(v);
                nombre = (TextView) v.findViewById(R.id.nombre_event);
                fecha = (TextView) v.findViewById(R.id.fecha_event);
                local = (TextView) v.findViewById(R.id.local_event);
                imagen = (ImageView) v.findViewById(R.id.imagen_event);
                familiar = (ImageView) v.findViewById(R.id.family_event);
                category1 = (ImageView) v.findViewById(R.id.category1);
                category2 = (ImageView) v.findViewById(R.id.category2);
                category3 = (ImageView) v.findViewById(R.id.category3);
                container_event = (RelativeLayout) v.findViewById(R.id.container_event);
                container_event.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(itemView.getContext(), lstEventos.get(getPosition()).getNombre(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), EventActivity.class);
                        intent.putExtra("Evento", lstEventos.get(getPosition()));
                        getContext().startActivity(intent);
                        Log.d("FNC", "Opening new Event");
                    }
                });
            }


        }


        public EventsAdapter(List<Evento> lstEventos) {
            this.lstEventos = lstEventos;
        }

        @Override
        public int getItemCount() {
            return lstEventos.size();
        }

        @Override
        public EventsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_event, viewGroup, false);
            return new EventsViewHolder(v);
        }

        @Override
        public void onBindViewHolder(EventsViewHolder viewHolder, int i) {
            Picasso.get().load(lstEventos.get(i).getImg())
                    .resize(400, 210)
                    .error(R.drawable.errorloading)
                    .into(viewHolder.imagen);
            viewHolder.nombre.setText(lstEventos.get(i).getNombre());
            viewHolder.fecha.setText(new SimpleDateFormat("E, dd MMM '-' HH:mm a",
                    new Locale("es", "ES"))
                    .format(lstEventos.get(i).getFecha_inicio())
                    .toUpperCase()
                    .replace(".", ""));
            viewHolder.local.setText(lstEventos.get(i).getLocal().getNombre());
//          Amigable para la familia
            if ((lstEventos.get(i).getFamiliar())) {
                viewHolder.familiar.setImageResource(R.drawable.family);
            } else {
                viewHolder.familiar.setImageResource(R.drawable.nofamily);
            }
//Categorias
            int[] categorias = lstEventos.get(i).getCategorias().clone();
            Tools.determinarCategoria(categorias[0], viewHolder.category1);
            Tools.determinarCategoria(categorias[1], viewHolder.category2);
            Tools.determinarCategoria(categorias[2], viewHolder.category3);

        }
    }


}
