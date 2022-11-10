package com.example.practica02_hypotenochas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityConfig extends AppCompatActivity {
    Intent salida,entrada;
    int icon,casillas,minas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_config);
        entrada = getIntent();
        Bundle b = entrada.getExtras();
       icon = b.getInt("personaje");

    }

    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view){
        switch (view.getId()){
            case R.id.radioButtonPrincipiante:
                casillas=64;
                minas=10;
                break;
            case R.id.radioButtonAmateur:
                casillas=144;
                minas=30;
                break;
            case R.id.radioButtonAvanzado:
                casillas=256;
                minas=60;
                break;
        }Toast.makeText(getApplicationContext(),"Casillas:"+casillas+"Minas:"+minas, Toast.LENGTH_LONG).show();

    }
    public void onClickVolverConfig(View view){
        System.out.println(casillas);
        salida = new Intent(this,MainActivityNuevo.class);
        salida.putExtra("personaje",icon);
        salida.putExtra("casillas",casillas);
        salida.putExtra("minas",minas);
        startActivity(salida);
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
