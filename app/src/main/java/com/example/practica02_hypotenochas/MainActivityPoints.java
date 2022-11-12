package com.example.practica02_hypotenochas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivityPoints extends AppCompatActivity {
    private Intent entrada, salida;
    private TextView tv;
    private int icon;
    private int puntos;
    private String tiempo;
    private String nombre;
    private int casillas;
    private SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_puntuacion);
        entrada = getIntent();
        Bundle b = entrada.getExtras();
        if (b != null) {
            icon = b.getInt("personaje");
            puntos = b.getInt("puntos");
            tiempo = b.getString("tiempo");
            nombre = b.getString("nombre");
            casillas = b.getInt("casillas");
        }

/////////////////////////CARDVIEW/////////////////////////////////////////
        RecyclerView RV = findViewById(R.id.RVPuntos);
        ArrayList<Jugador> lista = new ArrayList<Jugador>();
/////////////////////////////////////////////////////////////////////////

        //Creamos la base de datos y la tabla SI NO EXISTEN!!!

        db = openOrCreateDatabase("Puntos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS bdPuntuacion(Nombre VARCHAR,Puntos INTEGER,Tiempo VARCHAR,Nivel INTEGER,Icono INTEGER);");
        //añadimos la informacion a la base de datos
        if (b != null)//comprobacion de que no venimos de la pantalla inicial
            db.execSQL("INSERT INTO bdPuntuacion VALUES('" + nombre + "',+'" + puntos + "',+'" + tiempo + "',+'" + casillas + "',+'" + icon + "')");

        Cursor c = null;
        try {
            c = db.rawQuery("SELECT * FROM bdPuntuacion ORDER BY Puntos DESC", null);
            while (c.moveToNext())
                lista.add(new Jugador(c.getString(0), c.getInt(1), c.getString(2), c.getInt(3),
                        c.getInt(4)));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            c.close();
        }


        AdapterPuntos adapter = new AdapterPuntos(this, lista);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RV.setLayoutManager(linearLayoutManager);
        RV.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflamos el menu
        getMenuInflater().inflate(R.menu.menu_points, menu);

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        salida = new Intent(this, MainActivityPoints.class);
        RecyclerView RV = findViewById(R.id.RVPuntos);
        ArrayList<Jugador> lista = new ArrayList<Jugador>();
        Cursor c = null;
        switch (item.getItemId()) {
            case R.id.salir:
                salida = new Intent(this, MainActivity.class);
                startActivity(salida);
                break;
            case R.id.basura:
                //Dialogo de alerta que salta para confirmar el borrado de la base de datos
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("\t\t¡ATENCIÓN!");
                alertDialog.setIcon(R.drawable.atencion);
                alertDialog.setMessage("Se borrarán todas las puntuaciones.\n¿Desea continuar?");

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);

                alertDialog.setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Borrado de la base de datos
                                deleteDatabase("Puntos");
                                //Recarga de la pantalla para visualizar los datos borrados
                                startActivity(salida);
                            }
                        });
                alertDialog.setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //recarga de la pantalla para visualizar los datos
                                startActivity(salida);
                                dialog.cancel();
                            }
                        });
                alertDialog.show();
                break;
            case R.id.pri:
                try {
                    c = db.rawQuery("SELECT * FROM bdPuntuacion WHERE Nivel=64 ORDER BY Puntos DESC", null);
                    while (c.moveToNext())
                        lista.add(new Jugador(c.getString(0), c.getInt(1), c.getString(2), c.getInt(3),
                                c.getInt(4)));
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    c.close();
                }

                break;
            case R.id.ama:
                try {
                    c = db.rawQuery("SELECT * FROM bdPuntuacion WHERE Nivel=144 ORDER BY Puntos DESC", null);
                    while (c.moveToNext())
                        lista.add(new Jugador(c.getString(0), c.getInt(1), c.getString(2), c.getInt(3),
                                c.getInt(4)));
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    c.close();
                }
                break;
            case R.id.ava:
                try {

                    c = db.rawQuery("SELECT * FROM bdPuntuacion WHERE Nivel=256 ORDER BY Puntos DESC", null);
                    while (c.moveToNext())
                        lista.add(new Jugador(c.getString(0), c.getInt(1), c.getString(2), c.getInt(3),
                                c.getInt(4)));
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    c.close();
                }
                break;
            case R.id.all:
                salida = new Intent(this, MainActivityPoints.class);
                startActivity(salida);
                break;
            default:
                break;
        }
        //Cargamos la pantalla con los datos filtrados
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