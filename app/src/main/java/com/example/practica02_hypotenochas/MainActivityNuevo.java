package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityNuevo extends AppCompatActivity {
    Intent entrada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_personajes);
        entrada = getIntent();
    }
}