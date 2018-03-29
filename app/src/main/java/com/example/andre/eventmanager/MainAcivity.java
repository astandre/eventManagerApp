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
import android.widget.Toast;


import com.example.andre.eventmanager.model.Evento;
import com.example.andre.eventmanager.model.Local;
import com.example.andre.eventmanager.parsers.LocalParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

import okhttp3.Request;
import okhttp3.Response;


public class MainAcivity extends AppCompatActivity {
    private static RecyclerView recycler;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    ArrayList<Evento> lstEventos = new ArrayList<>();
    ArrayList<Local> lstLocales = new ArrayList<>();
    public String url;
    public String urlLocales;
    private static SwipeRefreshLayout swiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("FNC", "Launching Event list");

//        TODO onclick categoria
//        url
        url = "http://" + Constants.BASE_URL + "/api/eventos/";
        urlLocales = "http://" + Constants.BASE_URL + "/api/locales/";
        lstEventos = (ArrayList<Evento>) getIntent().getSerializableExtra("lstEventos");
        //Tool bar
        // Adding Toolbar to Main screen


//        createEvents();
//        swiperefresh = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
//        swiperefresh.setOnRefreshListener(
//                new SwipeRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        Log.i("test", "onRefresh called from SwipeRefreshLayout");

        // This method performs the actual data-refresh operation.
        // The method calls setRefreshing(false) when it's finished.
        try {

//                            run();
//                            obtener locales desde el api
            getLocales();
//
//
        } catch (IOException e) {
            e.printStackTrace();
        }
//                    }
//                }
//        );
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager,  ArrayList<Evento> lstEventos,  ArrayList<Local> lstLocales) {
// Preparando el adaptador
        Adapter adapter = new Adapter(getSupportFragmentManager());
// Para los cards de Eventos
        Bundle bundle = new Bundle();
        bundle.putSerializable("lstEventos", lstEventos);
        CardEventosFragment objFragCards = new CardEventosFragment();
        objFragCards.setArguments(bundle);
        adapter.addFragment(objFragCards, "Eventos");
//Para los cards de Locales
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable("lstLocales", lstLocales);
        CardLocalesFragment objFragCardsLocal = new CardLocalesFragment();
        objFragCardsLocal.setArguments(bundle2);
        adapter.addFragment(objFragCardsLocal, "Locales");
//        Para las categorias
        adapter.addFragment(new CategoriaFragment(), "Categorias");
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

    void getLocales() throws IOException {
        Log.d("FNC", "Attempting to get data");
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlLocales)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainAcivity.this, Constants.ERROR_CONNECTION, Toast.LENGTH_SHORT);
                swiperefresh.setRefreshing(false);
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                MainAcivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("FNC", "Succes getting data");
                        lstLocales.clear();
                        lstLocales = LocalParser.orderingLocales(myResponse);
//                        Necesario para el toolbar
                        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                        setSupportActionBar(toolbar);
                        // Setting ViewPager for each Tabs
                        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
                        setupViewPager(viewPager,lstEventos,lstLocales);
                        // Set Tabs inside Toolbar
                        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
                        tabs.setupWithViewPager(viewPager);
//                        swiperefresh.setRefreshing(false);

                    }
                });

            }
        });
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

}
