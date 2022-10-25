package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityWin extends AppCompatActivity {
    private Intent salida;

    private TextView numPunt,numtiempo;
    private int puntuacion;

    private int icon;
    private int casillas;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_winner);

        numPunt=findViewById(R.id.tvPuntosNumeros);

        numtiempo=findViewById(R.id.tvTiempoNumeros);

        Intent entrada = getIntent();
        Bundle b = entrada.getExtras();
        icon = b.getInt("personaje");
        String tiempo = b.getString("tiempo");
        casillas=b.getInt("casillas");

        String[] split = tiempo.split(":");
        double tiempoEnSegundos=Integer.parseInt(split[0])*60+Integer.parseInt(split[1]);

        puntuacion=(int)(10000/(tiempoEnSegundos/10)*(casillas/10));

        numPunt.setText(String.valueOf(puntuacion));
        numtiempo.setText(tiempo);
    }
}