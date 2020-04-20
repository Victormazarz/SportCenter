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

import com.example.sportcenter.Objetos.item_card;
import com.example.sportcenter.Objetos.item_playlist;
import com.example.sportcenter.R;

import java.util.List;

public class Adapter_Playlist extends RecyclerView.Adapter<Adapter_Playlist.Holder> implements View.OnClickListener {

    Context context;
    List<item_playlist> datos;
    private View.OnClickListener listener;

    public Adapter_Playlist(Context context, List<item_playlist> datos) {
        this.context = context;
        this.datos = datos;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.card_playlist,parent,false);
        v.setOnClickListener(this);
        return new Holder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.img.setImageResource(datos.get(position).getImagen());
        holder.titulo.setText(datos.get(position).getTitulo());
        holder.reproductor.setImageResource(datos.get(position).getReproductor());
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

        ImageView img,reproductor;
        TextView titulo;

        public Holder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.playlist_fondo);
            titulo = itemView.findViewById(R.id.playlist_titulo);
            reproductor = itemView.findViewById(R.id.playlist_reproductor);


        }

    }


}
