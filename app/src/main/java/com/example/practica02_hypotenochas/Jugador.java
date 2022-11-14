package com.example.practica02_hypotenochas;

/**
 * Clase que define el objeto jugador
 * Se utiliza para registrar los datos del jugador el el salón de la fama.
 */
public class Jugador {
    private int icon;
    private final int puntos;
    private final String tiempo;
    private final String nombre;
    private final int nivel;

    /**
     * Constructor
     *
     * @param nombre Nombre del jugador (String)
     * @param puntos Puntos realizados(int)
     * @param tiempo Tiempo utilizado (String)
     * @param nivel  Nivel de la partida (int)
     * @param icon   Icono del personaje con el que ha jugado (icon)
     */
    public Jugador(String nombre, int puntos, String tiempo, int nivel, int icon) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.tiempo = tiempo;
        this.nivel = nivel;
        this.icon = icon;
    }

    // Getters y Setters:

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getTiempo() {
        return tiempo;
    }

    public int getNivel() {
        return nivel;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}
