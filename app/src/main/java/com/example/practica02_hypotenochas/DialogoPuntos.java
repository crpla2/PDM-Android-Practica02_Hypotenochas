package com.example.practica02_hypotenochas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

/**
 * Clase que define el diálogo para introducir el nombre al ganar el juego.
 */
public class DialogoPuntos extends DialogFragment {
    RespuestaDialogoPuntos respuesta;
    EditText nombre;

    /**
     * Constructor, se llamará cuando hagamos show(), de la clase DialogFragment.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedinstanceState) {

        //1. Usamos la clase Builder para construir el dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //2. Seteamos las carácteristicas(titulo)
        builder.setTitle("Introduce tu nombre:");
        //Inflamos la vista
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_layout, null);
        //Seteamos la vista en el builder
        builder.setView(v);
        //Definimos el campo de introducción de texto
        nombre = v.findViewById(R.id.usuario);
        //3.Añadimos el botón (Guardar)
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            String res = "";

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                res = nombre.getText().toString();
                respuesta.onRespuesta(res);
            }
        });
        //4.Devolvemos el Dialog
        return builder.create();
    }

    /**
     * Interfaz para la comunicación entre la actividad y el fragmento dialogo
     */
    public interface RespuestaDialogoPuntos {
        void onRespuesta(String s);
    }

    /**
     * se invoca cuando el fragmento se añade a la actividad
     *
     * @param activity la actividad
     */
    @Override
    public void onAttach(@NonNull Context activity) {
        super.onAttach(activity);
        respuesta = (RespuestaDialogoPuntos) activity;
    }
}
