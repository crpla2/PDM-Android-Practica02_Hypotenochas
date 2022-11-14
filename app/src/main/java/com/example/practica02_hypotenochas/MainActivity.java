package com.example.practica02_hypotenochas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase de la actividad principal de la app (Pantalla de inicio)
 */
public class MainActivity extends AppCompatActivity {
    Intent entrada, salida;
    boolean recargado = false;
    int icon, casillas, minas;
    Drawable icono;

    /**
     * Método que almacena los datos de la actividad, sirve para colocar el código de inicialización.
     * Almacena la lógica básica de inicio de la aplicación que debería suceder solo una vez
     * durante toda la vida de la actividad.
     *
     * @param savedInstanceState referencia a un objeto Bundle que se pasa al método.
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        icon = R.drawable.bob;

        //Recepcion de la información del intent de origen
        entrada = getIntent();
        Bundle b = entrada.getExtras();
        if (b != null) {
            recargado = true;
            icon = b.getInt("personaje");
            icono = getDrawable(icon);
            casillas = b.getInt("casillas");
            minas = b.getInt("minas");
        }

    }

    /**
     * Metodo para inflar el menu
     *
     * @param menu recibe como parametro un recurso de menu
     * @return (boolean) devuelve true cuando se crea
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflamos el menu
        getMenuInflater().inflate(R.menu.menu, menu);
        if (recargado) {
            menu.getItem(3).setIcon(icono);
        }
        return true;
    }

    /**
     * Metodo que define el comportamiento del menu
     *
     * @param item recibe el menuitem seleccionado
     * @return (boolean) devuelve true cuando un item ha sido seleccionado.
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ((item.getItemId())) {
            case R.id.instrucciones:
                //Abre la pantalla de instruciones
                salida = new Intent(this, MainActivityInstrucciones.class);
                startActivity(salida);
                //transicion de derecha a izquierda
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                break;
            case R.id.nuevo:
                //Abre la un nuevo juego
                salida = new Intent(this, MainActivityNuevo.class);
                //Envío de información al intent de destino
                salida.putExtra("personaje", icon);
                salida.putExtra("casillas", casillas);
                salida.putExtra("minas", minas);
                startActivity(salida);
                //transicion zoom
                overridePendingTransition(R.anim.jump, R.anim.jump_out);
                break;
            case R.id.config:
                //Abre la pantalla de configuración
                salida = new Intent(this, MainActivityConfig.class);
                //Envío de información al intent de destino
                salida.putExtra("personaje", icon);
                startActivity(salida);
                //transicion de derecha a izquierda
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                break;
            case R.id.perso:
                //Abre la pantalla de elección de personajes
                salida = new Intent(this, MainActivityPerso.class);
                startActivity(salida);
                //transicion de derecha a izquierda
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                break;
            case R.id.fama:
                //Abre la pantalla de marcadores
                salida = new Intent(this, MainActivityPoints.class);
                startActivity(salida);
                //transicion de derecha a izquierda
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                break;
            default:
                break;
        }
        return true;
    }
}