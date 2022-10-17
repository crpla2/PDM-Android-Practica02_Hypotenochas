package com.example.practica02_hypotenochas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent entrada,salida;
    MenuItem mi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salida = getIntent();

    }
    @Override
    protected void onRestart() {
        super.onRestart();
       /* entrada = getIntent();
        mi=findViewById(R.id.perso);
        mi=findViewById(R.id.perso);
        Bundle b = entrada.getExtras();
        mi.setIcon((Drawable) b.get("personaje"));*/

      //  mi.setIcon(R.drawable.arenita);
        Toast.makeText(getApplicationContext(),"heheheh", Toast.LENGTH_LONG).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflamos el menu
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch ((item.getItemId())){
            case R.id.instrucciones:
                salida = new Intent(this,MainActivityInstrucciones.class);
                startActivity(salida);
                break;
            case R.id.nuevo:
                salida = new Intent(this,MainActivityNuevo.class);
                startActivity(salida);
                break;
            case R.id.config:
                salida = new Intent(this,MainActivityConfig.class);
                startActivity(salida);
                break;
            case R.id.perso:
                salida = new Intent(this,MainActivityPerso.class);
                startActivity(salida);
                break;
            default:
                break;
        }
        return true;
    }


}