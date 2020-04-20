package com.example.sportcenter.Clases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.example.sportcenter.Objetos.Usuario;
import com.example.sportcenter.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener{

    EditText etusuario, etcontrasena;
    TextView crear;
    Button enviar;
    Usuario user;
    DatabaseReference referencia_user;
    private final static String USERNAME = "username";
    String user_name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AndroidNetworking.initialize(getApplicationContext());

        //onResumes();


        etusuario = findViewById(R.id.usuario);
        etcontrasena = findViewById(R.id.contrasena);
        crear = findViewById(R.id.crear);
        enviar = findViewById(R.id.entrar);

        crear.setOnClickListener(this);
        enviar.setOnClickListener(this);
        // obtener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.entrar:
                obtener();
                break;
            case R.id.crear:
                Intent registrar = new Intent(this, Registrarse.class);
                startActivity(registrar);
                break;
        }
    }


    public void obtener() {

        referencia_user = FirebaseDatabase.getInstance().getReference("usuario");
        Query sentencia = referencia_user.orderByChild("email").equalTo("admin@gmail.com").limitToFirst(1);
        sentencia.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        user = d.getValue(Usuario.class);
                        acceder();
                    }
                } else {
                    Toast.makeText(Login.this, "MAL", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void acceder(){
        Intent main = new Intent(this, MainActivity.class);
        SaveUser();
        startActivity(main);
        finish();
    }


    private void SaveUser() {
        super.onPause();
        SharedPreferences seguridad = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor bloc = seguridad.edit();
        bloc.putString("username", user.getUsername());
        bloc.apply();
    }

    protected void onResumes(){
        super.onResume();
        SharedPreferences info = PreferenceManager.getDefaultSharedPreferences(this);
        user_name = info.getString("username", "");

        // Toast.makeText(this, user_name+"", Toast.LENGTH_LONG).show();
    }

}
