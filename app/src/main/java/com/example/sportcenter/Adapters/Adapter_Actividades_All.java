package com.example.sportcenter.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportcenter.Objetos.Actividades_Get;
import com.example.sportcenter.Objetos.item_recientes;
import com.example.sportcenter.R;

import java.util.List;

public class Adapter_Actividades_All extends RecyclerView.Adapter<Adapter_Actividades_All.Holder> implements View.OnClickListener {

    Context context;
    List<Actividades_Get> datos;
    private View.OnClickListener listener;

    public Adapter_Actividades_All(Context context, List<Actividades_Get> datos) {
        this.context = context;
        this.datos = datos;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.card_listado,parent,false);
        v.setOnClickListener(this);
        return new Holder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.img.setImageResource(getIcon(datos.get(position).getTipo()));
        holder.titulo.setText(datos.get(position).getTitulo());
        holder.tiempo.setText(datos.get(position).getTiempo()+"h");
        holder.ambiente.setText("Ambiente: "+datos.get(position).getAmbiente());
        holder.fecha.setText(datos.get(position).getFecha());

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }


    public class Holder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView titulo,tiempo,ambiente,fecha;

        public Holder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.recientes_imagen);
            titulo = itemView.findViewById(R.id.titulo);
            tiempo = itemView.findViewById(R.id.recientes_tiempo);
            ambiente = itemView.findViewById(R.id.recientes_ambiente);
            fecha = itemView.findViewById(R.id.recientes_fecha);

        }
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

}
