package com.example.practica02_hypotenochas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class DialogoGenero extends DialogFragment {
    RespuestaDialogoGenero respuesta;
    EditText nombre;

    /*contructor, esta clase se llamará cuando hacemos show()
    de la clase DialogFragment
     */

    @Override
    public Dialog onCreateDialog(Bundle savedinstanceState){

        //1. Usamos la clase Builder para construir el dialogo
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        //2. Seteamos las carácteristicas(titulo)
        builder.setTitle("Introduce tu nombre:");
       //Inflamos la vista
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_layout, null);
        //Seteamos la vista en el builder
        builder.setView(v);
        //
        nombre=v.findViewById(R.id.usuario);

        //3.Añadimos los botones (positivo,negativo,neutro)
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            String res="";
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                res=nombre.getText().toString();
                respuesta.onRespuesta(res);
            }
        });

        //4.Devolvemos el Dialog
        return builder.create();
    }

    //Interfaz para la comunicación entre la actividad y el fragmento doialogo
    public interface RespuestaDialogoGenero{
        public void onRespuesta(String s);
    }
    //se invoca cuando el fragmento se añade a la actividad

    @Override
    public void onAttach(Context activity){
        super.onAttach(activity);
        respuesta=(RespuestaDialogoGenero) activity;
    }
}
