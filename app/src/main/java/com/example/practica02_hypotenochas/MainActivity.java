package com.example.practica02_hypotenochas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent entrada,salida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salida = getIntent();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflamos el menu
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch ((item.getItemId())){
            case R.id.instrucciones:
                salida = new Intent(this,MainActivityInstrucciones.class);
                startActivity(salida);
                break;
            case R.id.nuevo:
                salida = new Intent(this,MainActivityNuevo.class);
                startActivity(salida);
                break;
            case R.id.config:
                salida = new Intent(this,MainActivityConfig.class);
                startActivity(salida);
                break;
            case R.id.perso:
                salida = new Intent(this,MainActivityPerso.class);
                startActivity(salida);
                break;
            default:
                break;
        }
        return true;
    }


}