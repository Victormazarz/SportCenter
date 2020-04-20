package com.example.sportcenter.Clases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportcenter.Adapters.Adapter_Recientes;
import com.example.sportcenter.Objetos.Actividad;
import com.example.sportcenter.Objetos.Usuario;
import com.example.sportcenter.Objetos.item_recientes;
import com.example.sportcenter.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Registro_Datos extends AppCompatActivity implements View.OnClickListener {

    List<item_recientes> recientes = new ArrayList<>();
    List<item_recientes> tres = new ArrayList<>();

    Adapter_Recientes adapter_recientes;
    RecyclerView rv_recientes;
    String user_name,friend_name;

    DatabaseReference referencia_correr, referencia_user;
    Usuario user;

    TextView nombre,klm,masrecintes;
    EditText username;
    Double contklm=0.00;

    LinearLayout actividad, home, perfil;
    Button buscarUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__datos);
        onResumes();
        iniciarlizar();

        adapter_recientes = new Adapter_Recientes(this,tres);
        rv_recientes = findViewById(R.id.rv_recientes);


        rv_recientes.setAdapter(adapter_recientes);
        rv_recientes.setLayoutManager(new LinearLayoutManager(this));
    }

    private void iniciarlizar(){

        nombre = findViewById(R.id.registro_nombre);
        klm = findViewById(R.id.registro_km);
        masrecintes = findViewById(R.id.mostrar_registro);
        username = findViewById(R.id.ETusername);

        actividad = findViewById(R.id.btnactivitdad);
        actividad.setOnClickListener(this);
        home = findViewById(R.id.home);
        home.setOnClickListener(this);
        perfil = findViewById(R.id.perfil);
        perfil.setOnClickListener(this);

        buscarUser = findViewById(R.id.buscaruser);
        buscarUser.setOnClickListener(this);

        masrecintes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambio(user_name);
            }
        });

        DatosRecientes();
        obtenerUsuario();
    }


    private void DatosRecientes() {

        referencia_correr = FirebaseDatabase.getInstance().getReference("actividades");
        Query sentencia = referencia_correr.orderByChild("username").equalTo(user_name);
        sentencia.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        Actividad correr = d.getValue(Actividad.class);
                        contklm += correr.getDistancia();
                        recientes.add(new item_recientes(correr.getTitulo(),getIcon(correr.getTipo()),correr.getDistancia(),correr.getTiempo(),correr.getAmbiente(),correr.getFecha()));
                    }
                }

                if (recientes.size()<4){

                if (recientes.size()==1){
                    tres.add(recientes.get(0));
                }
                if (recientes.size()==2){
                    tres.add(recientes.get(0));
                    tres.add(recientes.get(1));
                }
                if (recientes.size()==3){
                    tres.add(recientes.get(0));
                    tres.add(recientes.get(1));
                    tres.add(recientes.get(2));
                }}
                tres.add(recientes.get(0));
                tres.add(recientes.get(1));
                tres.add(recientes.get(2));

                Collections.reverse(recientes);
                adapter_recientes.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public int getIcon(String tipo){
        switch (tipo){
            case "Correr":
                return R.drawable.icon_run;
            case "Caminata":
                return R.drawable.icon_walk;
            case "Ciclismo":
                return R.drawable.icon_cicling;
            case "Natacion":
                return R.drawable.icon_swimming;
            case "Otra Actividad":
                return R.drawable.icon_gym;
        }
        return R.drawable.icon_run;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnactivitdad:
                Intent ir = new Intent(this, Registrar_Opcion.class);
                startActivity(ir);
                break;

            case R.id.home:
                Intent home = new Intent(this, MainActivity.class);
                startActivity(home);
                break;
            case R.id.perfil:
                Intent perfil = new Intent(this, Perfil.class);
                startActivity(perfil);
                break;
            case R.id.buscaruser:
                if (username.getText().toString().isEmpty()){
                    Toast.makeText(this, "Introduce nombre de Usuario", Toast.LENGTH_SHORT).show();
                }else{
                    friend_name = username.getText().toString();
                    cambio(friend_name);
                }
                break;
        }
    }

    protected void onResumes(){
        super.onResume();
        SharedPreferences info = PreferenceManager.getDefaultSharedPreferences(this);
        user_name = info.getString("username", "");
    }


    private void obtenerUsuario() {
        referencia_user = FirebaseDatabase.getInstance().getReference("usuario");
        Query sentencia = referencia_user.orderByChild("username").equalTo(user_name).limitToFirst(1);
        sentencia.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        user = d.getValue(Usuario.class);
                        nombre.setText(user.getNombre()+" "+user.getApellido());
                        klm.setText(contklm+"klm");
                    }
                } else {
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void cambio(String name){
        Intent ir = new Intent(this,Mostrar_actividades.class);
        ir.putExtra("username",name);
        startActivity(ir);
    }
}
