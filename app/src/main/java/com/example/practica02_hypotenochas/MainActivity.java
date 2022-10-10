package com.example.practica02_hypotenochas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
             setContentView(R.layout.activity_main_instrucciones);
                break;
            case R.id.nuevo:
                setContentView(R.layout.activity_main_nuevo);
                break;
            case R.id.config:
               setContentView(R.layout.activity_main_config);
                break;
            case R.id.perso:
                setContentView(R.layout.activity_main_personajes);
                break;
            default:
                break;
        }
        return true;
    }


}