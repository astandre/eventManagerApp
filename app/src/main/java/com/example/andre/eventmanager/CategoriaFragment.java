package com.example.andre.eventmanager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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

import com.example.andre.eventmanager.model.Categoria;


public class CategoriaFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon_categoria;
        public TextView name;
        private RelativeLayout list_container;

        public  ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_categoria_list, parent, false));
            icon_categoria = (ImageView) itemView.findViewById(R.id.list_icon);
            name = (TextView) itemView.findViewById(R.id.list_title);
             list_container = (RelativeLayout) itemView.findViewById(R.id.list_container);
            list_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), EventoCategoriaActivity.class);
                    intent.putExtra("cod_categoria",Tools.determinarCategoriaChar(getPosition()));
                    v.getContext().startActivity(intent);
                    Log.d("FNC", "Opening new Event");
                }
            });

        }
    }
    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static  int LENGTH=0;
        private final String[] categorias;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            categorias = resources.getStringArray(R.array.categorias);
            LENGTH = categorias.length;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            holder.name.setText(categorias[position]);
            Tools.determinarCategoria(Tools.determinarCategoriaChar(position), holder.icon_categoria);

        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
