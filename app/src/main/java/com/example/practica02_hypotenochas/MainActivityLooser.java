package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityLooser extends AppCompatActivity {
    Intent salida;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_looser);
        Handler handler = new Handler();
        //espera 3 segundos
        handler.postDelayed(() -> {
            //Ve a a inicio
            salida = new Intent(this, MainActivity.class);
            startActivity(salida);
            overridePendingTransition(R.anim.right_in, R.anim.right_out);
            //fin espera
        }, 3000);
    }
}