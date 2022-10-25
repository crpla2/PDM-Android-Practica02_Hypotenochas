package com.example.practica02_hypotenochas;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class buscaBurgers {
    private TreeMap<Integer, Integer> tableroMinado;
    static ArrayList<Integer> destapadas;
    private final TreeMap<Integer, Integer> destapadasKey;
    int casillas;
    int numMinas;
    int filasTablero;


    public buscaBurgers(int casillas, int numMinas, int filasTablero) {
        super();
        this.casillas = casillas;
        this.numMinas = numMinas;
        this.filasTablero = filasTablero;
        tableroMinado = new TreeMap<>();
        destapadas = new ArrayList<>();
        destapadasKey = new TreeMap<>();
    }
    /**
     *
     * @param posicion
     * @return
     */
    public TreeMap<Integer, Integer> descubreCasillas(int posicion) {
        //Si es una casilla que se puede destapar
        if (!tieneMina(posicion)) {
            if (!destapadas.contains(posicion)) {
                //Se destapa
                destapadas.add(posicion);
                //Si no hay minas cerca tengo que intentar destapar las vecinas
                if (minasAlrededor(posicion) == 0) {
                    for (int casilla : casillasAdyacentes(posicion, filasTablero)) {
                        //Si la celda vecina no es una mina, la destapo
                        if (!tieneMina(casilla)) {
                            //Función recursiva
                            descubreCasillas(casilla);
                        }
                    }
                }
            }
            destapadasKey.put(posicion, minasAlrededor(posicion));
        }
        return destapadasKey;
    }
    /**
     *
     * @param posicion
     * @return
     */
    public int minasAlrededor(int posicion) {
        int resultado = 0;
        for (int cas : casillasAdyacentes(posicion, filasTablero)) {
            if (tieneMina(cas)) {
                resultado++;
            }
        }
        return resultado;
    }
    /**
     *
     * @param posicion
     * @return
     */
    public boolean tieneMina(int posicion) {
        boolean resultado = false;
        if (tableroMinado.get(posicion) == 1)
            return true;
        return resultado;
    }
    boolean destapada(int pos){
        return destapadas.contains(pos);
    }
    /**
     * Método que calcula la posicion de las casillas adyacentes a una posición
     * dada
     *
     * @param posicion (int) la posición de la que buscamos las casillas que le
     *                 rodean.
     * @param filas    (int) número de filas que tiene la cuadricula (debe de ser
     *                 cuadrada; filas=columnas)
     * @return (int[]) devuelve un array de enteros que son la posición de las
     * casillas que rodean a la posición pasada por parametro.
     */
    public static int[] casillasAdyacentes(int posicion, int filas) {
        // si la posicion se encuentra en la columna de la izquierda(excepto el primer y
        // el ultimo registro)=>filaIzquierda=true;
        boolean filaIzquierda = false;
        for (int i = 1; i <= filas - 2; i++)
            if (posicion == i * filas + 1) {
                filaIzquierda = true;
                break;
            }
        // primera fila exceptuando la primra y la ultima posición
        if (posicion < filas && posicion > 1) {
            return new int[]{posicion - 1, posicion + 1, posicion + (filas - 1), posicion + filas,
                    posicion + (filas + 1)};
            // primera posición de la cuadricula
        } else if (posicion == 1) {
            return new int[]{posicion + 1, posicion + filas, posicion + (filas + 1)};
            // última posición de la primera fila
        } else if (posicion == filas) {
            return new int[]{posicion - 1, posicion + (filas - 1), posicion + filas};
            // última columna exceptuando la primera y la última posición
        } else if (posicion % filas == 0 && posicion != filas * filas) {
            return new int[]{posicion - (filas + 1), posicion - filas, posicion - 1, posicion + (filas - 1),
                    posicion + filas};
            // última posición de la cuadricula
        } else if (posicion == (filas * filas)) {
            return new int[]{posicion - (filas + 1), posicion - filas, posicion - 1};
            // última fila exceptuando la primera y la última posición.
        } else if (posicion > ((filas * filas) - (filas - 1)) && posicion < (filas * filas)) {
            return new int[]{posicion - (filas + 1), posicion - filas, posicion - (filas - 1), posicion - 1,
                    posicion + 1};
            // última posición de la primera columna
        } else if (posicion == ((filas * filas) - (filas - 1))) {
            return new int[]{posicion - filas, posicion - (filas - 1), posicion + 1};
            // primera columna
        } else if (filaIzquierda) {
            return new int[]{posicion - filas, posicion - (filas - 1), posicion + 1, posicion + filas,
                    posicion + (filas + 1)};
        }
        // cualquier posición exceptuando las del perimetro.
        return new int[]{posicion - (filas + 1), posicion - filas, posicion - (filas - 1), posicion - 1,
                posicion + 1, posicion + (filas - 1), posicion + filas, posicion + (filas + 1)};
    }
    /**
     * Método que genera un mapa cuyas claves serán las posiciónes en el tablero y
     * los valores, serán 0 si no tienen una mina asociada o 1 si sí que la tienen.
     *
     * @param casillas (int) número total de casilla que tiene el tablero
     * @param numMinas (int) número total de minas que habra escondidas en el
     *                 tablero
     * @return (TreeMap) mapa con las posiciones y las minas asociadas.
     */
    public TreeMap<Integer,Integer> generaTablero(int casillas, int numMinas) {
        int minado = 0;
        int[] tablero = new int[casillas];
        TreeSet<Integer> minas = new TreeSet<>();
        tableroMinado = new TreeMap<>();
        // Se llena el array tablero con numeros del 1 a n (numero total de casillas).
        for (int i = 0; i < tablero.length; i++) {
            tablero[i] = i + 1;
        }
        // Se generan n(numMinas) minas en posiciones no repetidas, se añaden al Treeset.
        while (minas.size() < numMinas)
            minas.add((int) (Math.random() * casillas + 1));
        // Se rellena el TreeMap
        for (int j : tablero) {
            for (int m : minas) {
                if (m == j) {
                    minado = 1;
                    break;
                }
            }
            tableroMinado.put(j, minado);
            minado = 0;
        }
        return tableroMinado;
    }
}