package com.example.andre.eventmanager;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.example.andre.eventmanager.model.Evento;

import java.util.ArrayList;
import java.util.List;


public class MainAcivity extends AppCompatActivity {
    private static RecyclerView recycler;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    ArrayList<Evento> lstEventos = new ArrayList<>();
    public String url;
    private static SwipeRefreshLayout swiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("FNC", "Launching Event list");

//        TODO onclick categoria
//        url
        url = "http://" + Constants.BASE_URL + "/api/events/";
        lstEventos = (ArrayList<Evento>) getIntent().getSerializableExtra("lstEventos");
        //Tool bar
        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

//        createEvents();
//        swiperefresh = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
//        swiperefresh.setOnRefreshListener(
//                new SwipeRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        Log.i("test", "onRefresh called from SwipeRefreshLayout");

        // This method performs the actual data-refresh operation.
        // The method calls setRefreshing(false) when it's finished.
//                        try {
//
////                            run();
////
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//        );
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = new Bundle();

        bundle.putSerializable("lstEventos", lstEventos);
// set Fragmentclass Arguments
        CardEventosFragment objFragCards = new CardEventosFragment();
        objFragCards.setArguments(bundle);
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(objFragCards, "Eventos Proximos");
        adapter.addFragment(new CategoriaFragment(), "Categorias");
//        adapter.addFragment(new TileContentFragment(), "Tile");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<android.support.v4.app.Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intent = new Intent(MainAcivity.this, AboutAcivity.class);
            MainAcivity.this.startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


//    void run() throws IOException {
//        Log.d("FNC","Attempting to get data");
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Toast.makeText(MainAcivity.this,Constants.ERROR_CONNECTION,Toast.LENGTH_SHORT);
//                swiperefresh.setRefreshing(false);
//                call.cancel();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                final String myResponse = response.body().string();
//
//                MainAcivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("FNC","Succes getting data");
//                        lstEventos.clear();
//                        lstEventos = EventParser.orderingEvents(myResponse);
//                        swiperefresh.setRefreshing(false);
//                        createEvents();
//                    }
//                });
//
//            }
//        });
//    }

//    public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
//        private List<Evento> lstEventos;
//
//        public class EventsViewHolder extends RecyclerView.ViewHolder {
//            // Campos respectivos de un item
//            private TextView nombre;
//            private TextView fecha;
//            private TextView local;
//            //            TODO add categorias
////            TODO add family
//            private ImageView imagen;
//            private ImageView familiar;
//            private ImageView category1;
//            private ImageView category2;
//            private ImageView category3;
//            private RelativeLayout container_event;
//            private EventsViewHolder(View v) {
//                super(v);
//                nombre = (TextView) v.findViewById(R.id.nombre_event);
//                fecha = (TextView) v.findViewById(R.id.fecha_event);
//                local = (TextView) v.findViewById(R.id.local_event);
//                imagen = (ImageView) v.findViewById(R.id.imagen_event);
//                familiar = (ImageView) v.findViewById(R.id.family_event);
//                category1 = (ImageView) v.findViewById(R.id.category1);
//                category2 = (ImageView) v.findViewById(R.id.category2);
//                category3 = (ImageView) v.findViewById(R.id.category3);
//                container_event = (RelativeLayout) v.findViewById(R.id.container_event);
//                container_event.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(itemView.getContext(), lstEventos.get(getPosition()).getNombre(), Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(MainAcivity.this, EventActivity.class);
//                        intent.putExtra("Evento", lstEventos.get(getPosition()));
//                        MainAcivity.this.startActivity(intent);
//                        Log.d("FNC", "Opening new Event");
//                    }
//                });
//            }
//
//
//        }
////        public void setClickListener(ClickListener clickListener){
////            this.clicklistener = clickListener;
////        }
//
//        public EventsAdapter(List<Evento> lstEventos) {
//            this.lstEventos = lstEventos;
//        }
//
//        @Override
//        public int getItemCount() {
//            return lstEventos.size();
//        }
//
//        @Override
//        public EventsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//            View v = LayoutInflater.from(viewGroup.getContext())
//                    .inflate(R.layout.item_event, viewGroup, false);
//            return new EventsViewHolder(v);
//        }
//
//        @Override
//        public void onBindViewHolder(EventsViewHolder viewHolder, int i) {
//            Picasso.get().load(lstEventos.get(i).getImg())
//                    .resize(400, 210)
//                    .error(R.drawable.errorloading)
//                    .into(viewHolder.imagen);
//            viewHolder.nombre.setText(lstEventos.get(i).getNombre());
//            viewHolder.fecha.setText(new SimpleDateFormat("E, dd MMM '-' HH:mm a",
//                    new Locale("es", "ES"))
//                    .format(lstEventos.get(i).getFecha_inicio())
//                    .toUpperCase()
//                    .replace(".", ""));
//            viewHolder.local.setText(lstEventos.get(i).getLocal().getNombre());
////            Amigable para la familia
//            if ((lstEventos.get(i).getFamiliar())) {
//                viewHolder.familiar.setImageResource(R.drawable.family);
//            } else {
//                viewHolder.familiar.setImageResource(R.drawable.nofamily);
//            }
////Categorias
//            int[]  categorias = lstEventos.get(i).getCategorias().clone() ;
//           Tools.determinarCategoria(categorias[0],viewHolder.category1);
//           Tools.determinarCategoria(categorias[1],viewHolder.category2);
//           Tools.determinarCategoria(categorias[2],viewHolder.category3);
//
//        }
//    }

//private void createEvents(){
//    // Obtener el Recycler
//    recycler = (RecyclerView) findViewById(R.id.reciclador);
//    recycler.setHasFixedSize(true);
//
//// Usar un administrador para LinearLayout
//    lManager = new LinearLayoutManager(this);
//    recycler.setLayoutManager(lManager);
//
//// Crear un nuevo adaptador
//    adapter = new EventsAdapter(lstEventos);
//    recycler.setAdapter(adapter);
//}
}
