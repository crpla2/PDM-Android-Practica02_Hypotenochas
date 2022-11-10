package com.example.practica02_hypotenochas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
/////////////////CARDVIEW////////////////////////////////////////////
        // tv = findViewById(R.id.tvPuntuacion);
//////////////////////////////////////////////////////////////////

        entrada = getIntent();
        Bundle b = entrada.getExtras();
        if(b!=null){
        icon =b.getInt("personaje");
        puntos = b.getString("puntos");
        tiempo = b.getString("tiempo");
        nombre = b.getString("nombre");
        casillas= b.getInt("casillas");
        }

/////////////////////////CARDVIEW/////////////////////////////////////////
        RecyclerView RV = findViewById(R.id.RVPuntos);
        ArrayList<Jugador> lista = new ArrayList<Jugador>();
/////////////////////////////////////////////////////////////////////////
        int nivel;
        switch (casillas){
            case 144:
                nivel=R.drawable.plata;
                break;
            case 256:
                nivel=R.drawable.oro;
                break;
            default:
                nivel=R.drawable.bronce;
                break;
        }
        /*PARA BORRAR LA BASE DE DATOS:*/
       // deleteDatabase("Puntos");

        System.out.println(casillas);
System.out.println(nivel);

        //Creamos la base de datos y la tabla SI NO EXISTEN!!!

        db = openOrCreateDatabase("Puntos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS bdPuntuacion(Nombre VARCHAR,Puntos VARCHAR,Tiempo VARCHAR,Nivel INTEGER,Icono INTEGER);");
        //añadimos la informacion a la base de datos
        if(b!=null)//comprobacion de que no venimos de la pantalla inicial
            db.execSQL("INSERT INTO bdPuntuacion VALUES('" + nombre + "',+'" + puntos + "',+'" + tiempo + "',+'"+nivel+"',+'"+icon+"')");

        Cursor c = null;
        try {

            //PRI     c = db.rawQuery("SELECT * FROM bdPuntuacion WHERE Nivel=2131230843 ORDER BY Puntos DESC", null);
            //AMA    c = db.rawQuery("SELECT * FROM bdPuntuacion WHERE Nivel=2131230950 ORDER BY Puntos DESC", null);
         //AVA      c = db.rawQuery("SELECT * FROM bdPuntuacion WHERE Nivel=2131230847 ORDER BY Puntos DESC", null);
//
            c = db.rawQuery("SELECT * FROM bdPuntuacion ORDER BY Puntos DESC", null);
            while (c.moveToNext())
               lista.add(new Jugador(c.getString(0),c.getString(1),c.getString(2),c.getInt(3),
                        c.getInt(4)));
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            c.close();
        }


        AdapterPuntos adapter = new AdapterPuntos(this, lista);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RV.setLayoutManager(linearLayoutManager);
        RV.setAdapter(adapter);
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
        RecyclerView RV = findViewById(R.id.RVPuntos);
        ArrayList<Jugador> lista = new ArrayList<Jugador>();
        Cursor c = null;
        switch (item.getItemId()){
            case R.id.salir:
                salida=new Intent(this,MainActivity.class);
                startActivity(salida);
                break;
            case R.id.basura:

                deleteDatabase("Puntos");
                salida=new Intent(this,MainActivityPoints.class);
                startActivity(salida);
                break;
            case R.id.pri:

                try {

                    c = db.rawQuery("SELECT * FROM bdPuntuacion WHERE Nivel=2131230843 ORDER BY Puntos DESC", null);
                    while (c.moveToNext())
                        lista.add(new Jugador(c.getString(0),c.getString(1),c.getString(2),c.getInt(3),
                                c.getInt(4)));
                } catch (Exception e) {
                    System.out.println(e.toString());
                } finally {
                    c.close();
                }


                break;
            case R.id.ama:
                try {

                    c = db.rawQuery("SELECT * FROM bdPuntuacion WHERE Nivel=2131230951 ORDER BY Puntos DESC", null);
                    while (c.moveToNext())
                        lista.add(new Jugador(c.getString(0),c.getString(1),c.getString(2),c.getInt(3),
                                c.getInt(4)));
                } catch (Exception e) {
                    System.out.println(e.toString());
                } finally {
                    c.close();
                }
                break;
            case R.id.ava:
 try {

                c = db.rawQuery("SELECT * FROM bdPuntuacion WHERE Nivel=2131230948 ORDER BY Puntos DESC", null);
                while (c.moveToNext())
                    lista.add(new Jugador(c.getString(0),c.getString(1),c.getString(2),c.getInt(3),
                            c.getInt(4)));
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                c.close();
            }
                break;
            case R.id.all:
                salida=new Intent(this,MainActivityPoints.class);
                startActivity(salida);
                break;
            default:
                ;
                break;
        }
        AdapterPuntos adapter = new AdapterPuntos(this, lista);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RV.setLayoutManager(linearLayoutManager);
        RV.setAdapter(adapter);


        return true;
    }
    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

}