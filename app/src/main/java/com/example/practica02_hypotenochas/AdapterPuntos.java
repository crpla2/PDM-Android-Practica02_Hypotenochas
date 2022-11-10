package com.example.practica02_hypotenochas;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterPuntos extends RecyclerView.Adapter<AdapterPuntos.ViewHolder> {

        private final Context context;
        private final ArrayList<Jugador> lista;
        Drawable drw;
        // Constructor
        public AdapterPuntos(Context context, ArrayList<Jugador> lista) {
            this.context = context;
            this.lista = lista;

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // to inflate the layout for each item of recycler view.
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);

            return new ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


            // to set data to textview and imageview of each card layout
            Jugador jugador = lista.get(position);
            holder.tvPosicion.setText(String.valueOf(position+1+"º"));
            holder.tvNombre.setText(jugador.getNombre());
            holder.tvTiempo.setText(jugador.getTiempo());
            holder.tvPuntos.setText(jugador.getPuntos());
            holder.ivIcono.setImageResource(jugador.getIcon());
            holder.tvNivel.setBackgroundResource(jugador.getNivel());

        }

        @Override
        public int getItemCount() {
            // this method is used for showing number of card items in recycler view
            return lista.size();
        }

        // View holder class for initializing of your views such as TextView and Imageview
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
                tvPuntos=itemView.findViewById(R.id.tvPuntosCard);
                tvNivel=itemView.findViewById(R.id.tvNivel);
                tvPosicion=itemView.findViewById(R.id.textViewPosicion);
            }
        }
    }

