package com.example.practica02_hypotenochas;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityPoints  extends AppCompatActivity {
    private Intent entrada;
    private TextView tv;
    private int icon;
    private String puntos;
    private String tiempo;
    private String nombre;
    private SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_puntuacion);
        tv = findViewById(R.id.tvPuntuacion);
        entrada = getIntent();
        Bundle b = entrada.getExtras();
        icon = b.getInt("personaje");
        puntos = b.getString("puntos");
        tiempo = b.getString("tiempo");
        nombre = b.getString("nombre");
        /*
            USAR UN CARDVIEW PARA VER LA PUNTUACIÓN!!!!!!!!!
         */

        //Creamos la base de datos y la tabla SI NO EXISTEN!!!
        db = openOrCreateDatabase("MisDiscos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS bdPuntuacion(Nombre VARCHAR,Puntos VARCHAR,Tiempo VARCHAR);");
        //añadimos la informacion a la base de datos
        db.execSQL("INSERT INTO bdPuntuacion VALUES('" + nombre + "',+'" + puntos + "',+'" + tiempo + "')");
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
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
