package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivityWin extends AppCompatActivity implements DialogoPuntos.RespuestaDialogoPuntos {
    private Intent salida;
    private Intent entrada;

    private TextView numPunt, numtiempo;

    private int puntuacion;

    private int icon;
    private int casillas;

    EditText ed;

    /**
     * Método que almacena los datos de la actividad, sirve para colocar el código de inicialización.
     * Almacena la lógica básica de inicio de la aplicación que debería suceder solo una vez
     * durante toda la vida de la actividad.
     *
     * @param savedInstanceState referencia a un objeto Bundle que se pasa al método.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_winner);

        numPunt = findViewById(R.id.tvPuntosNumeros);

        numtiempo = findViewById(R.id.tvTiempoNumeros);


        entrada = getIntent();
        Bundle b = entrada.getExtras();
        icon = b.getInt("personaje");
        String tiempo = b.getString("tiempo");
        casillas = b.getInt("casillas");

        String[] split = tiempo.split(":");
        double tiempoEnSegundos = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);

        puntuacion = (int) (10000 / (tiempoEnSegundos / 10) * (casillas / 10));

        numPunt.setText(String.valueOf(puntuacion));
        numtiempo.setText(tiempo);


    }

    @Override
    public void onRespuesta(String s) {
        salida = new Intent(this, MainActivityPoints.class);
        salida.putExtra("puntos", puntuacion);
        salida.putExtra("tiempo", numtiempo.getText().toString());
        salida.putExtra("nombre", s);
        salida.putExtra("personaje", icon);
        salida.putExtra("casillas", casillas);
        startActivity(salida);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    public void clic(View view) {
        DialogFragment dialogFragment = new DialogoPuntos();
        dialogFragment.show(getSupportFragmentManager(), "Mi dialogo");
    }

    public void clicSalir(View view) {
        salida = new Intent(this, MainActivity.class);
        startActivity(salida);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

}