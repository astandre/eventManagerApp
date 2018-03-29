package com.example.andre.eventmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.andre.eventmanager.parsers.EventParser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EventoCategoriaActivity extends AppCompatActivity {
String url;
    ArrayList<Evento> lstEventos = new ArrayList<>();
    private static RecyclerView recycler;
    private static RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager lManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_categoria);
        int cod  = getIntent().getIntExtra("cod_categoria",0);
        cod = cod +1;
        Log.d("test",String.valueOf(cod));
//        Log.d("test",cod);
        url ="http://" + Constants.BASE_URL +"/api/evento/categoria/"+cod;

        try {

                            run();
//                            obtener locales desde el api

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
//        Content-Type: application/json

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(EventoCategoriaActivity.this,Constants.ERROR_CONNECTION,Toast.LENGTH_SHORT);
//                swiperefresh.setRefreshing(false);
                call.cancel();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                final String myResponse = response.body().string();

                EventoCategoriaActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("FNC","Succes getting data");
                        try {
                            JSONArray dataJSON = new JSONArray(myResponse);
                            if(dataJSON.length()==0){
                                Toast.makeText(EventoCategoriaActivity.this, Constants.ERROR_CATEGORIA_NOT_FOUND, Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                lstEventos.clear();
                                lstEventos = EventParser.orderingEvents(myResponse);
                                createEvents();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
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
                        Intent intent = new Intent(v.getContext(), EventActivity.class);
                        intent.putExtra("Evento", lstEventos.get(getPosition()));
                        v.getContext().startActivity(intent);
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

    private void createEvents() {
        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.evento_categoria_reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new EventsAdapter(lstEventos);
        recycler.setAdapter(adapter);
    }

    }
