package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityConfig extends AppCompatActivity {
    Intent salida;
    int icon,casillas,minas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_config);
    }

    public void onRadioButtonClicked(View view){
        switch (view.getId()){
            case R.id.radioButtonPrincipiante:
                casillas=64;
                minas=1;
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

        salida = new Intent(this,MainActivityNuevo.class);
        salida.putExtra("personaje",icon);
        salida.putExtra("casillas",casillas);
        salida.putExtra("minas",minas);
        startActivity(salida);
    }
}
