package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase que define la pantalla looser
 */
public class MainActivityLooser extends AppCompatActivity {
    Intent salida;

    /**
     * Método que almacena los datos de la actividad, sirve para colocar el código de inicialización.
     * Almacena la lógica básica de inicio de la aplicación que debería suceder solo una vez
     * durante toda la vida de la actividad.
     *
     * Muestra la pantalla looser espera 3 segundos y vuelve a la pantalla de inicio
     *
     * @param savedInstanceState referencia a un objeto Bundle que se pasa al método.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_looser);

        Handler handler = new Handler();
        //espera 3 segundos
        handler.postDelayed(() -> {
            //Ve a a inicio
            salida = new Intent(this, MainActivity.class);
            startActivity(salida);
            //transicion de izquierda a derecha
            overridePendingTransition(R.anim.right_in, R.anim.right_out);
            //fin espera
        }, 3000);
    }
}