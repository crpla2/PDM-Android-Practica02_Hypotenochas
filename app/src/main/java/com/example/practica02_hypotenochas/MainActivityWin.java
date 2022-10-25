package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityWin extends AppCompatActivity {
    private Intent entrada;
    private Intent salida;

    private TextView numPunt,numtiempo;
    private int puntuacion;

    private int icon;
    private String tiempo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_winner);

        numPunt=findViewById(R.id.tvPuntosNumeros);

        numtiempo=findViewById(R.id.tvTiempoNumeros);

        entrada = getIntent();
        Bundle b = entrada.getExtras();
        icon = b.getInt("personaje");
        tiempo= b.getString("tiempo");

        String[] split = tiempo.split(":");
       double tiempoEnSegundos=Integer.parseInt(split[0])*60+Integer.parseInt(split[1]);

        puntuacion=(int)(10000/(tiempoEnSegundos/10));

        numPunt.setText(String.valueOf(puntuacion));
        numtiempo.setText(tiempo);
    }
}