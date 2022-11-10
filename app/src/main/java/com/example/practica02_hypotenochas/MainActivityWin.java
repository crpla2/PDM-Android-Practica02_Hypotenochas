package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivityWin extends AppCompatActivity implements DialogoPuntos.RespuestaDialogoGenero{
    private Intent salida;
    private Intent entrada;

    private TextView numPunt,numtiempo;

    private int puntuacion;

    private int icon;
    private int casillas;

    EditText ed;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_winner);

        numPunt=findViewById(R.id.tvPuntosNumeros);

        numtiempo=findViewById(R.id.tvTiempoNumeros);


        entrada = getIntent();
        Bundle b = entrada.getExtras();
        icon = b.getInt("personaje");
        String tiempo = b.getString("tiempo");
        casillas=b.getInt("casillas");

        String[] split = tiempo.split(":");
        double tiempoEnSegundos=Integer.parseInt(split[0])*60+Integer.parseInt(split[1]);

        puntuacion=(int)(10000/(tiempoEnSegundos/10)*(casillas/10));

        numPunt.setText(String.valueOf(puntuacion));
        numtiempo.setText(tiempo);


    }

    @Override
    public void onRespuesta(String s) {
        salida = new Intent(this,MainActivityPoints.class);
        salida.putExtra("puntos",numPunt.getText().toString());
        salida.putExtra("tiempo",numtiempo.getText().toString());
        salida.putExtra("nombre",s);
        salida.putExtra("personaje",icon);
        salida.putExtra("casillas",casillas);
        startActivity(salida);
    }
    public void clic(View view){
        DialogFragment dialogFragment= new DialogoPuntos();
        dialogFragment.show(getSupportFragmentManager(),"Mi dialogo");
    }
    public void clicSalir(View view){
        salida = new Intent(this,MainActivity.class);
        startActivity(salida);
    }

}