package com.example.practica02_hypotenochas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityPoints  extends AppCompatActivity {
    private Intent entrada,salida;
    private TextView tv;
    private int icon;
    private String puntos;
    private String tiempo;
    private String nombre;
    private int casillas;
    private SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_puntuacion);
        tv = findViewById(R.id.tvPuntuacion);
        entrada = getIntent();
        Bundle b = entrada.getExtras();
        if(b!=null){
        icon = b.getInt("personaje");
        puntos = b.getString("puntos");
        tiempo = b.getString("tiempo");
        nombre = b.getString("nombre");
        casillas= b.getInt("casillas");
        }

        /*
            USAR UN CARDVIEW PARA VER LA PUNTUACIÓN!!!!!!!!!
         */
        String nivel;
        switch (casillas){

            case 144:
                nivel="Amateur";
                break;
            case 256:
                nivel="Principiante";
                break;
            default:
                nivel="Principiante";
                break;
        }
        /*PARA BORRAR LA BASE DE DATOS:
        //deleteDatabase("Puntos");
        */
        //Creamos la base de datos y la tabla SI NO EXISTEN!!!

        db = openOrCreateDatabase("Puntos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS bdPuntuacion(Nombre VARCHAR,Puntos VARCHAR,Tiempo VARCHAR,Nivel VARCHAR,Icono INTEGER);");
        //añadimos la informacion a la base de datos
        if(b!=null)//comprobacion de que no venimos de la pantalla inicial
            db.execSQL("INSERT INTO bdPuntuacion VALUES('" + nombre + "',+'" + puntos + "',+'" + tiempo + "',+'"+nivel+"',+'"+icon+"')");
        //mostramos el resultado en el text view
        String result = "";
        Cursor c = null;

        try {
            c = db.rawQuery("SELECT * FROM bdPuntuacion ORDER BY Puntos DESC", null);
            while (c.moveToNext())
                result = result + "\n" + c.getString(0) + "\t\t\t\t\t" + c.getString(1) + "\t\t\t" + c.getString(2)+"\n";


        } catch (Exception e) {
            result = e.toString();
        } finally {
            c.close();
        }
        tv.setText(result);

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
    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
