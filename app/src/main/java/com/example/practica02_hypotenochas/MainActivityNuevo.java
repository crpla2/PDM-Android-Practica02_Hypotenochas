package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityNuevo extends AppCompatActivity {
    Intent entrada;
    Intent salida;
    int icon,casillas,minas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nuevo);

        entrada = getIntent();
        Bundle b= entrada.getExtras();
        icon=b.getInt("personaje");
        if(b.getInt("casillas")==0){
            casillas=64;
            minas=10;
        }else{
            casillas=b.getInt("casillas");
            minas=b.getInt("minas");
        }

        Toast.makeText(getApplicationContext(),"Casillas:"+casillas, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"Minas:"+minas, Toast.LENGTH_LONG).show();
    }

    /*
    		int casillas = 64;
		int numMinas = 10;
		int posicion = 15;
		int filasTablero = 8;

		buscaminas juego= new buscaminas(casillas, numMinas, posicion, filasTablero);
		juego.generaTablero(casillas, numMinas);
		System.out.println(juego.descubreCasillas(posicion, filasTablero));
		System.out.println(juego.descubreCasillas(64, filasTablero));
     */
    public void onClickVolverInst(View view){
        salida = new Intent(this,MainActivity.class);
        salida.putExtra("personaje",icon);
        startActivity(salida);
    }

}