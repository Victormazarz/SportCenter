package com.example.sportcenter.Clases;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportcenter.Objetos.Actividad;
import com.example.sportcenter.Objetos.Actividad_Nadar;
import com.example.sportcenter.Objetos.Actividad_otro;
import com.example.sportcenter.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Registrar_Actividad extends AppCompatActivity implements TiempoDialogo.EstablecerTiempo{

    String opcion = "", tipo = "",user_name,id,Stiempo;
    int layout = R.layout.registrar_actividad_correr_andar_bici;
    String[] opcionesCorrer = {"Ciudad", "Sendero", "Todo Terreno", "Mixta", "Playa"};
    String[] opcionesNadar = {"Olimpica", "Semi Olimpica", "Playa"};
    String[] opcionesOtros = {"Campo de Futbol", "Campo de Baloncesto", "Pista de Raqueta", "Gimnasio"};
    String[] opciones;


    Button enviar,bottontiempo;
    EditText titulo,calorias, distancia, ritmo_brazada, info;
    Spinner spinner;
    ArrayAdapter<String> adapter;

    DatabaseReference referencia_actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opcion = getIntent().getStringExtra("opcion");
        onResumes();
        layout = R.layout.registrar_actividad_correr_andar_bici;
        adapter = new ArrayAdapter<>(this, R.layout.spinner, opcionesCorrer);
        opciones = opcionesCorrer;

        referencia_actividad = FirebaseDatabase.getInstance().getReference();
        id = referencia_actividad.push().getKey();

        switch (opcion) {
            case "Correr":
                tipo = "Correr";
                break;
            case "Caminata":
                tipo = "Caminata";
                break;
            case "Ciclismo":
                tipo = "Ciclismo";
                break;
            case "Natacion":
                tipo = "Natacion";
                layout = R.layout.registrar_actividad_nadar;
                adapter = new ArrayAdapter<>(this, R.layout.spinner, opcionesNadar);
                opciones = opcionesCorrer;
                break;
            case "Otra Actividad":
                tipo = "Otra Actividad";
                layout = R.layout.registrar_actividad_otros;
                adapter = new ArrayAdapter<>(this, R.layout.spinner, opcionesOtros);
                opciones = opcionesCorrer;
                break;
        }
        setContentView(layout);
        getSupportActionBar().hide();

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        inicializar();

        enviar = findViewById(R.id.enviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                almacenar();
            }

        });

    }

    public void almacenar() {
        registrar();
        Intent ir = new Intent(this, MainActivity.class);
        startActivity(ir);
        finish();
    }

    public void inicializar() {

        titulo = findViewById(R.id.titulo);
        calorias = findViewById(R.id.calorias);
        info = findViewById(R.id.info);
        bottontiempo = findViewById(R.id.btiempo);
        bottontiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReg();
            }
        });

        if (opcion.equals("Correr") | opcion.equals("Caminata") | opcion.equals("Ciclismo")) {
            distancia = findViewById(R.id.distancia);
            ritmo_brazada = findViewById(R.id.ritmo);
        }
        if (opcion.equals("Natacion")) {
            ritmo_brazada = findViewById(R.id.brazadas);
            distancia = findViewById(R.id.distancia_nadar);
        }
    }

    public void addReg() {
        TiempoDialogo pregunta = new TiempoDialogo(Registrar_Actividad.this);
        pregunta.show(getSupportFragmentManager(), "Establecer tiempo");
    }

    private void registrar() {
        switch (opcion) {
            case "Correr":
            case "Caminata":
            case "Ciclismo":
                CorrerCaminataCiclismo();
                break;
            case "Natacion":
                Natacion();
                break;
            case "Otra Actividad":
                Otro();
                break;
        }
    }

    private void CorrerCaminataCiclismo() {
        Actividad actividad = new Actividad(tipo,titulo.getText().toString(),Stiempo,info.getText().toString(),opcionesCorrer[spinner.getSelectedItemPosition()],Integer.valueOf(calorias.getText().toString()),Double.valueOf(distancia.getText().toString()),Double.valueOf(ritmo_brazada.getText().toString()),getFecha(),user_name);
        referencia_actividad.child("actividades").child(id).setValue(actividad);
    }

    private void Natacion(){
        Actividad_Nadar actividad = new Actividad_Nadar(tipo,titulo.getText().toString(),Stiempo,info.getText().toString(),opcionesNadar[spinner.getSelectedItemPosition()],Integer.valueOf(calorias.getText().toString()),Integer.valueOf(ritmo_brazada.getText().toString()),Double.valueOf(distancia.getText().toString()),getFecha(),user_name);
        referencia_actividad.child("actividades").child(id).setValue(actividad);
    }

    private void Otro() {
       Actividad_otro actividad = new Actividad_otro(tipo,titulo.getText().toString(),Stiempo,info.getText().toString(),opcionesOtros[spinner.getSelectedItemPosition()],Integer.valueOf(calorias.getText().toString()),getFecha(),user_name);
       referencia_actividad.child("actividades").child(id).setValue(actividad);
    }

    private String getFecha(){
        Calendar fecha = new GregorianCalendar();
        return fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR);
    }

    protected void onResumes(){
        super.onResume();
        SharedPreferences info = PreferenceManager.getDefaultSharedPreferences(this);
        user_name = info.getString("username", "");
    }


    @Override
    public void setTiempo(String fecha) {
        bottontiempo.setText("Tiempo: "+fecha+"h");
        bottontiempo.setTextSize(20);
        Stiempo = fecha;
    }
}
