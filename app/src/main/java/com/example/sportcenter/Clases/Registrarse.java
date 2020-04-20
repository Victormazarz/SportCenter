package com.example.sportcenter.Clases;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.androidnetworking.AndroidNetworking;
import com.example.sportcenter.Objetos.Usuario;
import com.example.sportcenter.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Registrarse extends AppCompatActivity {

    String[] sexo = {"Hombre", "Mujer", "Sin especificar"};
    String[] nivel = {"Principiante", "Moderado", "Avanzado", "Profesional", "Deportista de Elite"};
    Spinner genero, snivel;
    ArrayAdapter<String> adapter;
    Button terminar,fecha;
    EditText nombre, apellidos, email, contrasena, contrasena2, altura, peso;
    CheckBox condiciones;
    int dia,mes,anyo;
    String fecha_nacimiento,id;

    DatabaseReference referencia_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        AndroidNetworking.initialize(getApplicationContext());
        inicializar();
    }


    private void inicializar() {
        referencia_user = FirebaseDatabase.getInstance().getReference();
        id = referencia_user.push().getKey();

        adapter = new ArrayAdapter<>(this, R.layout.spinner, sexo);
        genero = findViewById(R.id.genero);
        genero.setAdapter(adapter);

        adapter = new ArrayAdapter<>(this, R.layout.spinner, nivel);
        snivel = findViewById(R.id.nivel);
        snivel.setAdapter(adapter);

        nombre = findViewById(R.id.usuario);
        apellidos = findViewById(R.id.apellidos);
        email = findViewById(R.id.email);
        contrasena = findViewById(R.id.contrasenareg);
        contrasena2 = findViewById(R.id.contrasena2reg);
        altura = findViewById(R.id.altura);
        peso = findViewById(R.id.peso);
        condiciones = findViewById(R.id.check);

        fecha = findViewById(R.id.fecha);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFecha();
            }
        });

        terminar = findViewById(R.id.terminar);
        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    volver();
            }
        });
    }

    private void insertar() {
        Usuario user = new Usuario(nombre.getText().toString(),apellidos.getText().toString(),email.getText().toString(),contrasena.getText().toString(),fecha_nacimiento,Double.valueOf(altura.getText().toString()),Double.valueOf(peso.getText().toString()),1,"username");
        referencia_user.child("usuario").child(id).setValue(user);
    }

    private void volver(){
        insertar();
        Intent ir = new Intent(this, Login.class);
        startActivity(ir);
        finish();
    }


    private void setFecha(){ // Sacar fecha de hoyv
        final Calendar c = Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        anyo=c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //fecha_nacimiento = (year+"/"+(month+1)+"/"+dayOfMonth);
                fecha_nacimiento = (dayOfMonth+"/"+(month+1)+"/"+year);
                //Toast.makeText(Registrarse.this, fecha_nacimiento, Toast.LENGTH_SHORT).show();
            }
        },dia,mes,anyo);
        datePickerDialog.show();
    }
}
