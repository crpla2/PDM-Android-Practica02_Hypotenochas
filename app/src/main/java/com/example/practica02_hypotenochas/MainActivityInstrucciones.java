package com.example.practica02_hypotenochas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityInstrucciones extends AppCompatActivity {
    //Intent entrada;
    Intent salida;

    //int icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_instrucciones);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflamos el menu
        getMenuInflater().inflate(R.menu.menu_points, menu);

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if ((item.getItemId())==R.id.salir){
            salida=new Intent(this,MainActivity.class);
            startActivity(salida);
        }
        return true;
    }
}
