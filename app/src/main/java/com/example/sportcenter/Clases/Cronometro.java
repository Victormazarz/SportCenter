package com.example.sportcenter.Clases;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import com.example.sportcenter.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Cronometro extends AppCompatActivity {

    private Chronometer cronometro;
    private long parado;
    private boolean ejecutado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        cronometro = findViewById(R.id.cronometro);
    }


    public void startCronometro(View v){
        if (!ejecutado){
            cronometro.setBase(SystemClock.elapsedRealtime() - parado);
            cronometro.start();
            ejecutado=true;
        }

    }

    public void pauseCronometro(View v){
        if (ejecutado){
            parado = SystemClock.elapsedRealtime() - cronometro.getBase();
            cronometro.stop();
            ejecutado=false;
        }

    }

    public void resertCronometro(View v){
        cronometro.setBase(SystemClock.elapsedRealtime());
        parado = 0;
    }

    public void returnHome(View v){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        finish();
    }
}
