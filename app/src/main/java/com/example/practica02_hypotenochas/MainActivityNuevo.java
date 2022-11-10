package com.example.practica02_hypotenochas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivityNuevo extends AppCompatActivity {
    Intent entrada;
    Intent salida;
    int icon, casillas, numMinas;
    //

    int filasTablero;
    int marcadas;
    int encontradas;
    //
    Cronometro cronometro;
    TextView tvcronometro;
    TextView tvminas;
    String tiempoTranscurrido;
    //


    //
    private LinearLayout tablero;
    buscaBurgers juego;
    TreeMap<Integer, Integer> game;
    TreeMap<Integer, Integer> tableroMinado;
    boolean perdido = false;
    boolean ganado = false;
    //
    boolean clicado = false;

    List<Button> botones = new ArrayList<>();

    /**
     * Clase que almacena los datos de la actividad, sirve para colocar el código de inicialización.
     * Almacena la lógica básica de inicio de la aplicación que debería suceder solo una vez
     * durante toda la vida de la actividad.
     *
     * @param savedInstanceState referencia a un objeto Bundle que se pasa al método.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nuevo);

        entrada = getIntent();
        Bundle b = entrada.getExtras();
        icon = b.getInt("personaje");
        if (b.getInt("casillas") == 0) {
            casillas = 64;
            numMinas = 10;
        } else {
            casillas = b.getInt("casillas");
            numMinas = b.getInt("minas");
        }
        filasTablero = (int) Math.sqrt(casillas);

        tablero = findViewById(R.id.tablero);
        tablero.setOrientation(LinearLayout.HORIZONTAL);
        anadeLayouts();
        juego = new buscaBurgers(casillas, numMinas, filasTablero);
        tableroMinado = juego.generaTablero(casillas, numMinas);

        tvcronometro = findViewById(R.id.tiempo);
        cronometro = new Cronometro(tvcronometro);
        new Thread(cronometro).start();
        tvminas = findViewById(R.id.minastv);

        //Si no se descubren todas las minas en una hora se PIERDE
        if(String.valueOf(tvcronometro.getText()).equalsIgnoreCase("59:59")){
            derrota();
        }

    }
    //Si la app pierde el foco se pausa el cronometro
    @Override
    protected void onStop() {
        super.onStop();
        cronometro.pause();
    }
    //Si volvemos a la app se reanuda el cronometro
    @Override
    protected void onRestart() {
        super.onRestart();
        cronometro.pause();
    }

    /**
     * Clase que genera los layouts correspondientes para generar una cuadricula  donde
     * se añadirán los botones generados automáticamente mediante dos "For" anidados.
     * Se le asigna un id a cada botón que corresponde con la variable (int)numBoton.
     * Los id se asignan de izquierda a derecha por filas:    1   2   3   4
     * 5   6   7   8
     * 9   10  11  12
     * 13  14  15  16
     */

    private void anadeLayouts() {
        int numBoton;
        for (int i = 0; i < filasTablero; i++) {
            numBoton = i;
            LinearLayout ll = new LinearLayout(this);
            ll.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1));
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.setId(View.generateViewId());
            numBoton++;
            for (int j = 0; j < filasTablero; j++) {
                Button button = new Button(this);
                button.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1));

                button.setId(numBoton);
                numBoton += filasTablero;
                button.setBackgroundResource(R.drawable.boton_frente);

                button.setOnClickListener(this::onClick);
                button.setOnLongClickListener(this::onLongClick);

                ll.addView(button);

                botones.add(button);
            }
            tablero.addView(ll);
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private boolean onLongClick(View view) {
        if (!clicado) {
            marcadas++;
            //marca una bomba
            view.setForeground(getResources().getDrawable(R.drawable.bobmarcador,getTheme()));
            clicado = true;
            view.setClickable(false);
            //comprobación de que la casilla marcada tiene una burguer debajo
            if (juego.tieneMina(view.getId())) {
                encontradas++;
                //comprobación de que se haya ganado el juego
                if (encontradas == numMinas) {
                    victoria();
                }
            }
        } else {
            if(!view.isClickable())
                marcadas--;
            if (!juego.destapada(view.getId())) {

                view.setForeground(null);
                clicado = false;
                view.setClickable(true);
            }
        }
        tvminas.setText(String.valueOf(marcadas));
        return true;
    }

    /**
     * Método que controla qué botón se ha pulsado.
     *
     * @param view parámetro de tipo View que pertenece a la clase android.view.View.
     *             Permite controlar la interacción de la aplicación con el usuario.
     *             Cambia de botón los colores en función de cual ha sido pulsado:
     *             - Si es pulsado el último botón, cambia el color de todos los botones a un color
     *             generado de forma altatoria.
     *             - Si es pulsado cualquier otro de los "n" botones cambia el color del botón seleccionado.
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public void onClick(View view) {
        if (view.isClickable()) {
            game = juego.descubreCasillas(view.getId());
            //Se combrueba que la casilla seleccionada no contenga una bomba
            for (Map.Entry<Integer, Integer> minasEntry : tableroMinado.entrySet()) {

                if (minasEntry.getKey() == view.getId() && minasEntry.getValue() == 1) {
                    perdido = true;
                }
            }
            //descubre las minas si hemos perdido
            if (perdido) {
                derrota();

            } else {
                //Se descubren las casillas y se escribe el numero de bombas adyacentes

                for (Map.Entry<Integer, Integer> integerEntry : game.entrySet()) {
                    for (int i = 0; i < casillas; i++) {
                        Button b = botones.get(i);
                        if (b.getId() == integerEntry.getKey()) {
                            //efecto Rippler
                            b.setBackgroundResource(R.drawable.efectto_pulsar);
                            //espera 0.2 segundos
                            Handler handler = new Handler();
                            handler.postDelayed(() -> {
                                //cambio el fondo de los botones afectados
                                b.setBackgroundResource(R.drawable.boton_fondo);
                                //pon numeros en las casillas
                                int valor = integerEntry.getValue();
                                if (valor > 0) {
                                    b.setText(String.valueOf(integerEntry.getValue()));
                                    b.setTextSize(22);
                                    b.setTypeface(null, Typeface.BOLD);
                                    if (valor == 2) {
                                        b.setTextColor(getResources().getColor(R.color.verde,getTheme()));
                                    }
                                    if (valor > 2) {
                                        b.setTextColor(getResources().getColor(R.color.rojo,getTheme()));
                                    }
                                }
                                //fin espera
                            }, 200);
                        }
                    }
                }
            }
            game.clear();
            //comprobación de que se haya ganado el juego
            if (buscaBurgers.destapadas.size() == casillas - numMinas) {
                victoria();
            }
        }
    }

    /**
     *
     */
    public void victoria() {
        ganado = true;
        cronometro.pause();
        tiempoTranscurrido=String.valueOf(tvcronometro.getText());
        Handler handler = new Handler();
        //espera 2 segundos
           handler.postDelayed(() -> {
               System.out.println("nuevo"+casillas);
                salida = new Intent(this, MainActivityWin.class);
                salida.putExtra("personaje",icon);
                salida.putExtra("tiempo",tiempoTranscurrido);
                salida.putExtra("casillas",casillas);
                startActivity(salida);
                //fin espera
        }, 2000);

    }

    /**
     *
     */
    public void derrota() {
        cronometro.pause();
        Handler handler = new Handler();
        for (Map.Entry<Integer, Integer> minasEntry2 : tableroMinado.entrySet()) {
            for (int i = 0; i < casillas; i++) {
                int finalI = i;
                if ((minasEntry2.getKey() == (botones.get(finalI).getId())) && (minasEntry2.getValue() == 1)) {
                    //efecto rippler
                    botones.get(finalI).setForeground(getDrawable(R.drawable.efectto_pulsar));
                    //espera 0.2 segundos
                    handler.postDelayed(() ->
                    {
                        //descubre las bombas
                        botones.get(finalI).setBackground(getDrawable(R.drawable.boton_fondo));
                        botones.get(finalI).setForeground(getDrawable(R.drawable.bomburguesa));
                        //fin espera
                    }, 200);
                }
            }
        }
        //Espera 3 segundos
        handler.postDelayed(() -> {
            //Muestra pantalla de perdedor
            setContentView(R.layout.activity_main_looser);
            //espera 2 segundos
            handler.postDelayed(() -> {
                //Ve a a inicio
                salida = new Intent(this, MainActivity.class);
                startActivity(salida);
                //fin espera
            }, 3000);
            //fin espera
        }, 3000);

    }


}