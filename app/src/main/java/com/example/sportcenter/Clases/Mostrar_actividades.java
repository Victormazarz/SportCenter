package com.example.sportcenter.Clases;

import android.app.Dialog;
import android.os.Bundle;

import com.example.sportcenter.Adapters.Adapter_Actividades_All;
import com.example.sportcenter.Objetos.Actividades_Get;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

public class Mostrar_actividades extends AppCompatActivity {

    List<Actividades_Get> actividades = new ArrayList<>();

    Adapter_Actividades_All adapter_recientes;
    RecyclerView rv_recientes;
    String user_name;

    Dialog dialogo;
    DatabaseReference referencia_correr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_mostrar_actividades);
       //onResumes();
        user_name = getIntent().getStringExtra("username");
        iniciarlizar();

        adapter_recientes = new Adapter_Actividades_All(this,actividades);
        rv_recientes = findViewById(R.id.lista);

        dialogo = new Dialog(this);
        rv_recientes.setAdapter(adapter_recientes);
        rv_recientes.setLayoutManager(new LinearLayoutManager(this));
        
        adapter_recientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (actividades.get(rv_recientes.getChildAdapterPosition(v)).getTipo()){
                    case "Correr":
                    case "Caminata":
                    case "Ciclismo":
                        ShowPopupCorrer(v,rv_recientes);
                        break;
                    case "Natacion":
                        ShowPopupNadar(v,rv_recientes);
                        break;
                    case "Otra Actividad":
                        ShowPopupOtro(v,rv_recientes);
                        break;

                }

                //
            }
        });
        
    }

    private void iniciarlizar(){

        DatosRecientes();
    }

    public void ShowPopupCorrer(View v,final RecyclerView r){
        TextView titulo,calorias,tiempo,distancia,ritmo,ambiente,fecha,descripcion,x;
        ImageView img;
        dialogo.setContentView(R.layout.popup_correr);

        titulo = dialogo.findViewById(R.id.poptitulo);
        calorias = dialogo.findViewById(R.id.popcalorias);
        tiempo = dialogo.findViewById(R.id.poptiempo);
        distancia = dialogo.findViewById(R.id.popdistancia);
        ritmo = dialogo.findViewById(R.id.popbrazada);
        ambiente = dialogo.findViewById(R.id.popambiente);
        fecha = dialogo.findViewById(R.id.popfecha);
        descripcion = dialogo.findViewById(R.id.popdescripcion);
        x = dialogo.findViewById(R.id.x);
        img = dialogo.findViewById(R.id.popimagen);

        titulo.setText(actividades.get(r.getChildAdapterPosition(v)).getTitulo());
        calorias.setText("Calorias: "+actividades.get(r.getChildAdapterPosition(v)).getCalorias()+"");
        tiempo.setText("Tiempo de ejercicio: "+actividades.get(r.getChildAdapterPosition(v)).getTiempo()+"h");
        distancia.setText("Distancia: "+actividades.get(r.getChildAdapterPosition(v)).getDistancia()+"klm");
        ritmo.setText("Ritmo medio: "+actividades.get(r.getChildAdapterPosition(v)).getRitmo()+"klm/h");
        ambiente.setText("Ambiente: "+actividades.get(r.getChildAdapterPosition(v)).getAmbiente());
        fecha.setText("Fecha de la actividad:"+actividades.get(r.getChildAdapterPosition(v)).getFecha());
        descripcion.setText(actividades.get(r.getChildAdapterPosition(v)).getDescripcion());
        img.setImageResource(getIcon(actividades.get(r.getChildAdapterPosition(v)).getTipo()));

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });
        dialogo.show();
    }

    public void ShowPopupNadar(View v,final RecyclerView r){
        TextView titulo,calorias,tiempo,distancia,brazada,ambiente,fecha,descripcion,x;
        ImageView img;
        dialogo.setContentView(R.layout.popup_nadar2);

        titulo = dialogo.findViewById(R.id.poptitulo);
        calorias = dialogo.findViewById(R.id.popcalorias);
        tiempo = dialogo.findViewById(R.id.poptiempo);
        distancia = dialogo.findViewById(R.id.popdistancia);
        brazada = dialogo.findViewById(R.id.popbrazada);
        ambiente = dialogo.findViewById(R.id.popambiente);
        fecha = dialogo.findViewById(R.id.popfecha);
        descripcion = dialogo.findViewById(R.id.popdescripcion);
        x = dialogo.findViewById(R.id.x);
        img = dialogo.findViewById(R.id.popimagen);

        titulo.setText(actividades.get(r.getChildAdapterPosition(v)).getTitulo());
        calorias.setText("Calorias: "+actividades.get(r.getChildAdapterPosition(v)).getCalorias()+"");
        tiempo.setText("Tiempo de ejercicio: "+actividades.get(r.getChildAdapterPosition(v)).getTiempo()+"h");
        distancia.setText("Distancia: "+actividades.get(r.getChildAdapterPosition(v)).getDistancia()+"klm");
        brazada.setText("Brazadas: "+actividades.get(r.getChildAdapterPosition(v)).getBrazadas());
        ambiente.setText("Ambiente: "+actividades.get(r.getChildAdapterPosition(v)).getAmbiente());
        fecha.setText("Fecha de la actividad:"+actividades.get(r.getChildAdapterPosition(v)).getFecha());
        descripcion.setText(actividades.get(r.getChildAdapterPosition(v)).getDescripcion());
        img.setImageResource(getIcon(actividades.get(r.getChildAdapterPosition(v)).getTipo()));

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });
        dialogo.show();
    }

    public void ShowPopupOtro(View v,final RecyclerView r){
        TextView titulo,calorias,tiempo,ambiente,fecha,descripcion,x;
        ImageView img;
        dialogo.setContentView(R.layout.popup_otro2);

        titulo = dialogo.findViewById(R.id.poptitulo);
        calorias = dialogo.findViewById(R.id.popcalorias);
        tiempo = dialogo.findViewById(R.id.poptiempo);
        ambiente = dialogo.findViewById(R.id.popambiente);
        fecha = dialogo.findViewById(R.id.popfecha);
        descripcion = dialogo.findViewById(R.id.popdescripcion);
        x = dialogo.findViewById(R.id.x);
        img = dialogo.findViewById(R.id.popimagen);

        titulo.setText(actividades.get(r.getChildAdapterPosition(v)).getTitulo());
        calorias.setText("Calorias: "+actividades.get(r.getChildAdapterPosition(v)).getCalorias()+"");
        tiempo.setText("Tiempo de ejercicio: "+actividades.get(r.getChildAdapterPosition(v)).getTiempo()+"h");
        ambiente.setText("Ambiente: "+actividades.get(r.getChildAdapterPosition(v)).getAmbiente());
        fecha.setText("Fecha de la actividad:"+actividades.get(r.getChildAdapterPosition(v)).getFecha());
        descripcion.setText(actividades.get(r.getChildAdapterPosition(v)).getDescripcion());
        img.setImageResource(getIcon(actividades.get(r.getChildAdapterPosition(v)).getTipo()));

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });
        dialogo.show();
    }


    private void DatosRecientes() {

        referencia_correr = FirebaseDatabase.getInstance().getReference("actividades");
        Query sentencia = referencia_correr.orderByChild("username").equalTo(user_name);
        sentencia.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        Actividades_Get correr = d.getValue(Actividades_Get.class);
                        actividades.add(correr);
                    }
                }
                Collections.reverse(actividades);
                adapter_recientes.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }



/*    protected void onResumes(){
        super.onResume();
        SharedPreferences info = PreferenceManager.getDefaultSharedPreferences(this);
        user_name = info.getString("username", "");
        Toast.makeText(this, user_name+"", Toast.LENGTH_LONG).show();
    }*/


    public int getIcon(String tipo){
        switch (tipo){
            case "Correr":
                return R.drawable.img_run;
            case "Caminata":
                return R.drawable.img_senderismo;
            case "Ciclismo":
                return R.drawable.img_ciclismo;
            case "Natacion":
                return R.drawable.img_natacion;
            case "Otra Actividad":
                return R.drawable.img_gimnasio;
        }
        return R.drawable.img_gimnasio;
    }
}
