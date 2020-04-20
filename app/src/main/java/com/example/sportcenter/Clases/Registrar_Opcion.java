package com.example.sportcenter.Clases;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sportcenter.Adapters.Adapter;
import com.example.sportcenter.Objetos.item_card;
import com.example.sportcenter.R;

import java.util.ArrayList;
import java.util.List;

public class Registrar_Opcion extends AppCompatActivity {

    List<item_card> deportes = new ArrayList<>();
    Adapter adapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__opcion);

        rv = findViewById(R.id.rv);

        deportes.add(new item_card(R.drawable.img_run, "Correr"));
        deportes.add(new item_card(R.drawable.img_senderismo, "Caminata"));
        deportes.add(new item_card(R.drawable.img_ciclismo, "Ciclismo"));
        deportes.add(new item_card(R.drawable.img_natacion, "Natacion"));
        deportes.add(new item_card(R.drawable.img_gimnasio, "Otra Actividad"));

        adapter = new Adapter(this, deportes);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opcion = "";

                switch (deportes.get(rv.getChildAdapterPosition(v)).getTitulo()) {

                    case "Correr":
                        opcion = "Correr";
                        break;
                    case "Caminata":
                        opcion = "Caminata";
                        break;
                    case "Ciclismo":
                        opcion = "Ciclismo";
                        break;
                    case "Natacion":
                        opcion = "Natacion";
                        break;
                    case "Otra Actividad":
                        opcion = "Otra Actividad";
                        break;

                }
                cambio(opcion);
            }
        });
    }

    public void cambio(String opcion) {

        Intent ir = new Intent(this, Registrar_Actividad.class);
        ir.putExtra("opcion", opcion);
        startActivity(ir);

    }


}
