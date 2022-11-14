package com.example.practica02_hypotenochas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase que define la pantalla de configuración
 */
public class MainActivityConfig extends AppCompatActivity {
    Intent salida, entrada;
    int icon, casillas, minas;

    /**
     * Método que almacena los datos de la actividad, sirve para colocar el código de inicialización.
     * Almacena la lógica básica de inicio de la aplicación que debería suceder solo una vez
     * durante toda la vida de la actividad.
     *
     * @param savedInstanceState referencia a un objeto Bundle que se pasa al método.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_config);

        //Recepcion de la información del intent de origen
        entrada = getIntent();
        Bundle b = entrada.getExtras();
        icon = b.getInt("personaje");
    }

    /**
     * Metodo que define el comportamiento del radiobutton
     *
     * @param view recibe como paratmetro el boton seleccionado
     */
    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.radioButtonPrincipiante:
                casillas = 64;
                minas = 10;
                break;
            case R.id.radioButtonAmateur:
                casillas = 144;
                minas = 30;
                break;
            case R.id.radioButtonAvanzado:
                casillas = 256;
                minas = 60;
                break;
        }
        //Muestra el número de casillas y las minas
        Toast.makeText(getApplicationContext(), "Casillas:" + casillas + "Minas:" + minas, Toast.LENGTH_LONG).show();
    }

    /**
     * Metodo que define el comportamiento del boton jugar
     *
     * @param view recibe el boton pulsado
     */
    public void onClickJugarConfig(View view) {
        //Abre un nuevo juego
        salida = new Intent(this, MainActivityNuevo.class);
        //Envío de información al intent de destino
        salida.putExtra("personaje", icon);
        salida.putExtra("casillas", casillas);
        salida.putExtra("minas", minas);
        startActivity(salida);
        //transicion zoom
        overridePendingTransition(R.anim.jump, R.anim.jump_out);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ((item.getItemId()) == R.id.salir) {
            //Abre la pantalla de inicio
            salida = new Intent(this, MainActivity.class);
            startActivity(salida);
            //transicion de izquierda a derecha
            overridePendingTransition(R.anim.right_in, R.anim.right_out);
        }
        return true;
    }
}
