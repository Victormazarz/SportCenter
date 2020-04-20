package com.example.sportcenter.Clases;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sportcenter.Objetos.item_multiActividad;
import com.example.sportcenter.Objetos.item_playlist;
import com.example.sportcenter.Objetos.item_playlist_s;
import com.example.sportcenter.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MultiActividad extends AppCompatActivity {


    DatabaseReference referencia_user;
    List<item_multiActividad> lista = new ArrayList<>();
    TextView titulo;
    ImageView img;
    Chronometer crono;
    Button boton;
    int cont = 0, minuto = 3000;//100000
    boolean descanso = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiactividad);
        getActividades();
        incializar();
    }

    public void Empezar(View v) {
        cont = 0;
        crono.start();
        titulo.setText(lista.get(cont).getNombre());
        img.setImageResource(getImagen(lista.get(cont).getNombre()));
        boton.setVisibility(View.INVISIBLE);
    }

    private void getActividades() {

        referencia_user = FirebaseDatabase.getInstance().getReference();
        referencia_user.child("multiactividad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    item_multiActividad p = d.getValue(item_multiActividad.class);
                    lista.add(new item_multiActividad(p.getNombre(), p.getCantidad(), p.getTiempo()));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public void Cambio() {

        cont++;
        crono.setBase(SystemClock.elapsedRealtime());
        titulo.setText(lista.get(cont).getNombre());
        img.setImageResource(getImagen(lista.get(cont).getNombre()));

    }

    public void Final() {

        crono.setBase(SystemClock.elapsedRealtime());
        crono.stop();
        titulo.setText("ACTIVIDADES TERMIANADAS");
        img.setImageResource(R.drawable.ic_trophy);
        boton.setVisibility(View.VISIBLE);
    }

    public void incializar() {
        titulo = findViewById(R.id.multiactvtitulo);
        img = findViewById(R.id.multi_imagen);
        crono = findViewById(R.id.multi_cronometro);
        boton = findViewById(R.id.multi_emepzar);

        crono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                if (elapsedMillis > minuto) {//3 Segundos
                    if (cont >= (lista.size() - 1)) {
                        Final();
                    } else {
                        titulo.setText("10 Segundos de descanso");
                        Cambio();
                    }
                }
            }
        });

    }


    public void returnHomeM(View v) {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        finish();
    }

    private int getImagen(String img) {

        switch (img) {
            case "SENTADILLAS":
                return R.drawable.ic_multiactividad_sentadilla;
            case "FLEXIONES":
                return R.drawable.ic_multiactividad_flexiones;
            case "FLEXIONES CON SALTO":
                return R.drawable.ic_multiactividad_flexion_salto;
            case "CORRER":
                return R.drawable.ic_multiactividad_correr;
            case "PLANCHA":
                return R.drawable.ic_multiactividad_placha;
        }
        return R.drawable.ic_trophy;
    }


}
