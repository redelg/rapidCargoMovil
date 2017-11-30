package com.darkbox.rapidcargomov.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darkbox.rapidcargomov.Entidades.Encomienda;
import com.darkbox.rapidcargomov.R;

import java.util.ArrayList;

public class TrasladoAdapter extends RecyclerView.Adapter<TrasladoAdapter.ViewHolder> implements View.OnClickListener{

    private ArrayList<Encomienda> encomiendas;
    Context context;
    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    public TrasladoAdapter(Context context, ArrayList<Encomienda> _encomiendas) {
        this.encomiendas = _encomiendas;
        this.context = context;
    }

    @Override
    public TrasladoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_traslado, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        // Set strings to the views
        final TextView tvdescripcionOrden= (TextView) holder.view.findViewById(R.id.tv_descripcionOrden);
        final TextView tvfechaEntrega = (TextView) holder.view.findViewById(R.id.tv_fechaEntrega);
        final TextView tvRuta = (TextView) holder.view.findViewById(R.id.tv_Ruta);

        tvdescripcionOrden.setText(encomiendas.get(position).getDescripcionEncomienda());
        tvfechaEntrega.setText(encomiendas.get(position).getFechaLlegada());
        tvRuta.setText(encomiendas.get(position).getRuta().getNombreRuta());

    }

    @Override
    public int getItemCount() {
        return encomiendas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }
}
