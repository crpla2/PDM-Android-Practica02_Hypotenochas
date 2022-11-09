package com.example.practica02_hypotenochas;

import android.graphics.drawable.Drawable;

public class Jugador {
    private int icon;
    private String puntos;
    private String tiempo;
    private String nombre;
    private int nivel;

        // Constructor
        public Jugador(String nombre, String puntos, String tiempo, int nivel,int icon) {
            this.nombre =nombre ;
            this.puntos = puntos;
            this.tiempo = tiempo;
            this.nivel=nivel;
            this.icon=icon;
        }



    // Getter and Setter
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getPuntos() {
            return puntos;
        }
        public void setPuntos(String puntos) {
            this.puntos = puntos;
        }

        public String getTiempo() {
            return tiempo;
        }
        public void setTiempo(String tiempo) {
            this.tiempo = tiempo;
        }

        public int getNivel() {
            return nivel;
        }
        public void setNivel(int nivel) {
            this.nivel = nivel;
        }

        public int getIcon() {
            return nivel;
        }
        public void setIcon(int icon) {
           this.icon = icon;
        }

}
