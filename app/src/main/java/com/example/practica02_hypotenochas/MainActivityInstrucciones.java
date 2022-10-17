package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityInstrucciones extends AppCompatActivity {
    Intent entrada;
    Intent salida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_instrucciones);
        entrada = getIntent();
    }

    public void onClickVolverInst(View view){
        salida = new Intent(this,MainActivity.class);
        startActivity(salida);
    }
}
