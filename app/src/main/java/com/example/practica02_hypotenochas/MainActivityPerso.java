package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityPerso  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spn;
    private ImageView iv;
    Intent entrada,salida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_personajes);
        entrada= getIntent();
        inicioXML();
        darClic();
    }

    private void inicioXML() {
        spn = findViewById(R.id.spinnerPersonajes);
        iv=findViewById(R.id.imageViewPersonaje);
    }
    private void darClic() {
        spn.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.personajes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        salida = new Intent(this,MainActivity.class);
        int position = parent.getSelectedItemPosition();
        switch (position)
        {
            case 0:
                iv.setImageResource(R.drawable.bob);
                salida.putExtra("personaje",R.drawable.bob);
                break;
            case 1:
                iv.setImageResource(R.drawable.arenita);
                salida.putExtra("personaje",R.drawable.arenita);
                break;
            case 2:
                iv.setImageResource(R.drawable.calamardo);
                salida.putExtra("personaje", R.drawable.calamardo);
                break;
            case 3:
                iv.setImageResource(R.drawable.cangrejo);
                salida.putExtra("personaje", R.drawable.cangrejo);
                break;
            case 4:
                iv.setImageResource(R.drawable.patricio);
                salida.putExtra("personaje",R.drawable.patricio);
                break;
            case 5:
                iv.setImageResource(R.drawable.placton);
                salida.putExtra("personaje", R.drawable.placton);
                break;
            case 6:
                iv.setImageResource(R.drawable.gary);
                salida.putExtra("personaje",R.drawable.gary);
                break;
            default:
                break;
        }

       // Toast.makeText(getApplicationContext(),parent.getSelectedItem().toString(), Toast.LENGTH_LONG).show();



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


public void onClickVolverPerso(View view){
        startActivity(salida);
}







}
