package com.example.practica02_hypotenochas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityPerso extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spn;
    private ImageView iv;
    Intent salida;

    /**
     * Método que almacena los datos de la actividad, sirve para colocar el código de inicialización.
     * Almacena la lógica básica de inicio de la aplicación que debería suceder solo una vez
     * durante toda la vida de la actividad.
     *
     * @param savedInstanceState referencia a un objeto Bundle que se pasa al método.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_personajes);

        inicioXML();
        darClic();
    }

    private void inicioXML() {
        spn = findViewById(R.id.spinnerPersonajes);
        iv = findViewById(R.id.imageViewPersonaje);
    }

    private void darClic() {
        spn.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.personajes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        salida = new Intent(this, MainActivity.class);
        int position = parent.getSelectedItemPosition();
        switch (position) {
            case 0:
                iv.setImageResource(R.drawable.bob);
                salida.putExtra("personaje", R.drawable.bob);
                break;
            case 1:
                iv.setImageResource(R.drawable.arenita);
                salida.putExtra("personaje", R.drawable.arenita);
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
                salida.putExtra("personaje", R.drawable.patricio);
                break;
            case 5:
                iv.setImageResource(R.drawable.placton);
                salida.putExtra("personaje", R.drawable.placton);
                break;
            case 6:
                iv.setImageResource(R.drawable.gary);
                salida.putExtra("personaje", R.drawable.gary);
                break;
            default:
                break;
        }

        // Toast.makeText(getApplicationContext(),parent.getSelectedItem().toString(), Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void onClickVolverPerso(View view) {
        startActivity(salida);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);

    }

    /**
     * Metodo para inflar el menu
     *
     * @param menu recibe como parametro un recurso de menu
     * @return (boolean) devuelve true cuando se crea
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflamos el menu
        getMenuInflater().inflate(R.menu.menu_salida, menu);
        return true;
    }

    /**
     * Metodo que define el comportamiento del menu
     *
     * @param item recibe el menuitem seleccionado
     * @return (boolean) devuelve true cuando un item ha sido seleccionado.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ((item.getItemId()) == R.id.salir) {
            //Abre la pantalla de inicio
            salida = new Intent(this, MainActivity.class);
            startActivity(salida);
            overridePendingTransition(R.anim.right_in, R.anim.right_out);
        }
        return true;
    }
}
