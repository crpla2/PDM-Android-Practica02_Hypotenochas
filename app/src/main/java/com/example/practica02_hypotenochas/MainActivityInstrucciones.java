package com.example.practica02_hypotenochas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
/**
 * Clase que define la pantalla de instrucciones
 */
public class MainActivityInstrucciones extends AppCompatActivity {
    Intent salida;

    /**
     * Método que almacena los datos de la actividad, sirve para colocar el código de inicialización.
     * Almacena la lógica básica de inicio de la aplicación que debería suceder solo una vez
     * durante toda la vida de la actividad.
     *
     * @param savedInstanceState referencia a un objeto Bundle que se pasa al método.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_instrucciones);
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
        getMenuInflater().inflate(R.menu.menu_salida, menu);
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
        if ((item.getItemId()) == R.id.salir) {
            //Abre la pantalla de inincio
            salida = new Intent(this, MainActivity.class);
            startActivity(salida);
            //transicion de izquierda a derecha
            overridePendingTransition(R.anim.right_in, R.anim.right_out);
        }
        return true;
    }
}
