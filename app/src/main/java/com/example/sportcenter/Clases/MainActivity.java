package com.example.sportcenter.Clases;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.sportcenter.Adapters.Adapter_Playlist;
import com.example.sportcenter.Objetos.Usuario;
import com.example.sportcenter.Objetos.item_playlist;
import com.example.sportcenter.Objetos.item_playlist_s;
import com.example.sportcenter.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout actividad, registro, perfil;
    List<item_playlist> playlist = new ArrayList<>();

    Adapter_Playlist adapter_playlist;
    RecyclerView rv_playlist;
    DatabaseReference referencia_user;
    private final static String USERNAME = "username";
    String user_name,user_edad;
    Usuario user;

    TextView nombre,edad;
    FloatingActionButton crono,multi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
        onResumes();
        obtenerUsuario();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnactivitdad:
                Intent ir = new Intent(this, Registrar_Opcion.class);
                startActivity(ir);
                break;

            case R.id.registro:
                Intent registro = new Intent(this, Registro_Datos.class);
                startActivity(registro);
                break;
            case R.id.perfil:
                Intent perfil = new Intent(this, Perfil.class);
                startActivity(perfil);
                break;
        }
    }

    private void inicializar(){
        DatosPlaylist();

        actividad = findViewById(R.id.btnactivitdad);
        actividad.setOnClickListener(this);
        registro = findViewById(R.id.registro);
        registro.setOnClickListener(this);
        perfil = findViewById(R.id.perfil);
        perfil.setOnClickListener(this);
        rv_playlist = findViewById(R.id.rv_playlist);

        crono = findViewById(R.id.crono);
        crono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CambioCrono();
            }
        });

        multi = findViewById(R.id.multi);
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CambioMultiActividad();
            }
        });

        nombre = findViewById(R.id.tvNombre);
        edad = findViewById(R.id.tvEdad);

        adapter_playlist = new Adapter_Playlist(this, playlist);
        rv_playlist.setAdapter(adapter_playlist);
        rv_playlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapter_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, playlist.get(rv_playlist.getChildAdapterPosition(v)).getTitulo(), Toast.LENGTH_SHORT).show();
                cambio(playlist.get(rv_playlist.getChildAdapterPosition(v)).getUrl());
            }
        });

    }

    private void DatosPlaylist(){
        referencia_user = FirebaseDatabase.getInstance().getReference();
        referencia_user.child("playlists").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d:dataSnapshot.getChildren()) {
                   item_playlist_s p = d.getValue(item_playlist_s.class);
                   playlist.add(new item_playlist(getImagen(p.getImagen()),p.getTitulo(),getReproductor(p.getReproductor()),p.getUrl()));
                }
               adapter_playlist.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void obtenerUsuario() {
        referencia_user = FirebaseDatabase.getInstance().getReference("usuario");
        Query sentencia = referencia_user.orderByChild("username").equalTo(user_name).limitToFirst(1);
        sentencia.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        user = d.getValue(Usuario.class);
                        nombre.setText(setUpperCase(user.getNombre()+" "+user.getApellido()));
                        edad.setText("Edad: "+getEdad(user.getFecha_nacimiento()));
                    }
                } else {
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private int getImagen(String img) {

        switch (img) {
            case "playlist_img_5":
                return R.drawable.playlist_img_4;
            case "playlist_img_energia":
                return R.drawable.playlist_img_energia;
            case "playlist_img_3":
                return R.drawable.playlist_img_3;
            case "playlist_img_motivacion":
                return R.drawable.playlist_img_motivacion;
            case "playlist_img_2":
                return R.drawable.playlist_img_2;
            case "playlist_img_beast":
                return R.drawable.playlist_img_beast;
        }
        return R.drawable.playlist_img_beast;
    }

    private int getReproductor(String reproductor) {

        switch (reproductor) {
            case "youtube":
                return R.drawable.ic_youtube;
            case "spotify":
                return R.drawable.ic_spotify;
        }
        return R.drawable.ic_youtube;
    }

    protected void onResumes(){
        super.onResume();
        SharedPreferences info = PreferenceManager.getDefaultSharedPreferences(this);
        user_name = info.getString("username", "");
       // Toast.makeText(this, user_name+"", Toast.LENGTH_LONG).show();
    }

    private void cambio(String url) {
        Intent ir = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(ir);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getEdad(String fecha){

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse(fecha, fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);
        System.out.printf("Tu edad es: %s años, %s meses y %s días",
                periodo.getYears(), periodo.getMonths(), periodo.getDays());

        return periodo.getYears()+"";
    }

    private String setUpperCase(String cadena){
        char[] caracteres = cadena.toCharArray();
        caracteres[0] = Character.toUpperCase(caracteres[0]);

        for (int i = 0; i < cadena.length()- 2; i++)
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',')
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);

        return new String(caracteres);
    }

    public void CambioCrono(){
        Intent ir = new Intent(this,Cronometro.class);
        startActivity(ir);
    }

    public void CambioMultiActividad(){
        Intent ir = new Intent(this,MultiActividad.class);
        startActivity(ir);
    }

}
