package com.example.practica02_hypotenochas;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

/**
 * Clase que implementa el cronómetro del juego.
 */
public class Cronometro implements Runnable {
    // Atributos privados de la clase
    private final TextView etiq; // Etiqueta para mostrar la información
    private int segundos, minutos; // Segundos y minutos que lleva activo el cronómetro
    private final Handler escribirenUI; // Necesario para modificar la UI
    private Boolean pausado; // Para pausar el cronómetro
    private String salida; // Salida formateada de los datos del cronómetro

    /**
     * Constructor de la clase
     *
     * @param etiqueta Etiqueta para mostrar información
     */
    public Cronometro(TextView etiqueta) {
        etiq = etiqueta;
        salida = "";
        segundos = 0;
        minutos = 0;
        escribirenUI = new Handler();
        pausado = Boolean.FALSE;
    }

    /**
     * Acción del cronómetro, contar tiempo en segundo plano
     */
    @Override
    public void run() {
        try {
            while (Boolean.TRUE) {
                Thread.sleep(1000);
                salida = "";
                if (!pausado) {
                    segundos++;
                    if (segundos == 60) {
                        segundos = 0;
                        minutos++;
                    }
                    // Formateo la salida
                    if (minutos <= 9) {
                        salida += "0";
                    }
                    salida += minutos;
                    salida += ":";
                    if (segundos <= 9) {
                        salida += "0";
                    }
                    salida += segundos;
                    // Por seguridad, como solo cuenta minutos,
                    // antes de llegar a la hora de espera se reinicia el cronometro
                    if (salida.equalsIgnoreCase("59:59"))
                        reiniciar();
                    // Modifico la UI
                    try {
                        escribirenUI.post(() -> etiq.setText(salida));
                    } catch (Exception e) {
                        Log.i("Cronometro", "Error en el cronometro al escribir en la UI: " + e);
                    }
                }
            }
        } catch (InterruptedException e) {
            Log.i("Cronometro", "Error en el cronometro: " + e);
        }
    }

    /**
     * Poner el cronometro a cero.
     */
    public void reiniciar() {
        segundos = 0;
        minutos = 0;
        pausado = Boolean.FALSE;
    }

    /**
     * Pausar/reiniciar el cronómetro.
     */
    public void pause() {
        pausado = !pausado;
    }
}