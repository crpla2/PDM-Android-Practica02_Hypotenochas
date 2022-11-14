package com.example.practica02_hypotenochas;

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

/**
 * Clase que define el comportamiento de la pantalla "nuevo juego"
 */
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
    private LinearLayout tablero;
    buscaBurgers juego;
    TreeMap<Integer, Integer> game;
    TreeMap<Integer, Integer> tableroMinado;
    boolean perdido = false;
    boolean ganado = false;
    //
    List<Button> botones = new ArrayList<>();

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
        juego = new buscaBurgers(filasTablero);
        tableroMinado = juego.generaTablero(casillas, numMinas);

        tvcronometro = findViewById(R.id.tiempo);
        cronometro = new Cronometro(tvcronometro);
        new Thread(cronometro).start();
        tvminas = findViewById(R.id.minastv);
        marcadas = numMinas;
        tvminas.setText(String.valueOf(marcadas));
        //Si no se descubren todas las minas en una hora se PIERDE
        if (String.valueOf(tvcronometro.getText()).equalsIgnoreCase("59:59")) {
            derrota();
        }

    }

    /**
     * Método que define que ocurre cuando la catividad pierde el foco.
     * En este caso se pausa el cronómetro.
     */
    @Override
    protected void onStop() {
        super.onStop();
        cronometro.pause();
    }

    /**
     * Método que define que ocurre cuando la catividad recupera el foco.
     * En este caso se reanuda el cronómetro.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        cronometro.pause();
    }

    /**
     * Método que genera los layouts correspondientes para generar una cuadricula  donde
     * se añadirán los botones generados automáticamente mediante dos "For" anidados.
     * Se le asigna un id a cada botón que corresponde con la variable (int)numBoton.
     * Los id se asignan de izquierda a derecha por filas:    1   2   3   4
     *                                                        5   6   7   8
     *                                                        9   10  11  12
     *                                                        13  14  15  16
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

    /**
     * Método que define el comportamiento ante una pulsación larga.
     * En este caso marca la casilla donde creemos que hay una
     * cangreburguer si no ha sido marcada anteriormente, en
     * ese caso la desmarcaría.
     *
     * @param view recibe como parametro un objeto del tipo View.
     *             En este caso será el boton pulsado.
     * @return devuelve true cuando se ha producido una pulación larga.
     */
    private boolean onLongClick(View view) {
        //Si no ha sido marcada anteriormente
        if (view.isClickable()) {
            //Si no ha sido destapada
            if (!juego.destapada(view.getId())) {
                marcadas--;
                //marca una posible bomba
                view.setForeground(getResources().getDrawable(R.drawable.bobmarcador, getTheme()));
                //definela como marcada
                view.setClickable(false);
                //comprobación de que la casilla marcada tiene una burguer debajo
                if (juego.tieneMina(view.getId())) {
                    encontradas++;
                    //comprobación de que se haya ganado el juego
                    if (encontradas == numMinas) {
                        victoria();
                    }
                }
            }
        } else {//Si está marcada
            if (!view.isClickable())
                marcadas++;
            view.setForeground(null);
            view.setClickable(true);
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

    public void onClick(View view) {
        //Si no ha sido marcada
        if (view.isClickable()) {
            game = juego.descubreCasillas(view.getId());
            //bucle que impide que se pueda iniciar una partida destapando una bomba
            while (juego.getDestapadas().size() == 0) {
                //Se combrueba que la casilla seleccionada no contenga una bomba
                for (Map.Entry<Integer, Integer> minasEntry : tableroMinado.entrySet()) {
                    if (minasEntry.getKey() == view.getId() && minasEntry.getValue() == 1) {
                        tableroMinado = juego.generaTablero(casillas, numMinas);
                        this.onClick(view);
                    }
                }
            }
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
                            if (b.isClickable()) {
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
                                            b.setTextColor(getResources().getColor(R.color.verde, getTheme()));
                                        }
                                        if (valor > 2) {
                                            b.setTextColor(getResources().getColor(R.color.rojo, getTheme()));
                                        }
                                    }
                                    //fin espera
                                }, 200);
                            }
                        }
                    }
                }
            }
            game.clear();
            //comprobación de que se haya ganado el juego
            if (juego.getDestapadas().size() == casillas - numMinas) {
                victoria();
            }
        }
    }

    /**
     * Metodo que define el comportamiento del boton volver (piña).
     *
     * @param view recibe como parametro el boton
     */
    public void volver(View view) {
        salida = new Intent(this, MainActivity.class);
        startActivity(salida);
        //transicion de izquierda a derecha
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    /**
     * Metodo que define el compoprtamiento cuando se gana el juego
     */
    public void victoria() {
        ganado = true;
        cronometro.pause();
        tiempoTranscurrido = String.valueOf(tvcronometro.getText());
        Handler handler = new Handler();
        //espera 2 segundos
        handler.postDelayed(() -> {
            System.out.println("nuevo" + casillas);
            salida = new Intent(this, MainActivityWin.class);
            salida.putExtra("personaje", icon);
            salida.putExtra("tiempo", tiempoTranscurrido);
            salida.putExtra("casillas", casillas);
            startActivity(salida);
            //transicion difuminado
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            //fin espera
        }, 3000);
    }

    /**
     * Metodo que define el comportamiento cuando se pierde el juego
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
            salida = new Intent(this, MainActivityLooser.class);
            startActivity(salida);
            //transición de difuminado
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }, 3000);
    }
}