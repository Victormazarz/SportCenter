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

import com.example.sportcenter.R;
import com.example.sportcenter.Objetos.item_card;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> implements View.OnClickListener {

    Context context;
    List<item_card> datos;
    private View.OnClickListener listener;

    public Adapter(Context context, List<item_card> datos) {
        this.context = context;
        this.datos = datos;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.card_item,parent,false);
        v.setOnClickListener(this);
        return new Holder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.img.setImageResource(datos.get(position).getImagen());
        holder.deporte.setText(datos.get(position).getTitulo());

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
        TextView deporte;

        public Holder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imagen);
            deporte = itemView.findViewById(R.id.actividad);

        }

    }


}
