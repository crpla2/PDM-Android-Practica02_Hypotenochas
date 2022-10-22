package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class MainActivityNuevo extends AppCompatActivity {
    Intent entrada;
    Intent salida;
    int icon,casillas,numMinas;
    //

    int filasTablero;
    int destapadas;
    //animaciones


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
        Bundle b= entrada.getExtras();
        icon=b.getInt("personaje");
        if(b.getInt("casillas")==0){
            casillas=64;
            numMinas=10;
        }else{
            casillas=b.getInt("casillas");
            numMinas=b.getInt("minas");
        }
        filasTablero= (int) Math.sqrt(casillas);
        Toast.makeText(getApplicationContext(),"Casillas:"+casillas, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"Minas:"+numMinas, Toast.LENGTH_LONG).show();

        tablero = findViewById(R.id.tablero);
        tablero.setOrientation(LinearLayout.HORIZONTAL);
        anadeLayouts();
        juego = new buscaBurgers(casillas, numMinas, filasTablero);
        tableroMinado = juego.generaTablero(casillas, numMinas);

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
                button.setBackgroundColor(colorAleatorio());
                button.setId(numBoton);
                numBoton += filasTablero;
                button.setBackground(getDrawable(R.drawable.boton_frente));

                button.setOnClickListener(this::onClick);
                button.setOnLongClickListener(this::onLongClick);

                ll.addView(button);

                botones.add(button);
            }
            tablero.addView(ll);
        }
    }

    boolean clicado = false;

    private boolean onLongClick(View view) {

        if (!clicado) {
            view.setForeground(getResources().getDrawable(R.drawable.bobmarcador));
            clicado = true;
            if(juego.tieneMina(view.getId()))
                destapadas++;
        } else {
            view.setForeground(getResources().getDrawable(R.drawable.boton_frente));
            clicado = false;
        }

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
        game = juego.descubreCasillas(view.getId());
        //Se combrueba que la casilla seleccionada no contenga una bomba
        for (Map.Entry<Integer, Integer> minasEntry : tableroMinado.entrySet()) {
            if (minasEntry.getKey() == view.getId() && minasEntry.getValue() == 1) {
                Toast.makeText(getApplicationContext(), "Perdiste!!", Toast.LENGTH_LONG).show();
                perdido = true;
            }
        }
        //descubre las minas si hemos perdido
        if (perdido) {
            for (Map.Entry<Integer, Integer> minasEntry2 : tableroMinado.entrySet()) {
                for (int i = 0; i < casillas; i++) {
                    if ((minasEntry2.getKey() == (botones.get(i).getId())) && (minasEntry2.getValue() == 1)) {
                        //  Animation shake = AnimationUtils.loadAnimation( R.anim.);
                        //  view.startAnimation(shake);
                        botones.get(i).setForeground(getDrawable(R.drawable.bomburguesa));
                    }
                }
            }
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
                                    b.setTextColor(getResources().getColor(R.color.verde));
                                }
                                if (valor > 2) {
                                    b.setTextColor(getResources().getColor(R.color.rojo));
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
            Toast.makeText(getApplicationContext(), "Ganaste!!" + destapadas, Toast.LENGTH_LONG).show();
            ganado = true;
        }
    }

    /**
     * Método que genera un color de forma aleatoria.
     *
     * @return int devuelve un entero (Color ints).
     * FUNCIONAMIENTO:
     * Crea un objeto "rnd" de la clase Random y mediante el método nextInt(255) genera números
     * aleatorios hasta el 255.
     * Llama al método estático "argb" de la Clase Color que recibe cuatro argumentos en
     * forma de entero(int) del rango [0-255], donde:
     * - El primer argumento "Alpha" es el nivel de opacidad (fijado al máximo:255).
     * - El segundo argumento "Red" el nivel de rojo (generado aleatoriamente).
     * - El tercer argumento "Green" el nivel de verde (generado aleatoriamente).
     * - El tercer argumento "Blue" el nivel de azul (generado aleatoriamente).
     */
    private int colorAleatorio() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(100),
                rnd.nextInt(150), 180);
    }


}