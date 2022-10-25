package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.StringTokenizer;

public class MainActivityWin extends AppCompatActivity {
    private Intent entrada;
    private Intent salida;

    private TextView tvpuntos;
    private int puntuacion;

    private int icon;
    private String tiempo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_winner);

        tvpuntos=findViewById(R.id.textViewpuntuacion);

        entrada = getIntent();
        Bundle b = entrada.getExtras();
        icon = b.getInt("personaje");
        tiempo= b.getString("tiempo");

        String[] split = tiempo.split(":");
       double tiempoEnSegundos=Integer.parseInt(split[0])*60+Integer.parseInt(split[1]);

        puntuacion=(int)(10000/(tiempoEnSegundos/10));

        tvpuntos.setText("Puntuación: "+puntuacion+"\nTiempo: "+tiempo);
    }
}