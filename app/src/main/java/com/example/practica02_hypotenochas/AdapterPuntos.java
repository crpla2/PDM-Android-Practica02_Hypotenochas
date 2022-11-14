package com.example.practica02_hypotenochas;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Clase que define el adaptador para el reciclerview.
 */
public class AdapterPuntos extends RecyclerView.Adapter<AdapterPuntos.ViewHolder> {

    private final ArrayList<Jugador> lista;

    /**
     * Constructor
     *
     * @param lista recibe como parametros un lista de Jugadores
     */
    public AdapterPuntos(ArrayList<Jugador> lista) {
        this.lista = lista;
    }

    /**
     * Metodo que sirve para inflar el layout con cada elemento del reciclerview
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Metodo que sirve para setear los datos de los textview y de los imageview de los card layout
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Jugador jugador = lista.get(position);
        holder.tvPosicion.setText((position + 1) + "º");
        holder.tvNombre.setText(jugador.getNombre());
        holder.tvTiempo.setText(jugador.getTiempo());
        holder.tvPuntos.setText(String.valueOf(jugador.getPuntos()));
        holder.ivIcono.setImageResource(jugador.getIcon());
        System.out.println(jugador.getNivel());

        switch (jugador.getNivel()) {
            case 64:
                holder.tvNivel.setBackgroundResource(R.drawable.bronce);
                break;
            case 144:
                holder.tvNivel.setBackgroundResource(R.drawable.plata);
                break;
            case 256:
                holder.tvNivel.setBackgroundResource(R.drawable.oro);
                break;
        }
    }

    /**
     * Metodo que devuelve el número de elementos card del reciclerview
     */
    @Override
    public int getItemCount() {
        return lista.size();
    }

    /**
     * Clase viewholder que inicializa los textview y los imageview
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivIcono;
        private final TextView tvNombre;
        private final TextView tvTiempo;
        private final TextView tvPuntos;
        private final TextView tvNivel;
        private final TextView tvPosicion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcono = itemView.findViewById(R.id.ivIcono);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTiempo = itemView.findViewById(R.id.tvTiempoCard);
            tvPuntos = itemView.findViewById(R.id.tvPuntosCard);
            tvNivel = itemView.findViewById(R.id.tvNivel);
            tvPosicion = itemView.findViewById(R.id.textViewPosicion);
        }
    }
}

