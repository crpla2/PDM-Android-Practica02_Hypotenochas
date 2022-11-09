package com.example.practica02_hypotenochas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Intent entrada,salida;
    boolean recargado=false;
    int icon,casillas,minas;
    Drawable icono;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        icon=R.drawable.bob;

        entrada = getIntent();
        Bundle b= entrada.getExtras();
                if (b != null) {
            recargado=true;
            icon=b.getInt("personaje");
            icono=getDrawable(icon);
            casillas=b.getInt("casillas");
            minas=b.getInt("minas");
        }

        System.out.println("icon:" + icon);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflamos el menu
        getMenuInflater().inflate(R.menu.menu, menu);
        if (recargado){
            menu.getItem(3).setIcon(icono);
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch ((item.getItemId())){
            case R.id.instrucciones:
                salida = new Intent(this,MainActivityInstrucciones.class);
                startActivity(salida);
                break;
            case R.id.nuevo:
                salida = new Intent(this,MainActivityNuevo.class);
                salida.putExtra("personaje",icon);
                salida.putExtra("casillas",casillas);
                salida.putExtra("minas",minas);
                startActivity(salida);
                break;
            case R.id.config:
                salida = new Intent(this,MainActivityConfig.class);
                salida.putExtra("personaje",icon);
                startActivity(salida);
                break;
            case R.id.perso:
                salida = new Intent(this,MainActivityPerso.class);
                startActivity(salida);
                break;
            case R.id.fama:
                salida = new Intent(this,MainActivityPoints.class);
                startActivity(salida);
                break;
            default:
                break;
        }
        return true;
    }
}