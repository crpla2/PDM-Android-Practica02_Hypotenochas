package com.example.practica02_hypotenochas;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

public class Cronometro implements Runnable
{
    // Atributos privados de la clase
    private TextView etiq; // Etiqueta para mostrar la información
    private int segundos, minutos; // Segundos, minutos y horas que lleva activo el cronómetro
    private Handler escribirenUI; // Necesario para modificar la UI
    private Boolean pausado; // Para pausar el cronómetro
    private String salida; // Salida formateada de los datos del cronómetro

    /**
     * Constructor de la clase
     * @param etiqueta Etiqueta para mostrar información
     */
    public Cronometro(TextView etiqueta)
    {
        etiq = etiqueta;
        salida = "";
        segundos = 0;
        minutos = 0;
        escribirenUI = new Handler();
        pausado = Boolean.FALSE;
    }

    @Override
    /**
     * Acción del cronómetro, contar tiempo en segundo plano
     */
    public void run()
    {
        try
        {
            while(Boolean.TRUE)
            {
                Thread.sleep(1000);
                salida = "";
                if( !pausado )
                {
                    segundos++;
                    if(segundos == 60)
                    {
                        segundos = 0;
                        minutos++;
                    }
                    // Formateo la salida
                    if( minutos <= 9 )
                    {
                        salida += "0";
                    }
                    salida += minutos;
                    salida += ":";
                    if( segundos <= 9 )
                    {
                        salida += "0";
                    }
                    salida += segundos;
                    // Por seguridad, como solo cuenta minutos,
                    // antes de llegar a la hora de espera se reinicia el cronometro
                    if (salida.equalsIgnoreCase("59:59"))
                        reiniciar();
                    // Modifico la UI
                    try
                    {
                        escribirenUI.post(() -> etiq.setText(salida));
                    }
                    catch (Exception e)
                    {
                        Log.i("Cronometro", "Error en el cronometro al escribir en la UI: " + e);
                    }
                }
            }
        }
        catch (InterruptedException e)
        {
            Log.i("Cronometro", "Error en el cronometro: " + e);
        }
    }
    public void reiniciar()
    {
        segundos = 0;
        minutos = 0;
        pausado = Boolean.FALSE;
    }
    public void pause()
    {
        pausado = !pausado;
    }
}